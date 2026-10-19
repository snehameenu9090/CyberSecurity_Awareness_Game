package com.cyberaware.frontend;

import com.cyberaware.backend.StudyLanguage;
import com.cyberaware.backend.CyberAwareApp;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.function.IntConsumer;

public final class GamesModule {
    private static final int ARCADE = 0;
    private static final int PHISHING = 1;
    private static final int MALWARE = 2;
    private static final int PASSWORD = 3;

    private final CyberAwareUI ui;
    private final CardLayout gameLayout = new CardLayout();
    private final JPanel gamePages = new JPanel(gameLayout);
    private final ArcadeGamePanel arcadeGame;
    private final PhishingSortGame phishingGame;
    private final MalwareCleanupGame malwareGame;
    private final PassphraseBuilderGame passphraseGame;
    private int selectedGame = -1;
    private boolean moduleActive;

    public GamesModule(CyberAwareUI ui, CyberAwareApp app) {
        this.ui = ui;
        IntConsumer addPoints = app::addPoints;
        arcadeGame = new ArcadeGamePanel(addPoints, app::getStudyLanguage);
        phishingGame = new PhishingSortGame(addPoints, app::getStudyLanguage);
        malwareGame = new MalwareCleanupGame(addPoints, app::getStudyLanguage);
        passphraseGame = new PassphraseBuilderGame(addPoints, app::getStudyLanguage);
    }

    public JPanel build() {
        arcadeGame.refreshLanguage();
        phishingGame.refreshLanguage();
        malwareGame.refreshLanguage();
        passphraseGame.refreshLanguage();
        JPanel page = ui.contentPage(t("GAME ARENA"),
                t("Choose an adventure! Each game has clear controls, a visible goal, and its own cyber-safety lesson."));
        gamePages.removeAll();
        JScrollPane menuScroll = new JScrollPane(buildArcadeMenu(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        menuScroll.setBorder(BorderFactory.createEmptyBorder());
        menuScroll.setOpaque(false);
        menuScroll.getViewport().setOpaque(false);
        menuScroll.getVerticalScrollBar().setUnitIncrement(18);
        gamePages.add(menuScroll, "menu");
        gamePages.add(buildArcadeGame(), "arcade");
        gamePages.add(buildPhishingGame(), "phishing");
        gamePages.add(buildMalwareGame(), "malware");
        gamePages.add(buildPassphraseGame(), "password");
        gamePages.setBackground(UiTheme.BACKGROUND);
        page.add(gamePages, BorderLayout.CENTER);
        showSelectedPage();
        return page;
    }

    private JPanel buildArcadeMenu() {
        JPanel menu = new JPanel(new GridLayout(2, 2, 16, 16));
        menu.setOpaque(false);
        menu.setBorder(new EmptyBorder(5, 5, 16, 16));
        menu.add(gameCard(t("🛡  Cyber Shield Defender"), UiTheme.SKY_PAGE, UiTheme.BLUE_INK,
                t("SPACE ARCADE  •  KEYBOARD"),
                t("Fly your shield ship through the cyber sky! Pick up blue backup stars, dodge sneaky purple phishing ghosts, and blast red malware with your firewall."),
                t("Survive for 60 seconds • move ← → or A/D • fire SPACE • pause P"),
                t("PLAY ARCADE"), ARCADE));
        menu.add(gameCard(t("✉  Phishing Inbox"), UiTheme.PINK_PAGE, UiTheme.PINK_INK,
                t("EMAIL SORTING  •  DRAG & DROP"),
                t("Become a scam-spotting detective! Drag each pretend email into the SPAM BIN or SAFE INBOX. Look for strange senders, fake urgency and suspicious links."),
                t("Sort 6 emails into the right bin. You have 3 hearts and earn 10 stars for each correct match."),
                t("OPEN INBOX"), PHISHING));
        menu.add(gameCard(t("🧹  Malware Cleanup Crew"), UiTheme.MINT_PAGE, UiTheme.PRIMARY_DARK,
                t("DEVICE RESCUE  •  CLICK TO SCAN"),
                t("Help the friendly computer! Inspect its pretend apps. Click the 4 sneaky malware downloads to quarantine them—but keep its trusted apps safe!"),
                t("Find all 4 infected downloads. Wrong guesses cost a heart. This is only a pretend device scan."),
                t("RESCUE THE DEVICE"), MALWARE));
        menu.add(gameCard(t("🔐  Passphrase Builder"), UiTheme.LAVENDER_PAGE, UiTheme.PURPLE_INK,
                t("WORD-TILE PUZZLE  •  PASSWORD POWER"),
                t("Make a secret-agent sample phrase! Pick 4 different word tiles before time runs out. Longer, unique passphrases are harder to guess."),
                t("Choose exactly 4 unique words in 45 seconds. Never use this demo phrase as your real password."),
                t("BUILD A PASSPHRASE"), PASSWORD));
        return menu;
    }

    private JPanel gameCard(String title, Color fill, Color ink, String tag,
                            String description, String goal, String action, int game) {
        JPanel card = new JPanel(new BorderLayout(0, 11));
        card.setBackground(fill);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.BORDER),
                new EmptyBorder(18, 20, 18, 20)));
        card.add(ui.label(tag, 10, ink, true), BorderLayout.NORTH);
        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.add(ui.label(title, 18, ink, true));
        text.add(Box.createVerticalStrut(9));
        text.add(ui.wrappedAt(description, 13, UiTheme.TEXT, 370));
        text.add(Box.createVerticalStrut(8));
        text.add(ui.wrappedAt(t("YOUR GOAL: ") + goal, 12, ink, 370));
        card.add(text, BorderLayout.CENTER);
        JButton play = ui.button(action);
        play.addActionListener(event -> openGame(game));
        card.add(play, BorderLayout.SOUTH);
        return card;
    }

    private JPanel buildArcadeGame() {
        return gamePage(t("🛡 CYBER SHIELD DEFENDER"), UiTheme.SKY_PAGE,
                t("FLY YOUR SHIP: Hold ← → or A/D to steer. Hold SPACE to fire firewall pulses. Press P to pause."),
                t("BLUE STAR = backup +15 • PURPLE GHOST = phishing • RED BUG = malware (zap it!) • GOLD SHIELD = one extra heart."),
                arcadeGame, arcadeGame::startMission);
    }

    private JPanel buildPhishingGame() {
        return gamePage(t("✉ PHISHING INBOX: SCAM-SPOTTER"), UiTheme.PINK_PAGE,
                t("HOW TO PLAY: Drag the pretend email card with your mouse. Drop scams in SPAM BIN and real messages in SAFE INBOX."),
                t("Sort 6 emails. Check sender spelling, fake urgency, unexpected prizes, and suspicious links. Each correct match earns 10 stars."),
                phishingGame, phishingGame::startGame);
    }

    private JPanel buildMalwareGame() {
        return gamePage(t("🧹 MALWARE CLEANUP CREW: DEVICE RESCUE"), UiTheme.MINT_PAGE,
                t("HOW TO PLAY: Click app icons to scan them. Infected apps turn into QUARANTINED. Restart to play another round."),
                t("Quarantine 4 suspicious downloads. A wrong guess costs a heart. Keep familiar, trusted apps safe."),
                malwareGame, malwareGame::startGame);
    }

    private JPanel buildPassphraseGame() {
        return gamePage(t("🔐 PASSPHRASE BUILDER: SECRET AGENT"), UiTheme.LAVENDER_PAGE,
                t("HOW TO PLAY: Tap exactly 4 different word tiles, then press MAKE MY SAMPLE PHRASE before 45 seconds."),
                t("Long, unique passphrases help protect accounts. The phrase here is practice-only; never use it as a real password."),
                passphraseGame, passphraseGame::startGame);
    }

    private JPanel gamePage(String title, Color background, String controls, String goal,
                            JPanel game, Runnable start) {
        JPanel page = new JPanel(new BorderLayout(0, 14));
        page.setBackground(background);
        page.setBorder(new EmptyBorder(16, 20, 18, 20));
        JPanel intro = new JPanel(new GridLayout(0, 1, 0, 7));
        intro.setOpaque(false);
        intro.add(ui.label(title, 23, UiTheme.TEXT, true));
        intro.add(ui.wrappedAt(controls, 13, UiTheme.PRIMARY_DARK, 750));
        intro.add(ui.wrappedAt(t("YOUR GOAL: ") + goal, 12, UiTheme.MUTED, 750));
        JPanel titleBar = new JPanel(new BorderLayout(8, 0));
        titleBar.setOpaque(false);
        titleBar.add(intro, BorderLayout.CENTER);
        JButton back = ui.button(t("← GAME ARENA"));
        back.addActionListener(event -> openGame(-1));
        titleBar.add(back, BorderLayout.EAST);
        page.add(titleBar, BorderLayout.NORTH);

        JPanel gameFrame = new JPanel(new BorderLayout());
        gameFrame.setBackground(background);
        gameFrame.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 255, 255, 220), 3, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        gameFrame.add(game, BorderLayout.CENTER);
        page.add(gameFrame, BorderLayout.CENTER);

        if (game instanceof ArcadeGamePanel) {
            JButton startButton = ui.button(t("▶ START / RESTART"));
            startButton.addActionListener(event -> {
                start.run();
                arcadeGame.requestFocusInWindow();
            });
            page.add(startButton, BorderLayout.SOUTH);
        } else {
            JPanel lower = new JPanel(new BorderLayout());
            lower.setOpaque(false);
            lower.add(ui.wrappedAt(t("✨ Play for stars and practise a real online-safety habit!"),
                    12, UiTheme.MUTED, 450), BorderLayout.CENTER);
            String startText = game == phishingGame ? t("✉ SORT EMAILS")
                    : game == malwareGame ? t("🧹 SCAN DEVICE") : t("🔐 CREATE SAMPLE");
            JButton startButton = ui.button("▶ " + startText + " / " + t("▶ START / RESTART"));
            startButton.addActionListener(event -> start.run());
            lower.add(startButton, BorderLayout.EAST);
            page.add(lower, BorderLayout.SOUTH);
        }
        return page;
    }

    private void openGame(int game) {
        selectedGame = game;
        showSelectedPage();
    }

    private void showSelectedPage() {
        String card = switch (selectedGame) {
            case ARCADE -> "arcade";
            case PHISHING -> "phishing";
            case MALWARE -> "malware";
            case PASSWORD -> "password";
            default -> "menu";
        };
        gameLayout.show(gamePages, card);
        syncActiveGame();
    }

    public void setActive(boolean active) {
        moduleActive = active;
        syncActiveGame();
        passphraseGame.setActive(active && selectedGame == PASSWORD);
    }

    public void showArenaMenu() {
        selectedGame = -1;
        showSelectedPage();
    }

    private void syncActiveGame() {
        arcadeGame.setActive(moduleActive && selectedGame == ARCADE);
        passphraseGame.setActive(moduleActive && selectedGame == PASSWORD);
    }

    private String t(String english) {
        return UiText.text(ui.getApp().getStudyLanguage(), english);
    }
}
