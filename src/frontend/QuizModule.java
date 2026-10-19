package com.cyberaware.frontend;

import com.cyberaware.backend.CyberAwareApp;
import com.cyberaware.backend.StudyLanguage;
import com.cyberaware.backend.QuizCategory;
import com.cyberaware.backend.QuizQuestion;
import com.cyberaware.backend.QuizQuestionBank;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public final class QuizModule {
    private final CyberAwareUI ui;
    private final CyberAwareApp app;
    private List<QuizQuestion> questions = List.of();
    private QuizCategory category;
    private int index;
    private int score;
    private int selected = -1;
    private boolean answered;
    private String feedback = "";

    public QuizModule(CyberAwareUI ui, CyberAwareApp app) {
        this.ui = ui;
        this.app = app;
    }

    public JPanel build() {
        if (category == null) return categoryMenu();
        if (index == questions.size()) return result();
        StudyLanguage language = app.getStudyLanguage();
        JPanel page = ui.contentPage(category.displayName(language) + " quiz",
                text(language, "Answer two questions in this topic.",
                        "इस विषय के दो प्रश्नों के उत्तर दें।",
                        "এই বিষয়ের দুটি প্রশ্নের উত্তর দিন।",
                        "இந்தத் தலைப்பில் இரண்டு கேள்விகளுக்குப் பதிலளிக்கவும்.",
                        "ఈ అంశంలోని రెండు ప్రశ్నలకు సమాధానం ఇవ్వండి.",
                        "या विषयातील दोन प्रश्नांची उत्तरे द्या."));
        QuizQuestion question = questions.get(index);
        JPanel card = ui.panel(new BorderLayout(0, 16));
        card.setBorder(new EmptyBorder(26, 30, 26, 30));
        card.add(ui.label(text(language, "QUESTION " + (index + 1) + " / " + questions.size()
                        + "     •     SCORE " + score,
                "प्रश्न " + (index + 1) + " / " + questions.size() + "     •     अंक " + score,
                "প্রশ্ন " + (index + 1) + " / " + questions.size() + "     •     স্কোর " + score,
                "கேள்வி " + (index + 1) + " / " + questions.size() + "     •     மதிப்பெண் " + score,
                "ప్రశ్న " + (index + 1) + " / " + questions.size() + "     •     స్కోరు " + score,
                "प्रश्न " + (index + 1) + " / " + questions.size() + "     •     गुण " + score),
                12, UiTheme.PRIMARY_DARK, true), BorderLayout.NORTH);

        JPanel answers = ui.panel(new GridLayout(0, 1, 0, 10));
        answers.setOpaque(false);
        answers.add(ui.wrapped(question.prompt(), 19, UiTheme.TEXT));
        ButtonGroup group = new ButtonGroup();
        String[] options = question.options();
        for (int optionIndex = 0; optionIndex < options.length; optionIndex++) {
            final int choice = optionIndex;
            JRadioButton option = new JRadioButton(options[optionIndex]);
            option.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 15));
            option.setForeground(answered ? Color.WHITE : UiTheme.TEXT);
            option.setBackground(answered && optionIndex == question.correctIndex()
                    ? UiTheme.SUCCESS
                    : answered && optionIndex == selected ? UiTheme.DANGER : UiTheme.SURFACE_ALT);
            option.setOpaque(true);
            option.setBorder(new EmptyBorder(10, 10, 10, 10));
            option.setSelected(selected == optionIndex);
            option.setEnabled(!answered);
            option.addActionListener(event -> selected = choice);
            group.add(option);
            answers.add(option);
        }
        if (!feedback.isBlank()) {
            answers.add(ui.wrappedAt(feedback, 14,
                    answered && selected == question.correctIndex() ? UiTheme.SUCCESS : UiTheme.DANGER, 760));
        }
        card.add(answers, BorderLayout.CENTER);

        JButton action = ui.button(answered
                ? text(language, "NEXT QUESTION  →", "अगला प्रश्न  →", "পরের প্রশ্ন  →",
                "அடுத்த கேள்வி  →", "తదుపరి ప్రశ్న  →", "पुढील प्रश्न  →")
                : text(language, "SUBMIT ANSWER", "उत्तर जमा करें", "উত্তর জমা দিন",
                "பதிலைச் சமர்ப்பிக்கவும்", "సమాధానం సమర్పించండి", "उत्तर सबमिट करा"));
        action.addActionListener(event -> {
            if (!answered) {
                if (selected < 0) {
                    JOptionPane.showMessageDialog(ui,
                            text(language, "Choose an answer before continuing.",
                                    "आगे बढ़ने से पहले उत्तर चुनें।", "এগোনোর আগে উত্তর বেছে নিন।",
                                    "தொடர்வதற்கு முன் பதிலைத் தேர்ந்தெடுக்கவும்.",
                                    "కొనసాగించే ముందు సమాధానం ఎంచుకోండి.", "पुढे जाण्यापूर्वी उत्तर निवडा."),
                            text(language, "Answer required", "उत्तर आवश्यक", "উত্তর প্রয়োজন",
                                    "பதில் தேவை", "సమాధానం అవసరం", "उत्तर आवश्यक"),
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                answered = true;
                if (selected == question.correctIndex()) {
                    score++;
                    app.addPoints(10);
                    feedback = text(language, "CORRECT — YOU WIN THIS QUESTION!\nYour answer: ",
                            "सही — यह प्रश्न आपने जीता!\nआपका उत्तर: ",
                            "সঠিক — এই প্রশ্নে আপনি জিতেছেন!\nআপনার উত্তর: ",
                            "சரி — இந்தக் கேள்வியில் வெற்றி!\nஉங்கள் பதில்: ",
                            "సరైనది — ఈ ప్రశ్నలో గెలిచారు!\nమీ సమాధానం: ",
                            "बरोबर — हा प्रश्न तुम्ही जिंकलात!\nतुमचे उत्तर: ")
                            + options[selected] + "\n"
                            + text(language, "Why: ", "क्यों सही है: ", "কেন সঠিক: ",
                            "ஏன் சரி: ", "ఎందుకు సరైనది: ", "का बरोबर: ")
                            + question.explanation() + "\n+10 points.";
                } else {
                    feedback = text(language, "INCORRECT — TRY AGAIN ON THE NEXT QUESTION.\nYour answer: ",
                            "गलत — अगले प्रश्न में फिर कोशिश करें।\nआपका उत्तर: ",
                            "ভুল — পরের প্রশ্নে আবার চেষ্টা করুন।\nআপনার উত্তর: ",
                            "தவறு — அடுத்த கேள்வியில் முயற்சிக்கவும்.\nஉங்கள் பதில்: ",
                            "తప్పు — తదుపరి ప్రశ్నలో ప్రయత్నించండి.\nమీ సమాధానం: ",
                            "चूक — पुढील प्रश्नात पुन्हा प्रयत्न करा.\nतुमचे उत्तर: ")
                            + options[selected] + "\n"
                            + text(language, "Correct answer: ", "सही उत्तर: ", "সঠিক উত্তর: ",
                            "சரியான பதில்: ", "సరైన సమాధానం: ", "योग्य उत्तर: ")
                            + options[question.correctIndex()] + "\n"
                            + text(language, "Why: ", "क्यों: ", "কেন: ", "ஏன்: ", "ఎందుకు: ", "का: ")
                            + question.explanation();
                }
            } else {
                index++;
                selected = -1;
                answered = false;
                feedback = "";
            }
            ui.showDashboardAndSelect("quiz");
        });
        card.add(action, BorderLayout.SOUTH);
        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private JPanel categoryMenu() {
        StudyLanguage language = app.getStudyLanguage();
        JPanel page = ui.contentPage(text(language, "Choose a quiz topic", "क्विज़ का विषय चुनें",
                        "কুইজের বিষয় বেছে নিন", "வினாடி வினா தலைப்பைத் தேர்ந்தெடுக்கவும்",
                        "క్విజ్ అంశాన్ని ఎంచుకోండి", "प्रश्नमंजुषेचा विषय निवडा"),
                text(language, "Each section has its own questions and score.",
                        "हर विषय के प्रश्न और अंक अलग हैं।", "প্রতিটি বিষয়ের প্রশ্ন ও স্কোর আলাদা।",
                        "ஒவ்வொரு தலைப்பிற்கும் தனிக் கேள்விகளும் மதிப்பெண்ணும் உண்டு.",
                        "ప్రతి విభాగానికి వేర్వేరు ప్రశ్నలు, స్కోరు ఉంటాయి.",
                        "प्रत्येक विषयाचे प्रश्न आणि गुण वेगळे आहेत."));
        JPanel categories = new JPanel(new GridLayout(0, 1, 0, 12));
        categories.setOpaque(false);
        categories.setBorder(new EmptyBorder(10, 20, 10, 20));
        for (QuizCategory choice : QuizCategory.values()) {
            JButton button = ui.button(choice.displayName(language) + "  •  2 "
                    + text(language, "QUESTIONS", "प्रश्न", "প্রশ্ন", "கேள்விகள்", "ప్రశ్నలు", "प्रश्न"));
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.addActionListener(event -> startCategory(choice));
            categories.add(button);
        }
        page.add(categories, BorderLayout.CENTER);
        return page;
    }

    private void startCategory(QuizCategory selectedCategory) {
        category = selectedCategory;
        questions = QuizQuestionBank.questions(category, app.getStudyLanguage());
        index = 0;
        score = 0;
        selected = -1;
        answered = false;
        feedback = "";
        ui.showDashboardAndSelect("quiz");
    }

    private JPanel result() {
        StudyLanguage language = app.getStudyLanguage();
        boolean perfect = score == questions.size();
        JPanel page = ui.contentPage(perfect
                        ? text(language, "Perfect score, ", "पूर्ण अंक, ", "পূর্ণ স্কোর, ",
                        "முழு மதிப்பெண், ", "పూర్తి స్కోరు, ", "पूर्ण गुण, ") + app.getPlayerName() + "!"
                        : text(language, "Quiz complete, ", "क्विज़ पूरा, ", "কুইজ শেষ, ",
                        "வினாடி வினா முடிந்தது, ", "క్విజ్ పూర్తయింది, ", "प्रश्नमंजुषा पूर्ण, ")
                        + app.getPlayerName() + "!",
                text(language, "Final result: ", "अंतिम परिणाम: ", "চূড়ান্ত ফল: ",
                        "இறுதி முடிவு: ", "చివరి ఫలితం: ", "अंतिम निकाल: ")
                        + score + " / " + questions.size());
        JPanel card = ui.panel(new GridLayout(0, 1, 0, 16));
        card.setBorder(new EmptyBorder(30, 34, 30, 34));
        card.add(ui.label(perfect
                        ? text(language, "CYBER SAFETY CHAMPION!", "साइबर सुरक्षा चैंपियन!",
                        "সাইবার নিরাপত্তা চ্যাম্পিয়ন!", "இணையப் பாதுகாப்பு வெற்றியாளர்!",
                        "సైబర్ భద్రత ఛాంపియన్!", "सायबर सुरक्षा विजेते!")
                        : text(language, "KEEP PRACTISING!", "अभ्यास जारी रखें!", "অনুশীলন চালিয়ে যান!",
                        "தொடர்ந்து பயிற்சி செய்யுங்கள்!", "సాధన కొనసాగించండి!", "सराव सुरू ठेवा!"),
                24, UiTheme.PRIMARY_DARK, true));
        card.add(ui.wrapped(perfect
                ? text(language, "Congratulations, ", "बधाई हो, ", "অভিনন্দন, ", "வாழ்த்துகள், ",
                "అభినందనలు, ", "अभिनंदन, ") + app.getPlayerName()
                + text(language, "! You answered every question correctly.",
                "! आपने सभी प्रश्नों के सही उत्तर दिए।", "! আপনি সব প্রশ্নের সঠিক উত্তর দিয়েছেন।",
                "! எல்லா கேள்விகளுக்கும் சரியாகப் பதிலளித்தீர்கள்.",
                "! మీరు అన్ని ప్రశ్నలకు సరిగ్గా సమాధానం ఇచ్చారు.",
                "! तुम्ही सर्व प्रश्नांची योग्य उत्तरे दिलीत.")
                : text(language, "Review the explanations, then try this topic again.",
                "व्याख्या पढ़ें और इस विषय का फिर प्रयास करें।",
                "ব্যাখ্যাগুলি পড়ে এই বিষয়টি আবার চেষ্টা করুন।",
                "விளக்கங்களைப் படித்து இந்தத் தலைப்பை மீண்டும் முயற்சிக்கவும்.",
                "వివరణలు చదివి ఈ అంశాన్ని మళ్లీ ప్రయత్నించండి.",
                "स्पष्टीकरण वाचा आणि हा विषय पुन्हा सोडवा."),
                16, UiTheme.TEXT));
        JButton restart = ui.button(text(language, "RETAKE THIS QUIZ", "यह क्विज़ फिर दें",
                "এই কুইজটি আবার দিন", "இந்த வினாடி வினாவை மீண்டும் முயற்சிக்கவும்",
                "ఈ క్విజ్‌ను మళ్లీ రాయండి", "ही प्रश्नमंजुषा पुन्हा सोडवा"));
        restart.addActionListener(event -> startCategory(category));
        card.add(restart);
        JButton topics = ui.button(text(language, "CHOOSE ANOTHER TOPIC", "दूसरा विषय चुनें",
                "অন্য বিষয় বেছে নিন", "மற்றொரு தலைப்பைத் தேர்ந்தெடுக்கவும்",
                "మరో అంశాన్ని ఎంచుకోండి", "दुसरा विषय निवडा"));
        topics.addActionListener(event -> {
            category = null;
            questions = List.of();
            index = 0;
            score = 0;
            ui.showDashboardAndSelect("quiz");
        });
        card.add(topics);
        page.add(card, BorderLayout.CENTER);
        return page;
    }

    private static String text(StudyLanguage language, String english, String hindi,
                               String bengali, String tamil, String telugu, String marathi) {
        return switch (language) {
            case ENGLISH -> english;
            case HINDI -> hindi;
            case BENGALI -> bengali;
            case TAMIL -> tamil;
            case TELUGU -> telugu;
            case MARATHI -> marathi;
        };
    }
}
