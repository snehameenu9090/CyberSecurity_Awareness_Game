package com.cyberaware.frontend;

import com.cyberaware.backend.StudyLanguage;
import com.cyberaware.backend.AwarenessTopic;
import com.cyberaware.backend.AwarenessContent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public final class AwarenessModule {
    private static final Color[] PAGE_COLORS = {
            UiTheme.PINK_PAGE, UiTheme.LAVENDER_PAGE, UiTheme.MINT_PAGE,
            UiTheme.SKY_PAGE, UiTheme.PEACH_PAGE, UiTheme.SUN_PAGE
    };
    private static final Color[] TITLE_COLORS = {
            UiTheme.PINK_INK, UiTheme.PURPLE_INK, UiTheme.PRIMARY_DARK,
            UiTheme.BLUE_INK, UiTheme.PEACH_INK, UiTheme.WARNING
    };

    private final CyberAwareUI ui;

    public AwarenessModule(CyberAwareUI ui) {
        this.ui = ui;
    }

    public JPanel build() {
        StudyLanguage language = ui.getApp().getStudyLanguage();
        JPanel page = ui.contentPage(
                localized(language, "Cyber safety toolkit", "साइबर सुरक्षा मार्गदर्शिका",
                        "সাইবার নিরাপত্তা নির্দেশিকা", "இணையப் பாதுகாப்பு வழிகாட்டி",
                        "సైబర్ భద్రత మార్గదర్శిని", "सायबर सुरक्षा मार्गदर्शक"),
                localized(language, "Read each topic, see a real-world example, and use the safety checklist.",
                        "हर विषय पढ़ें, उदाहरण देखें और सुरक्षा सूची अपनाएँ।",
                        "প্রতিটি বিষয় পড়ুন, উদাহরণ দেখুন এবং নিরাপত্তা তালিকা অনুসরণ করুন।",
                        "ஒவ்வொரு தலைப்பையும் படித்து, எடுத்துக்காட்டைப் பார்த்து, பாதுகாப்புப் பட்டியலைப் பின்பற்றுங்கள்.",
                        "ప్రతి అంశాన్ని చదివి, ఉదాహరణను చూసి, భద్రతా సూచనలను అనుసరించండి.",
                        "प्रत्येक विषय वाचा, उदाहरण पाहा आणि सुरक्षा यादी वापरा."));
        JPanel topics = new JPanel();
        topics.setOpaque(false);
        topics.setLayout(new BoxLayout(topics, BoxLayout.Y_AXIS));
        List<AwarenessTopic> allTopics = AwarenessContent.allTopics(language);
        for (int i = 0; i < allTopics.size(); i++) {
            topics.add(buildTopic(allTopics.get(i), PAGE_COLORS[i], TITLE_COLORS[i], language));
            topics.add(Box.createVerticalStrut(16));
        }

        JScrollPane scroll = new JScrollPane(topics,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(22);
        page.add(scroll, BorderLayout.CENTER);
        return page;
    }

    private JPanel buildTopic(AwarenessTopic topic, Color background, Color headingColor,
                              StudyLanguage language) {
        JPanel card = ui.panel(new BorderLayout());
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(background);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.BORDER),
                new EmptyBorder(22, 24, 22, 24)));
        card.add(ui.label("✦  " + topic.title(), 21, headingColor, true));
        card.add(Box.createVerticalStrut(14));
        addSection(card, localized(language, "WHAT TO KNOW", "क्या जानें", "যা জানবেন",
                "நீங்கள் அறிய வேண்டியது", "మీరు తెలుసుకోవాల్సింది", "काय जाणून घ्यावे"),
                topic.advice(), UiTheme.PRIMARY_DARK, 5);
        card.add(Box.createVerticalStrut(10));
        addSection(card, localized(language, "REAL-WORLD EXAMPLE", "वास्तविक उदाहरण",
                "বাস্তব উদাহরণ", "நிஜ உலக எடுத்துக்காட்டு", "నిజ జీవిత ఉదాహరణ",
                "प्रत्यक्ष उदाहरण"), topic.example(), UiTheme.BLUE_INK, 4);
        card.add(Box.createVerticalStrut(10));
        addSection(card, localized(language, "WHAT YOU SHOULD DO", "आपको क्या करना चाहिए",
                "আপনার যা করা উচিত", "நீங்கள் செய்ய வேண்டியது", "మీరు చేయాల్సింది",
                "तुम्ही काय करावे"), topic.checklist(), UiTheme.PINK_INK, 4);
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, card.getPreferredSize().height));
        return card;
    }

    private static String localized(StudyLanguage language, String english, String hindi,
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

    private void addSection(JPanel parent, String heading, String text, Color ink, int rows) {
        JLabel title = ui.label(heading, 13, ink, true);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        parent.add(title);
        parent.add(Box.createVerticalStrut(5));

        int readableRows = Math.max(rows, (int) Math.ceil(text.length() / 75.0) + 1);
        JTextArea body = new JTextArea(text, readableRows, 70);
        body.setFont(new Font(UiTheme.FONT_FAMILY, Font.PLAIN, 15));
        body.setForeground(UiTheme.TEXT);
        body.setBackground(parent.getBackground());
        body.setLineWrap(true);
        body.setWrapStyleWord(true);
        body.setEditable(false);
        body.setFocusable(false);
        body.setOpaque(false);
        body.setBorder(BorderFactory.createEmptyBorder());
        body.setAlignmentX(Component.LEFT_ALIGNMENT);
        body.setMaximumSize(new Dimension(Integer.MAX_VALUE, body.getPreferredSize().height));
        parent.add(body);
    }
}
