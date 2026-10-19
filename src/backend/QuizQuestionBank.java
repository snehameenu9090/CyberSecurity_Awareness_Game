package com.cyberaware.backend;

import java.util.List;

public final class QuizQuestionBank {
    private QuizQuestionBank() {
    }

    public static List<QuizQuestion> questions(QuizCategory category, StudyLanguage language) {
        return switch (category) {
            case PASSWORD -> List.of(
                    q(language,
                            "A shopping-site password was exposed. How do you protect your email?",
                            "शॉपिंग साइट का पासवर्ड लीक हुआ। ईमेल को कैसे बचाएँ?",
                            "কেনাকাটার সাইটের পাসওয়ার্ড ফাঁস। ইমেইল কীভাবে বাঁচাবেন?",
                            "வாங்கும் தளக் கடவுச்சொல் கசிந்தது. மின்னஞ்சலை எப்படிப் பாதுகாப்பீர்கள்?",
                            "షాపింగ్ సైట్ పాస్‌వర్డ్ బయటపడింది. ఇమెయిల్‌ను ఎలా కాపాడతారు?",
                            "खरेदीच्या संकेतस्थळाचा पासवर्ड उघड झाला. ईमेल कसे सुरक्षित ठेवाल?",
                            choices(language, category), 0, why(language, category)),
                    q(language,
                            "What is the safest habit for passwords across accounts?",
                            "अलग-अलग खातों के लिए पासवर्ड की सबसे सुरक्षित आदत क्या है?",
                            "বিভিন্ন অ্যাকাউন্টে পাসওয়ার্ডের নিরাপদ অভ্যাস কী?",
                            "பல கணக்குகளுக்கான பாதுகாப்பான கடவுச்சொல் பழக்கம் எது?",
                            "అనేక ఖాతాలకు సురక్షిత పాస్‌వర్డ్ అలవాటు ఏది?",
                            "अनेक खात्यांसाठी पासवर्डची सुरक्षित सवय कोणती?",
                            choices(language, category), 0, why(language, category)));
            case PHISHING -> List.of(
                    q(language,
                            "A message requests a delivery fee through an unexpected link. What should you do?",
                            "अनजान लिंक से पार्सल शुल्क माँगा गया। क्या करें?",
                            "অচেনা লিংকে পার্সেল ফি চাওয়া হয়েছে। কী করবেন?",
                            "தெரியாத இணைப்பில் பார்சல் கட்டணம் கேட்கப்படுகிறது. என்ன செய்வீர்கள்?",
                            "తెలియని లింక్ ద్వారా పార్సెల్ రుసుము అడిగారు. ఏమి చేస్తారు?",
                            "अनोळखी दुव्याने पार्सल शुल्क मागितले. काय कराल?",
                            choices(language, category), 0, why(language, category)),
                    q(language,
                            "A caller asks you to read out a one-time login code. What is safest?",
                            "कॉलर लॉगिन OTP माँगता है। सबसे सुरक्षित क्या है?",
                            "ফোনকারী লগইন OTP চাইছে। সবচেয়ে নিরাপদ কাজ কী?",
                            "அழைப்பவர் உள்நுழைவு OTP கேட்கிறார். பாதுகாப்பானது எது?",
                            "కాలర్ లాగిన్ OTP అడుగుతున్నారు. సురక్షితమైనది ఏది?",
                            "फोनवर लॉगिन OTP मागितला. सुरक्षित कृती कोणती?",
                            choices(language, category), 0, why(language, category)));
            case WIFI -> List.of(
                    q(language,
                            "Two cafe Wi-Fi names look alike. What should you do?",
                            "कैफ़े के दो Wi-Fi नाम एक जैसे हैं। क्या करें?",
                            "ক্যাফের দুটি Wi-Fi নাম একই রকম। কী করবেন?",
                            "கஃபேவின் இரண்டு Wi-Fi பெயர்கள் ஒரே மாதிரி. என்ன செய்வீர்கள்?",
                            "కేఫ్‌లో రెండు Wi-Fi పేర్లు ఒకేలా ఉన్నాయి. ఏమి చేస్తారు?",
                            "कॅफेमधील दोन Wi-Fi नावे सारखी आहेत. काय कराल?",
                            choices(language, category), 0, why(language, category)),
                    q(language,
                            "What is safer for sensitive activity on unfamiliar public Wi-Fi?",
                            "अनजान सार्वजनिक Wi-Fi पर संवेदनशील काम के लिए क्या सुरक्षित है?",
                            "অপরিচিত পাবলিক Wi-Fi-তে সংবেদনশীল কাজের জন্য কী নিরাপদ?",
                            "தெரியாத பொது Wi-Fi-இல் முக்கியப் பணிக்கு எது பாதுகாப்பானது?",
                            "తెలియని పబ్లిక్ Wi-Fiలో సున్నితమైన పనికి ఏది సురక్షితం?",
                            "अनोळखी सार्वजनिक Wi-Fi वर संवेदनशील कामासाठी काय सुरक्षित?",
                            choices(language, category), 0, why(language, category)));
            case MALWARE -> List.of(
                    q(language,
                            "An unknown pop-up offers a cleaner after claiming your device is infected. What should you do?",
                            "अनजान पॉप-अप डिवाइस संक्रमित बताकर क्लीनर देता है। क्या करें?",
                            "অচেনা পপ-আপ ডিভাইসে সংক্রমণ বলে ক্লিনার দিচ্ছে। কী করবেন?",
                            "தெரியாத பாப்-அப் சாதனம் பாதிக்கப்பட்டதாகக் கூறி சுத்திகரிப்பானைத் தருகிறது. என்ன செய்வீர்கள்?",
                            "తెలియని పాప్-అప్ పరికరానికి వైరస్ ఉందని క్లీనర్ ఇస్తోంది. ఏమి చేస్తారు?",
                            "अनोळखी पॉप-अप उपकरण संक्रमित सांगून क्लीनर देतो. काय कराल?",
                            choices(language, category), 0, why(language, category)),
                    q(language,
                            "You suspect ransomware on a device. What is a safe first step?",
                            "डिवाइस पर रैनसमवेयर का शक है। पहला सुरक्षित कदम क्या है?",
                            "ডিভাইসে র‍্যানসমওয়্যারের সন্দেহ। প্রথম নিরাপদ পদক্ষেপ কী?",
                            "சாதனத்தில் ransomware சந்தேகம். முதல் பாதுகாப்பான செயல் எது?",
                            "పరికరంలో రాన్సమ్‌వేర్ అనుమానం. మొదటి సురక్షిత చర్య ఏది?",
                            "उपकरणावर रॅन्समवेअरचा संशय. पहिले सुरक्षित पाऊल कोणते?",
                            choices(language, category), 0, why(language, category)));
            case AI -> List.of(
                    q(language,
                            "An AI chatbot gives an urgent payment link. What should you do?",
                            "AI चैटबॉट तुरंत भुगतान लिंक देता है। क्या करें?",
                            "AI চ্যাটবট জরুরি পেমেন্ট লিংক দিয়েছে। কী করবেন?",
                            "AI அவசரக் கட்டண இணைப்பைத் தருகிறது. என்ன செய்வீர்கள்?",
                            "AI అత్యవసర చెల్లింపు లింక్ ఇచ్చింది. ఏమి చేస్తారు?",
                            "AI तातडीची पेमेंट लिंक देते. काय कराल?",
                            choices(language, category), 0, why(language, category)),
                    q(language,
                            "What should you never enter into a public AI tool?",
                            "सार्वजनिक AI टूल में क्या कभी नहीं डालना चाहिए?",
                            "পাবলিক AI টুলে কী কখনও দেওয়া উচিত নয়?",
                            "பொது AI கருவியில் எதை இடக்கூடாது?",
                            "పబ్లిక్ AI సాధనంలో ఏమి ఇవ్వకూడదు?",
                            "सार्वजनिक AI साधनात काय देऊ नये?",
                            choices(language, category), 0, why(language, category)));
        };
    }

    private static QuizQuestion q(StudyLanguage language, String english, String hindi,
                                  String bengali, String tamil, String telugu, String marathi,
                                  String[] options, int correct, String explanation) {
        return new QuizQuestion(text(language, english, hindi, bengali, tamil, telugu, marathi),
                options, correct, explanation);
    }

    private static String[] choices(StudyLanguage language, QuizCategory category) {
        return switch (category) {
            case PASSWORD -> new String[]{
                    text(language, "Use unique strong passwords", "हर खाते के लिए अलग मज़बूत पासवर्ड",
                            "প্রতিটি অ্যাকাউন্টে আলাদা শক্তিশালী পাসওয়ার্ড", "ஒவ்வொரு கணக்குக்கும் தனி வலுவான கடவுச்சொல்",
                            "ప్రతి ఖాతాకు ప్రత్యేక బలమైన పాస్‌వర్డ్", "प्रत्येक खात्यासाठी वेगळा मजबूत पासवर्ड"),
                    text(language, "Reuse one short password", "एक छोटा पासवर्ड बार-बार रखें", "একটি ছোট পাসওয়ার্ড বারবার ব্যবহার",
                            "ஒரு குறுகிய கடவுச்சொல்லை மீண்டும் பயன்படுத்துங்கள்", "ఒక చిన్న పాస్‌వర్డ్‌ను మళ్లీ వాడండి",
                            "एक छोटा पासवर्ड पुन्हा वापरा"),
                    text(language, "Share passwords with others", "पासवर्ड दूसरों से साझा करें", "অন্যদের সঙ্গে পাসওয়ার্ড ভাগ করুন",
                            "கடவுச்சொற்களை மற்றவர்களுடன் பகிருங்கள்", "పాస్‌వర్డ్‌లను ఇతరులతో పంచుకోండి",
                            "पासवर्ड इतरांसोबत शेअर करा")};
            case PHISHING -> new String[]{
                    text(language, "Verify using an official trusted channel", "आधिकारिक भरोसेमंद माध्यम से जाँचें",
                            "অফিসিয়াল বিশ্বস্ত মাধ্যমে যাচাই করুন", "அதிகாரப்பூர்வ நம்பகமான வழியில் சரிபார்க்கவும்",
                            "అధికారిక నమ్మకమైన మార్గంలో నిర్ధారించండి", "अधिकृत विश्वासार्ह मार्गाने तपासा"),
                    text(language, "Open the unexpected link quickly", "अनजान लिंक तुरंत खोलें", "অচেনা লিংক দ্রুত খুলুন",
                            "தெரியாத இணைப்பை உடனே திறக்கவும்", "తెలియని లింక్‌ను వెంటనే తెరవండి",
                            "अनोळखी दुवा लगेच उघडा"),
                    text(language, "Share your password or one-time code", "पासवर्ड या OTP बताएँ",
                            "পাসওয়ার্ড বা OTP দিন", "கடவுச்சொல் அல்லது OTP-ஐப் பகிருங்கள்",
                            "పాస్‌వర్డ్ లేదా OTP పంచుకోండి", "पासवर्ड किंवा OTP सांगा")};
            case WIFI -> new String[]{
                    text(language, "Confirm the network or use mobile data", "नेटवर्क की पुष्टि करें या मोबाइल डेटा लें",
                            "নেটওয়ার্ক যাচাই করুন বা মোবাইল ডেটা ব্যবহার করুন", "வலையமைப்பை உறுதிசெய்யுங்கள் அல்லது மொபைல் தரவைப் பயன்படுத்துங்கள்",
                            "నెట్‌వర్క్ నిర్ధారించండి లేదా మొబైల్ డేటా వాడండి", "नेटवर्कची खात्री करा किंवा मोबाइल डेटा वापरा"),
                    text(language, "Join whichever name looks familiar", "जो नाम जाना-पहचाना लगे उससे जुड़ें",
                            "পরিচিত মনে হওয়া নেটওয়ার্কে যুক্ত হন", "அறிமுகமான பெயரில் உள்ள வலையமைப்பில் இணையுங்கள்",
                            "తెలిసిన పేరులా కనిపించేదానికి కనెక్ట్ అవ్వండి", "ओळखीच्या नावाच्या नेटवर्कला जोडा"),
                    text(language, "Ignore security warnings", "सुरक्षा चेतावनियाँ अनदेखी करें",
                            "নিরাপত্তা সতর্কতা উপেক্ষা করুন", "பாதுகாப்பு எச்சரிக்கைகளைப் புறக்கணிக்கவும்",
                            "భద్రతా హెచ్చరికలను పట్టించుకోకండి", "सुरक्षा इशारे दुर्लक्षित करा")};
            case MALWARE -> new String[]{
                    text(language, "Disconnect and use trusted security help", "नेटवर्क से अलग करके भरोसेमंद सुरक्षा मदद लें",
                            "সংযোগ বিচ্ছিন্ন করে বিশ্বস্ত নিরাপত্তা সহায়তা নিন", "துண்டித்து நம்பகமான பாதுகாப்பு உதவியைப் பெறுங்கள்",
                            "వేరు చేసి నమ్మకమైన భద్రతా సహాయం పొందండి", "वेगळे करून विश्वसनीय सुरक्षा मदत घ्या"),
                    text(language, "Install an unknown cleaner", "अनजान क्लीनर इंस्टॉल करें",
                            "অচেনা ক্লিনার ইনস্টল করুন", "தெரியாத சுத்திகரிப்பானை நிறுவுங்கள்",
                            "తెలియని క్లీనర్‌ను ఇన్‌స్టాల్ చేయండి", "अनोळखी क्लीनर स्थापित करा"),
                    text(language, "Connect every backup drive", "सभी बैकअप ड्राइव जोड़ें",
                            "সব ব্যাকআপ ড্রাইভ সংযুক্ত করুন", "எல்லா காப்புப்பிரதி இயக்ககங்களையும் இணைக்கவும்",
                            "అన్ని బ్యాకప్ డ్రైవ్‌లను కనెక్ట్ చేయండి", "सर्व बॅकअप ड्राइव्ह जोडा")};
            case AI -> new String[]{
                    text(language, "Verify claims and protect private information", "जानकारी जाँचें और निजी डेटा सुरक्षित रखें",
                            "তথ্য যাচাই করুন এবং ব্যক্তিগত তথ্য সুরক্ষিত রাখুন", "தகவலைச் சரிபார்த்து தனிப்பட்ட விவரங்களைப் பாதுகாக்கவும்",
                            "సమాచారాన్ని నిర్ధారించి వ్యక్తిగత వివరాలు కాపాడండి", "माहिती तपासा आणि खासगी माहिती सुरक्षित ठेवा"),
                    text(language, "Trust every answer without checking", "बिना जाँचे हर उत्तर पर भरोसा करें",
                            "যাচাই না করে সব উত্তর বিশ্বাস করুন", "சரிபார்க்காமல் ஒவ்வொரு பதிலையும் நம்புங்கள்",
                            "తనిఖీ చేయకుండా ప్రతి సమాధానాన్ని నమ్మండి", "तपासल्याशिवाय प्रत्येक उत्तरावर विश्वास ठेवा"),
                    text(language, "Enter passwords and private records", "पासवर्ड और निजी रिकॉर्ड डालें",
                            "পাসওয়ার্ড ও ব্যক্তিগত নথি দিন", "கடவுச்சொல் மற்றும் தனிப்பட்ட பதிவுகளை உள்ளிடுங்கள்",
                            "పాస్‌వర్డ్‌లు, వ్యక్తిగత రికార్డులు ఇవ్వండి", "पासवर्ड आणि खासगी नोंदी द्या")};
        };
    }

    private static String why(StudyLanguage language, QuizCategory category) {
        return switch (category) {
            case PASSWORD -> text(language, "Unique passwords help prevent one breach exposing other accounts.",
                    "अलग पासवर्ड एक लीक से दूसरे खातों को बचाते हैं।", "আলাদা পাসওয়ার্ড এক সাইটের ফাঁস থেকে অন্য অ্যাকাউন্ট বাঁচায়।",
                    "தனித்த கடவுச்சொற்கள் ஒரு கசிவிலிருந்து மற்ற கணக்குகளைப் பாதுகாக்கும்.",
                    "ప్రత్యేక పాస్‌వర్డ్‌లు ఒక లీక్ నుంచి ఇతర ఖాతాలను కాపాడతాయి.", "वेगळे पासवर्ड एका गळतीपासून इतर खाती वाचवतात.");
            case PHISHING -> text(language, "Verify requests independently. Never share passwords or one-time codes.",
                    "अनुरोध की अलग से पुष्टि करें। पासवर्ड या OTP न बताएँ।", "অনুরোধ আলাদাভাবে যাচাই করুন। পাসওয়ার্ড বা OTP দেবেন না।",
                    "கோரிக்கையைத் தனியாகச் சரிபார்க்கவும். கடவுச்சொல் அல்லது OTP பகிர வேண்டாம்.",
                    "అభ్యర్థనను విడిగా నిర్ధారించండి. పాస్‌వర్డ్ లేదా OTP పంచుకోవద్దు.", "विनंतीची स्वतंत्र खात्री करा. पासवर्ड किंवा OTP देऊ नका.");
            case WIFI -> text(language, "A network name alone is not proof. Confirm it and avoid sensitive activity on untrusted Wi-Fi.",
                    "सिर्फ नाम से नेटवर्क असली साबित नहीं होता। पुष्टि करें और संवेदनशील काम टालें।", "শুধু নাম দিয়ে নেটওয়ার্ক আসল প্রমাণ হয় না। যাচাই করুন এবং সংবেদনশীল কাজ এড়ান।",
                    "பெயர் மட்டும் உண்மையை நிரூபிக்காது. உறுதிசெய்து முக்கியப் பணிகளைத் தவிர்க்கவும்.",
                    "పేరు మాత్రమే నిజమని నిరూపించదు. నిర్ధారించి సున్నితమైన పనులు నివారించండి.", "फक्त नावावरून नेटवर्क खरे ठरत नाही. खात्री करा आणि संवेदनशील काम टाळा.");
            case MALWARE -> text(language, "Avoid unknown downloads. Disconnect a suspected infected device and get trusted help.",
                    "अनजान डाउनलोड से बचें। संक्रमित डिवाइस अलग करके भरोसेमंद मदद लें।", "অচেনা ডাউনলোড এড়ান। সংক্রমিত ডিভাইস বিচ্ছিন্ন করে বিশ্বস্ত সহায়তা নিন।",
                    "தெரியாத பதிவிறக்கங்களைத் தவிர்க்கவும். பாதிப்பு சாதனத்தைத் துண்டித்து நம்பகமான உதவியைப் பெறுங்கள்.",
                    "తెలియని డౌన్‌లోడ్‌లు నివారించండి. అనుమానిత పరికరాన్ని వేరు చేసి సహాయం పొందండి.", "अनोळखी डाउनलोड टाळा. संशयित उपकरण वेगळे करून विश्वसनीय मदत घ्या.");
            case AI -> text(language, "AI can make mistakes. Verify important claims and never submit private credentials or records.",
                    "AI गलत हो सकता है। अहम जानकारी जाँचें और निजी पासवर्ड या रिकॉर्ड न डालें।", "AI ভুল করতে পারে। গুরুত্বপূর্ণ তথ্য যাচাই করুন, ব্যক্তিগত পাসওয়ার্ড বা নথি দেবেন না।",
                    "AI தவறாகக் கூறலாம். முக்கியத் தகவலைச் சரிபார்த்து தனிப்பட்ட தரவை இட வேண்டாம்.",
                    "AI తప్పు చెప్పవచ్చు. ముఖ్యమైన సమాచారాన్ని నిర్ధారించి వ్యక్తిగత వివరాలు ఇవ్వవద్దు.", "AI चुकीचे सांगू शकते. महत्त्वाची माहिती तपासा आणि खासगी तपशील देऊ नका.");
        };
    }

    private static String text(StudyLanguage language, String english, String hindi, String bengali,
                               String tamil, String telugu, String marathi) {
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
