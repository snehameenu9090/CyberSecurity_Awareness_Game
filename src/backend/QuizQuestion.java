package com.cyberaware.backend;

public record QuizQuestion(String prompt, String[] options, int correctIndex, String explanation) {
    public QuizQuestion {
        options = options.clone();
        if (correctIndex < 0 || correctIndex >= options.length) {
            throw new IllegalArgumentException("Correct answer must identify an available option.");
        }
    }

    @Override
    public String[] options() {
        return options.clone();
    }
}
