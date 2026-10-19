package com.cyberaware.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;
import java.awt.*;
import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import com.cyberaware.backend.StudyLanguage;

public final class PassphraseBuilderGame extends MiniGamePanel {
    private static final long serialVersionUID = 1L;
    private static final String[] WORDS = {
            "purple", "rocket", "meadow", "cloud", "turtle", "comet", "garden", "panda"
    };
    private final JToggleButton[] tiles = new JToggleButton[WORDS.length];
    private final JLabel preview = new JLabel("Your word-tile sample will appear here.", SwingConstants.CENTER);
    private final JButton create = new JButton("✨ MAKE MY SAMPLE PHRASE");
    private final Timer timer;
    private int seconds = 45;
    private boolean finished;
    private boolean started;

    public PassphraseBuilderGame(IntConsumer award, Supplier<StudyLanguage> language) {
        super(UiTheme.LAVENDER_PAGE, award, language);
        JPanel center = new JPanel(new BorderLayout(0, 12));
        center.setOpaque(false);
        JPanel tilesPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        tilesPanel.setOpaque(false);
        for (int i = 0; i < WORDS.length; i++) {
            final int tileIndex = i;
            JToggleButton tile = new JToggleButton(wordLabels()[i]);
            tile.setUI(new BasicToggleButtonUI());
            tile.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 14));
            tile.setForeground(Color.WHITE);
            tile.setBackground(i % 2 == 0 ? UiTheme.PURPLE_INK : UiTheme.BLUE_INK);
            tile.setOpaque(true);
            tile.setContentAreaFilled(true);
            tile.setBorderPainted(true);
            tile.setFocusPainted(false);
            tile.setBorder(tileBorder(false));
            tile.addChangeListener(event -> {
                boolean selected = tile.isSelected();
                tile.setBackground(selected ? UiTheme.PRIMARY_DARK
                        : tileIndex % 2 == 0 ? UiTheme.PURPLE_INK : UiTheme.BLUE_INK);
                tile.setBorder(tileBorder(selected));
            });
            tile.addActionListener(event -> updatePreview(tile));
            tiles[i] = tile;
            tilesPanel.add(tile);
        }
        preview.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 17));
        preview.setForeground(UiTheme.TEXT);
        preview.setOpaque(true);
        preview.setBackground(Color.WHITE);
        preview.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.PRIMARY_DARK, 2),
                new EmptyBorder(12, 14, 12, 14)));
        center.add(tilesPanel, BorderLayout.CENTER);
        center.add(preview, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.add(status, BorderLayout.CENTER);
        create.setUI(new BasicButtonUI());
        create.setText(t("✨ MAKE MY SAMPLE PHRASE"));
        create.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 12));
        create.setForeground(UiTheme.TEXT);
        create.setBackground(new Color(255, 224, 145));
        create.setOpaque(true);
        create.setContentAreaFilled(true);
        create.setBorderPainted(true);
        create.setFocusPainted(false);
        create.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.PRIMARY_DARK, 2),
                new EmptyBorder(10, 14, 10, 14)));
        create.setPreferredSize(new Dimension(260, 48));
        create.addActionListener(event -> submit());
        footer.add(create, BorderLayout.SOUTH);
        add(footer, BorderLayout.SOUTH);
        timer = new Timer(1000, event -> onTimerTick());
        showStatus(t("🔐 Select exactly four different words. Make a sample phrase—not a real account password."));
    }

    public void setActive(boolean active) {
        if (!active) {
            timer.stop();
        } else if (started && !finished && seconds > 0 && !timer.isRunning()) {
            timer.start();
        }
    }

    public void startGame() {
        timer.stop();
        started = true;
        seconds = 45;
        points = 0;
        lives = 3;
        finished = false;
        preview.setText(t("Your word-tile sample will appear here."));
        for (JToggleButton tile : tiles) {
            tile.setSelected(false);
            tile.setEnabled(true);
        }
        create.setEnabled(true);
        feedback = t("Goal: select four unique words. Long, unique passphrases are safer.");
        updateStatus();
        timer.start();
    }

    private void onTimerTick() {
        seconds--;
        updateStatus();
        if (seconds <= 0) {
            finished = true;
            timer.stop();
            feedback = t("Time is up. Correct objective: submit exactly four different word tiles.");
            lockTiles();
            showResult(false, t("TIME UP — TRY AGAIN!"),
                    t("Your submission: ") + selectionCount() + t(" word tiles selected.\n")
                            + t("Correct objective: choose exactly 4 unique words before time runs out.\n")
                            + t("This is a practice-only phrase; never use it as a real password."));
        }
    }

    private void updatePreview(JToggleButton changedTile) {
        int selected = selectionCount();
        if (selected > 4) {
            changedTile.setSelected(false);
            selected = selectionCount();
            feedback = t("You can choose up to four different words.");
            updateStatus();
            return;
        }
        String phrase = String.join(" • ", Arrays.stream(tiles)
                .filter(JToggleButton::isSelected).map(JToggleButton::getText).toList());
        preview.setText(phrase.isEmpty() ? t("Your word-tile sample will appear here.") : phrase);
        feedback = selected + t(" of 4 word tiles selected. This generated sample must never be reused as your real password.");
        updateStatus();
    }

    private int selectionCount() {
        int count = 0;
        for (JToggleButton tile : tiles) if (tile.isSelected()) count++;
        return count;
    }

    private void submit() {
        if (finished) return;
        if (selectionCount() != 4) {
            feedback = t("Select exactly four word tiles before submitting.");
            updateStatus();
            return;
        }
        finished = true;
        timer.stop();
        addPoints(30);
        String phrase = selectedPhrase();
        feedback = t("You submitted: ") + phrase + t(". This is only a sample; do not use it on a real account.");
        lockTiles();
        showResult(true, t("YOU WIN — PASSPHRASE BUILT!"),
                t("Your submission: ") + phrase + "\n"
                        + t("Correct objective: exactly 4 different words. +30 points.\n")
                        + t("This phrase is a demo only. Never use it as a real password."));
    }

    private String selectedPhrase() {
        return String.join(" ", Arrays.stream(tiles)
                .filter(JToggleButton::isSelected).map(JToggleButton::getText).toList());
    }

    private void lockTiles() {
        for (JToggleButton tile : tiles) tile.setEnabled(false);
        create.setEnabled(false);
        updateStatus();
    }

    private void updateStatus() {
        status.setOpaque(true);
        status.setBackground(Color.WHITE);
        status.setForeground(UiTheme.TEXT);
        status.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 14));
        status.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.PRIMARY_DARK, 2),
                new EmptyBorder(8, 10, 8, 10)));
        status.setText("<html><div style='width:660px;color:#0C1624'>⏱ " + seconds
                + t(" seconds ") + "&nbsp; | &nbsp; " + t("Score: ") + points
                + t(" points<br>") + feedback + "</div></html>");
    }

    private String[] wordLabels() {
        return switch (language.get()) {
            case ENGLISH, BENGALI, MARATHI -> WORDS.clone();
            case HINDI -> new String[]{"बैंगनी", "रॉकेट", "घास का मैदान", "बादल", "कछुआ", "धूमकेतु", "बगीचा", "पांडा"};
            case TAMIL -> new String[]{"ஊதா", "ராக்கெட்", "புல்வெளி", "மேகம்", "ஆமை", "வால்நட்சத்திரம்", "தோட்டம்", "பாண்டா"};
            case TELUGU -> new String[]{"ఊదా", "రాకెట్", "పచ్చిక మైదానం", "మేఘం", "తాబేలు", "తోకచుక్క", "తోట", "పాండా"};
        };
    }

    public void refreshLanguage() {
        String[] labels = wordLabels();
        for (int i = 0; i < tiles.length; i++) tiles[i].setText(labels[i]);
        create.setText(t("✨ MAKE MY SAMPLE PHRASE"));
        if (!started) {
            feedback = t("Goal: select four unique words. Long, unique passphrases are safer.");
            preview.setText(t("Your word-tile sample will appear here."));
            showStatus(t("🔐 Select exactly four different words. Make a sample phrase—not a real account password."));
        }
    }

    private static javax.swing.border.Border tileBorder(boolean selected) {
        Color outline = selected ? new Color(255, 203, 74) : UiTheme.TEXT;
        int thickness = selected ? 4 : 2;
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(outline, thickness),
                new EmptyBorder(13, 12, 13, 12));
    }
}
