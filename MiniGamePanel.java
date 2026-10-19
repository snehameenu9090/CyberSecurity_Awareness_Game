package com.cyberaware.frontend;

import com.cyberaware.backend.StudyLanguage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

abstract class MiniGamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    protected final transient IntConsumer award;
    protected final transient Supplier<StudyLanguage> language;
    protected int points;
    protected int lives = 3;
    protected String feedback;
    protected final JLabel status = new JLabel();

    protected MiniGamePanel(Color background, IntConsumer award, Supplier<StudyLanguage> language) {
        this.award = award;
        this.language = language;
        feedback = t("Press START / RESTART to play.");
        setLayout(new BorderLayout(0, 12));
        setBackground(background);
        setBorder(new EmptyBorder(17, 17, 17, 17));
        status.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 13));
        status.setForeground(UiTheme.TEXT);
        status.setBorder(new EmptyBorder(4, 4, 4, 4));
        add(status, BorderLayout.NORTH);
    }

    protected void showStatus(String prefix) {
        status.setOpaque(false);
        status.setForeground(UiTheme.TEXT);
        status.setBorder(new EmptyBorder(4, 4, 4, 4));
        status.setText("<html><div style='width:660px'>" + prefix + " &nbsp; ⭐ " + points
                + t(" points") + " &nbsp; 💛 " + Math.max(0, lives) + " " + t(" lives") + "<br>"
                + feedback + "</div></html>");
    }

    protected void showResult(boolean won, String title, String details) {
        status.setOpaque(true);
        status.setBackground(won ? UiTheme.MINT_PAGE : UiTheme.PINK_PAGE);
        status.setForeground(won ? UiTheme.SUCCESS : UiTheme.DANGER);
        status.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(won ? UiTheme.SUCCESS : UiTheme.DANGER),
                new EmptyBorder(10, 12, 10, 12)));
        status.setText("<html><div style='width:760px'><b>" + title + "</b><br>"
                + details.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\n", "<br>") + "</div></html>");
    }

    protected void addPoints(int amount) {
        points += amount;
        award.accept(amount);
    }

    protected String t(String english) {
        return UiText.text(language.get(), english);
    }
}
