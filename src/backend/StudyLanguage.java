package com.cyberaware.backend;

public enum StudyLanguage {
    ENGLISH("English"),
    HINDI("हिन्दी"),
    BENGALI("বাংলা"),
    TAMIL("தமிழ்"),
    TELUGU("తెలుగు"),
    MARATHI("मराठी");

    private final String displayName;
    private static final StudyLanguage[] SELECTABLE = {
            ENGLISH, HINDI, TAMIL, TELUGU
    };

    StudyLanguage(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static StudyLanguage[] selectableLanguages() {
        return SELECTABLE.clone();
    }
}
