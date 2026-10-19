package com.cyberaware.frontend;

import com.cyberaware.backend.CyberAwareApp;
import com.cyberaware.backend.StudyLanguage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public final class CyberAwareUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private final transient CyberAwareApp app;
    private final CardLayout screenLayout = new CardLayout();
    private final JPanel screenCards = new JPanel(screenLayout);
    private final JPanel frameRoot = new JPanel(new BorderLayout());
    private final transient AwarenessModule awarenessModule;
    private final transient GamesModule gamesModule;
    private final transient QuizModule quizModule;

    public CyberAwareUI(CyberAwareApp app) {
        super("CYBERSECURITY AWARENESS GAME");
        this.app = app;
        awarenessModule = new AwarenessModule(this);
        gamesModule = new GamesModule(this, app);
        quizModule = new QuizModule(this, app);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1020, 700));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameRoot.setBackground(UiTheme.BACKGROUND);
        screenCards.setBackground(UiTheme.BACKGROUND);
        setContentPane(frameRoot);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                gamesModule.setActive(false);
            }
        });
        showWelcome();
    }

    void showWelcome() {
        gamesModule.setActive(false);
        frameRoot.removeAll();
        JPanel card = panel(new GridBagLayout());
        card.setBorder(new EmptyBorder(34, 48, 34, 48));
        card.setPreferredSize(new Dimension(680, 550));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(8, 0, 8, 0);

        JLabel projectName = label("<html><div style='text-align:center'>CYBERSECURITY<br>AWARENESS GAME</div></html>",
                34, UiTheme.PRIMARY_DARK, true);
        projectName.setHorizontalAlignment(SwingConstants.CENTER);
        addAt(card, projectName, c, 0);
        JLabel subtitle = label(UiText.text(app.getStudyLanguage(), "DESKTOP CYBER SAFETY ARCADE"),
                13, UiTheme.SECONDARY, true);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        addAt(card, subtitle, c, 1);
        JLabel intro = wrapped(UiText.text(app.getStudyLanguage(),
                "Learn to spot digital threats. Practise with games. Stay safer online."),
                16, UiTheme.TEXT);
        intro.setHorizontalAlignment(SwingConstants.CENTER);
        addAt(card, intro, c, 2);
        JLabel playerNameLabel = label(UiText.text(app.getStudyLanguage(), "PLAYER NAME"),
                12, UiTheme.MUTED, true);
        addAt(card, playerNameLabel, c, 3);

        JTextField name = new JTextField();
        name.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        name.setForeground(UiTheme.TEXT);
        name.setCaretColor(UiTheme.PRIMARY);
        name.setBackground(UiTheme.SURFACE);
        name.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.BORDER),
                new EmptyBorder(12, 15, 12, 15)));
        name.setText(app.getPlayerName());
        name.putClientProperty("JTextField.placeholderText",
                UiText.text(app.getStudyLanguage(), "Enter your name"));
        addAt(card, name, c, 4);

        JLabel languageLabel = label(UiText.text(app.getStudyLanguage(),
                "LEARNING LANGUAGE  •  सीखने की भाषा"), 12, UiTheme.MUTED, true);
        languageLabel.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 12));
        addAt(card, languageLabel, c, 5);
        JComboBox<StudyLanguage> language = new JComboBox<>(StudyLanguage.selectableLanguages());
        language.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 16));
        language.setForeground(UiTheme.TEXT);
        language.setBackground(Color.WHITE);
        language.setSelectedItem(app.getStudyLanguage());
        addAt(card, language, c, 6);

        JButton start = button(UiText.text(app.getStudyLanguage(), "START YOUR MISSION  →"));
        start.setFont(new Font("Segoe UI", Font.BOLD, 18));
        start.setForeground(Color.WHITE);
        start.setBackground(new Color(0, 42, 58));
        start.setOpaque(true);
        start.setContentAreaFilled(true);
        start.setBorderPainted(true);
        start.setUI(new BasicButtonUI());
        start.setMinimumSize(new Dimension(520, 62));
        start.setPreferredSize(new Dimension(520, 62));
        start.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(5, 20, 32), 3),
                new EmptyBorder(15, 22, 15, 22)));
        start.addActionListener(event -> {
            try {
                if (!app.startSession(name.getText())) {
                    JOptionPane.showMessageDialog(this,
                            UiText.text(app.getStudyLanguage(), "Please enter your name to start."),
                            UiText.text(app.getStudyLanguage(), "Player name required"),
                            JOptionPane.INFORMATION_MESSAGE);
                    name.requestFocusInWindow();
                    return;
                }
                showDashboardAndSelect("home");
            } catch (SQLException exception) {
                showDatabaseError(exception);
            }
        });
        addAt(card, start, c, 7);
        JLabel privacy = wrapped(UiText.text(app.getStudyLanguage(),
                "No real passwords needed. Your progress stays on this computer."),
                12, UiTheme.MUTED);
        privacy.setHorizontalAlignment(SwingConstants.CENTER);
        addAt(card, privacy, c, 8);
        language.addActionListener(event -> {
            try {
                app.setStudyLanguage((StudyLanguage) language.getSelectedItem());
            } catch (SQLException exception) {
                showDatabaseError(exception);
                language.setSelectedItem(app.getStudyLanguage());
                return;
            }
            StudyLanguage selectedLanguage = app.getStudyLanguage();
            subtitle.setText(UiText.text(selectedLanguage, "DESKTOP CYBER SAFETY ARCADE"));
            intro.setText("<html><div style='width:600px;line-height:1.5'>"
                    + htmlText(UiText.text(selectedLanguage,
                    "Learn to spot digital threats. Practise with games. Stay safer online."))
                    + "</div></html>");
            playerNameLabel.setText(UiText.text(selectedLanguage, "PLAYER NAME"));
            languageLabel.setText(UiText.text(selectedLanguage, "LEARNING LANGUAGE  •  सीखने की भाषा"));
            name.putClientProperty("JTextField.placeholderText",
                    UiText.text(selectedLanguage, "Enter your name"));
            start.setText(UiText.text(selectedLanguage, "START YOUR MISSION  →"));
            privacy.setText("<html><div style='width:600px;line-height:1.5'>"
                    + htmlText(UiText.text(selectedLanguage,
                    "No real passwords needed. Your progress stays on this computer."))
                    + "</div></html>");
        });

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(card);
        frameRoot.add(center, BorderLayout.CENTER);
        frameRoot.revalidate();
        frameRoot.repaint();
    }

    public void showDashboardAndSelect(String destination) {
        gamesModule.setActive(false);
        if ("games".equals(destination)) {
            gamesModule.showArenaMenu();
        }
        showDashboard();
        screenLayout.show(screenCards, destination);
        gamesModule.setActive("games".equals(destination));
    }

    private void showDashboard() {
        frameRoot.removeAll();
        JPanel sidebar = panel(new GridLayout(0, 1, 0, 11));
        sidebar.setBackground(UiTheme.SURFACE_ALT);
        sidebar.setBorder(new EmptyBorder(25, 16, 24, 16));
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.add(label("✦  CYBER GAME", 17, UiTheme.PRIMARY_DARK, true));
        sidebar.add(nav(UiText.text(app.getStudyLanguage(), "⌂   HOME"), "home"));
        sidebar.add(nav(UiText.text(app.getStudyLanguage(), "▤   AWARENESS"), "awareness"));
        sidebar.add(nav(UiText.text(app.getStudyLanguage(), "✧   GAME ARENA"), "games"));
        sidebar.add(nav(UiText.text(app.getStudyLanguage(), "✓   QUIZ"), "quiz"));
        sidebar.add(Box.createVerticalStrut(25));
        sidebar.add(label(UiText.text(app.getStudyLanguage(), "YOUR SCORE"), 12, UiTheme.MUTED, true));
        sidebar.add(label(app.getScore() + UiText.text(app.getStudyLanguage(), " POINTS"), 19, UiTheme.PRIMARY_DARK, true));
        sidebar.add(label(app.getPlayerName(), 12, UiTheme.TEXT, true));
        frameRoot.add(sidebar, BorderLayout.WEST);

        JPanel content = panel(new BorderLayout());
        content.add(buildHeader(), BorderLayout.NORTH);
        screenCards.removeAll();
        screenCards.add(buildHome(), "home");
        screenCards.add(awarenessModule.build(), "awareness");
        screenCards.add(gamesModule.build(), "games");
        screenCards.add(quizModule.build(), "quiz");
        content.add(screenCards, BorderLayout.CENTER);
        frameRoot.add(content, BorderLayout.CENTER);
        screenLayout.show(screenCards, "home");
        frameRoot.revalidate();
        frameRoot.repaint();
    }

    private JPanel buildHeader() {
        JPanel header = panel(new BorderLayout());
        header.setBorder(new EmptyBorder(18, 28, 15, 28));
        JPanel titleBlock = panel(new GridLayout(0, 1, 0, 4));
        titleBlock.setOpaque(false);
        titleBlock.setBorder(BorderFactory.createEmptyBorder());
        titleBlock.add(label("CYBERSECURITY AWARENESS GAME", 17, UiTheme.PRIMARY_DARK, true));
        titleBlock.add(label(UiText.text(app.getStudyLanguage(), "Welcome, ") + app.getPlayerName()
                        + UiText.text(app.getStudyLanguage(), "  |  Learn • Play • Protect"),
                11, UiTheme.MUTED, false));
        header.add(titleBlock, BorderLayout.WEST);
        header.add(label(UiText.text(app.getStudyLanguage(), "5 TEAM MODULES   •   19 PROJECT FILES"), 11, UiTheme.PRIMARY_DARK, true),
                BorderLayout.EAST);
        return header;
    }

    private JPanel buildHome() {
        JPanel page = contentPage("CYBERSECURITY AWARENESS GAME",
                UiText.text(app.getStudyLanguage(), "Your desktop cybersecurity mission starts here."));
        JPanel hero = new HomeHeroPanel();
        hero.setLayout(new BorderLayout(20, 14));
        hero.setBorder(new EmptyBorder(30, 34, 30, 34));
        JPanel copy = panel(new BorderLayout(0, 13));
        copy.setOpaque(false);
        copy.setBorder(BorderFactory.createEmptyBorder());
        JPanel heroCopy = new JPanel();
        heroCopy.setOpaque(false);
        heroCopy.setLayout(new BoxLayout(heroCopy, BoxLayout.Y_AXIS));
        heroCopy.add(label(UiText.text(app.getStudyLanguage(), "✦  YOUR CYBER ADVENTURE STARTS HERE"), 13, UiTheme.PURPLE_INK, true));
        heroCopy.add(Box.createVerticalStrut(12));
        heroCopy.add(wrapped(UiText.text(app.getStudyLanguage(), "Play. Explore. Become a cyber-safety hero."), 27, UiTheme.TEXT));
        heroCopy.add(Box.createVerticalStrut(12));
        heroCopy.add(label(UiText.text(app.getStudyLanguage(), "Points earned: ") + app.getScore()
                        + UiText.text(app.getStudyLanguage(), "   |   Player: ") + app.getPlayerName(),
                14, UiTheme.MUTED, true));
        heroCopy.add(Box.createVerticalStrut(18));
        JLabel nextStep = label(UiText.text(app.getStudyLanguage(), "CHOOSE YOUR NEXT STEP"), 12, UiTheme.PRIMARY_DARK, true);
        nextStep.setAlignmentX(Component.LEFT_ALIGNMENT);
        heroCopy.add(nextStep);
        heroCopy.add(Box.createVerticalStrut(9));

        JPanel actions = new JPanel(new GridLayout(1, 3, 10, 0));
        actions.setOpaque(false);
        actions.setBorder(BorderFactory.createEmptyBorder());
        actions.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton games = button(UiText.text(app.getStudyLanguage(), "EXPLORE GAME ARENA"));
        games.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 12));
        games.addActionListener(event -> showDashboardAndSelect("games"));
        JButton awareness = button(UiText.text(app.getStudyLanguage(), "LEARN SAFETY"));
        awareness.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 12));
        awareness.addActionListener(event -> showDashboardAndSelect("awareness"));
        JButton quiz = button(UiText.text(app.getStudyLanguage(), "TAKE THE QUIZ"));
        quiz.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 12));
        quiz.addActionListener(event -> showDashboardAndSelect("quiz"));
        actions.add(games);
        actions.add(awareness);
        actions.add(quiz);
        heroCopy.add(actions);

        JPanel centeredCopy = new JPanel(new GridBagLayout());
        centeredCopy.setOpaque(false);
        centeredCopy.add(heroCopy);
        copy.add(centeredCopy, BorderLayout.CENTER);
        hero.add(copy, BorderLayout.CENTER);
        JPanel mascot = new JPanel(new BorderLayout(0, 6));
        mascot.setOpaque(false);
        mascot.setPreferredSize(new Dimension(210, 270));
        mascot.add(new TeddyMascot(), BorderLayout.CENTER);
        JPanel mascotCaption = new JPanel(new GridLayout(0, 1, 0, 2));
        mascotCaption.setOpaque(false);
        JLabel buddy = label(UiText.text(app.getStudyLanguage(), "CYBER BUDDY"), 13, UiTheme.PRIMARY_DARK, true);
        buddy.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel buddyMessage = label(UiText.text(app.getStudyLanguage(), "Ready to help you stay safe!"), 11, UiTheme.TEXT, true);
        buddyMessage.setHorizontalAlignment(SwingConstants.CENTER);
        mascotCaption.add(buddy);
        mascotCaption.add(buddyMessage);
        mascot.add(mascotCaption, BorderLayout.SOUTH);
        hero.add(mascot, BorderLayout.EAST);
        page.add(hero, BorderLayout.CENTER);

        JPanel stats = new JPanel(new GridLayout(1, 3, 14, 0));
        stats.setOpaque(false);
        stats.add(stat("4", UiText.text(app.getStudyLanguage(), "Fun cyber games"), UiTheme.PINK_PAGE, UiTheme.PINK_INK));
        stats.add(stat("6", UiText.text(app.getStudyLanguage(), "Helpful safety lessons"), UiTheme.LAVENDER_PAGE, UiTheme.PURPLE_INK));
        stats.add(stat("10", UiText.text(app.getStudyLanguage(), "Questions across 5 topic quizzes"), UiTheme.SUN_PAGE, UiTheme.WARNING));
        page.add(stats, BorderLayout.SOUTH);
        return page;
    }

    private JPanel stat(String amount, String caption, Color background, Color accent) {
        JPanel card = panel(new GridLayout(0, 1, 0, 6));
        card.setBackground(background);
        card.setBorder(new EmptyBorder(13, 17, 13, 17));
        card.add(label(amount, 23, accent, true));
        card.add(label(caption, 12, UiTheme.TEXT, false));
        return card;
    }

    private static final class HomeHeroPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setPaint(new GradientPaint(0, 0, UiTheme.PINK_PAGE,
                    getWidth(), getHeight(), UiTheme.LAVENDER_PAGE));
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
            g.setColor(new Color(255, 255, 255, 105));
            g.fillOval(getWidth() - 230, -75, 230, 230);
            g.fillOval(getWidth() - 145, getHeight() - 115, 160, 160);
            g.dispose();
        }
    }

    private static final class TeddyMascot extends JPanel {
        private static final long serialVersionUID = 1L;

        private TeddyMascot() {
            setOpaque(false);
            setPreferredSize(new Dimension(210, 220));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            double scale = Math.min(getWidth() / 210.0, getHeight() / 220.0);
            g.translate((getWidth() - 210 * scale) / 2, (getHeight() - 220 * scale) / 2);
            g.scale(scale, scale);

            Color outline = new Color(91, 55, 43);
            Color bodyFur = new Color(145, 83, 51);
            Color lightFur = new Color(250, 220, 181);
            Color innerEar = new Color(235, 153, 148);

            g.setColor(new Color(255, 255, 255, 155));
            g.fillOval(26, 8, 158, 158);

            g.setColor(outline);
            g.fillOval(46, 23, 52, 52);
            g.fillOval(112, 23, 52, 52);
            g.setColor(innerEar);
            g.fillOval(59, 36, 26, 26);
            g.fillOval(125, 36, 26, 26);

            g.setColor(outline);
            g.fillOval(67, 156, 43, 37);
            g.fillOval(100, 156, 43, 37);
            g.setColor(lightFur);
            g.fillOval(75, 165, 25, 15);
            g.fillOval(109, 165, 25, 15);

            g.setColor(bodyFur);
            g.fillOval(59, 101, 92, 82);
            g.fillRoundRect(43, 111, 36, 58, 25, 25);
            g.fillRoundRect(131, 111, 36, 58, 25, 25);
            g.setColor(new Color(187, 124, 83));
            g.fillOval(72, 119, 66, 57);

            g.fillOval(48, 40, 114, 105);
            g.setColor(lightFur);
            g.fillOval(69, 78, 72, 53);

            g.setColor(outline);
            g.fillOval(78, 78, 10, 13);
            g.fillOval(122, 78, 10, 13);
            g.setColor(Color.WHITE);
            g.fillOval(81, 80, 3, 4);
            g.fillOval(125, 80, 3, 4);
            g.setColor(new Color(235, 143, 145));
            g.fillOval(62, 99, 18, 10);
            g.fillOval(130, 99, 18, 10);
            g.setColor(outline);
            g.fillOval(96, 95, 18, 13);
            g.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawArc(91, 102, 13, 12, 205, 125);
            g.drawArc(104, 102, 13, 12, 210, 125);

            g.setColor(new Color(19, 54, 79));
            g.fillRoundRect(54, 137, 102, 51, 9, 9);
            g.setColor(new Color(210, 237, 246));
            g.fillRoundRect(60, 143, 90, 37, 5, 5);
            g.setColor(new Color(12, 38, 59));
            g.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 10));
            g.drawString("CYBER", 83, 159);
            g.drawString("GAME", 86, 173);
            g.setColor(new Color(19, 54, 79));
            g.fillRoundRect(47, 185, 116, 9, 5, 5);
            g.setColor(outline);
            g.fillOval(48, 133, 25, 25);
            g.fillOval(137, 133, 25, 25);
            g.setColor(lightFur);
            g.fillOval(54, 139, 13, 13);
            g.fillOval(143, 139, 13, 13);

            g.dispose();
        }
    }

    private JButton nav(String title, String destination) {
        JButton button = button(title);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addActionListener(event -> showDashboardAndSelect(destination));
        return button;
    }

    public JPanel contentPage(String title, String subtitle) {
        JPanel page = panel(new BorderLayout(0, 20));
        page.setBorder(new EmptyBorder(10, 28, 28, 28));
        JPanel heading = panel(new GridLayout(0, 1, 0, 5));
        heading.setBorder(BorderFactory.createEmptyBorder());
        heading.add(label(title, 25, UiTheme.TEXT, true));
        heading.add(label(subtitle, 13, UiTheme.MUTED, false));
        page.add(heading, BorderLayout.NORTH);
        return page;
    }

    public CyberAwareApp getApp() {
        return app;
    }

    private void showDatabaseError(SQLException exception) {
        JOptionPane.showMessageDialog(this,
                "The local database could not save this change.\n"
                        + "Check that the project data folder is writable.\n\n"
                        + exception.getMessage(),
                "Database save error", JOptionPane.ERROR_MESSAGE);
    }

    public JPanel panel(LayoutManager layout) {
        JPanel result = new JPanel(layout);
        result.setBackground(UiTheme.SURFACE);
        result.setBorder(BorderFactory.createLineBorder(UiTheme.BORDER));
        return result;
    }

    public JLabel label(String text, int size, Color color, boolean bold) {
        JLabel result = new JLabel(text);
        result.setFont(new Font(UiTheme.FONT_FAMILY, bold ? Font.BOLD : Font.PLAIN, size));
        result.setForeground(color);
        return result;
    }

    public JLabel wrapped(String text, int size, Color color) {
        return label("<html><div style='width:600px;line-height:1.5'>" + htmlText(text) + "</div></html>",
                size, color, false);
    }

    public JLabel wrappedAt(String text, int size, Color color, int width) {
        return label("<html><div style='width:" + width + "px;line-height:1.4'>" + htmlText(text) + "</div></html>",
                size, color, false);
    }

    private static String htmlText(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\n", "<br>");
    }

    public JButton button(String text) {
        JButton result = new JButton(text);
        result.setUI(new BasicButtonUI());
        result.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 15));
        result.setForeground(Color.WHITE);
        result.setBackground(UiTheme.PRIMARY_DARK);
        result.setOpaque(true);
        result.setContentAreaFilled(true);
        result.setBorderPainted(true);
        result.setFocusPainted(false);
        result.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.TEXT, 2),
                new EmptyBorder(12, 16, 12, 16)));
        result.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return result;
    }

    static void addAt(JPanel parent, Component child, GridBagConstraints template, int row) {
        GridBagConstraints constraints = (GridBagConstraints) template.clone();
        constraints.gridy = row;
        parent.add(child, constraints);
    }
}
