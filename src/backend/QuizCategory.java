package com.cyberaware.backend;


public enum QuizCategory {
    PASSWORD("Password safety", "पासवर्ड सुरक्षा", "পাসওয়ার্ড নিরাপত্তা",
            "கடவுச்சொல் பாதுகாப்பு", "పాస్‌వర్డ్ భద్రత", "पासवर्ड सुरक्षा"),
    PHISHING("Phishing and scams", "फ़िशिंग और धोखाधड़ी", "ফিশিং ও প্রতারণা",
            "ஃபிஷிங் மற்றும் மோசடிகள்", "ఫిషింగ్ మరియు మోసాలు", "फिशिंग आणि फसवणूक"),
    WIFI("Public Wi-Fi", "सार्वजनिक Wi-Fi", "পাবলিক Wi-Fi",
            "பொது Wi-Fi", "పబ్లిక్ Wi-Fi", "सार्वजनिक Wi-Fi"),
    MALWARE("Malware safety", "मैलवेयर सुरक्षा", "ম্যালওয়্যার নিরাপত্তা",
            "தீம்பொருள் பாதுகாப்பு", "మాల్వేర్ భద్రత", "मालवेअर सुरक्षा"),
    AI("AI safety", "AI सुरक्षा", "AI নিরাপত্তা",
            "AI பாதுகாப்பு", "AI భద్రత", "AI सुरक्षा");

    private final String[] names;

    QuizCategory(String... names) {
        this.names = names;
    }

    public String displayName(StudyLanguage language) {
        return names[language.ordinal()];
    }
}
