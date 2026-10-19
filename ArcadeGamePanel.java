package com.cyberaware.frontend;

import com.cyberaware.backend.StudyLanguage;
import com.cyberaware.backend.GameEntity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

public final class ArcadeGamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int PLAYER_WIDTH = 58;
    private static final int PLAYER_HEIGHT = 34;
    private static final double MISSION_SECONDS = 60;

    private final transient IntConsumer awardPoints;
    private final transient Supplier<StudyLanguage> language;
    private final Timer frameTimer;
    private final transient List<GameEntity> entities = new ArrayList<>();
    private final boolean[] keysDown = new boolean[3];
    private double playerX;
    private double elapsed;
    private double nextSpawn;
    private double nextShot;
    private double lastFrameNanos;
    private int score;
    private int lives = 3;
    private int wave = 1;
    private int spawnCount;
    private String message;
    private GameState state = GameState.READY;

    public ArcadeGamePanel(IntConsumer awardPoints, Supplier<StudyLanguage> language) {
        this.awardPoints = awardPoints;
        this.language = language;
        message = t("Press START MISSION or Enter. Move with A/D or arrows; SPACE fires your firewall.");
        setOpaque(true);
        setBackground(UiTheme.GAME_BACKGROUND);
        setPreferredSize(new Dimension(720, 450));
        setMinimumSize(new Dimension(500, 330));
        setFocusable(true);
        frameTimer = new Timer(16, event -> updateFrame());
        frameTimer.setCoalesce(true);
        installControls();
    }

    public void startMission() {
        entities.clear();
        for (int i = 0; i < keysDown.length; i++) keysDown[i] = false;
        elapsed = 0;
        nextSpawn = 0.6;
        nextShot = 0;
        score = 0;
        lives = 3;
        wave = 1;
        spawnCount = 0;
        message = t("Collect SAFE packets, zap malware with SPACE, and dodge red phishing traps.");
        playerX = Math.max(PLAYER_WIDTH, (getWidth() - PLAYER_WIDTH) / 2.0);
        state = GameState.RUNNING;
        lastFrameNanos = System.nanoTime();
        frameTimer.start();
        requestFocusInWindow();
        repaint();
    }

    public void setActive(boolean active) {
        if (!active) {
            frameTimer.stop();
        } else if (state == GameState.RUNNING && !frameTimer.isRunning()) {
            lastFrameNanos = System.nanoTime();
            frameTimer.start();
            requestFocusInWindow();
        }
    }

    public void refreshLanguage() {
        if (state == GameState.READY) {
            message = t("Press START MISSION or Enter. Move with A/D or arrows; SPACE fires your firewall.");
        }
        repaint();
    }

    private void installControls() {
        bind("pressed LEFT", "left-on", () -> keysDown[0] = true);
        bind("released LEFT", "left-off", () -> keysDown[0] = false);
        bind("pressed A", "left-a-on", () -> keysDown[0] = true);
        bind("released A", "left-a-off", () -> keysDown[0] = false);
        bind("pressed RIGHT", "right-on", () -> keysDown[1] = true);
        bind("released RIGHT", "right-off", () -> keysDown[1] = false);
        bind("pressed D", "right-d-on", () -> keysDown[1] = true);
        bind("released D", "right-d-off", () -> keysDown[1] = false);
        bind("pressed SPACE", "fire-on", () -> keysDown[2] = true);
        bind("released SPACE", "fire-off", () -> keysDown[2] = false);
        bind("pressed P", "pause", this::togglePause);
        bind("pressed ENTER", "start", () -> {
            if (state != GameState.RUNNING) startMission();
        });
    }

    private void bind(String keyStroke, String name, Runnable action) {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyStroke), name);
        getActionMap().put(name, new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent event) {
                if (isShowing()) action.run();
            }
        });
    }

    private void togglePause() {
        if (state == GameState.RUNNING) {
            state = GameState.PAUSED;
            frameTimer.stop();
            message = t("Mission paused. Press P to continue or Enter to restart.");
        } else if (state == GameState.PAUSED) {
            state = GameState.RUNNING;
            lastFrameNanos = System.nanoTime();
            frameTimer.start();
            message = t("Back in the field!");
        }
        repaint();
    }

    private void updateFrame() {
        if (state != GameState.RUNNING || getWidth() <= PLAYER_WIDTH || getHeight() <= PLAYER_HEIGHT) return;
        long now = System.nanoTime();
        double delta = Math.min((now - lastFrameNanos) / 1_000_000_000.0, 0.05);
        lastFrameNanos = now;
        elapsed += delta;
        wave = 1 + (int) (elapsed / 15);

        double direction = (keysDown[1] ? 1 : 0) - (keysDown[0] ? 1 : 0);
        playerX = Math.max(PLAYER_WIDTH / 2.0 + 8,
                Math.min(getWidth() - PLAYER_WIDTH / 2.0 - 8, playerX + direction * 390 * delta));
        if (keysDown[2] && elapsed >= nextShot) {
            entities.add(new GameEntity(GameEntity.Type.FIREWALL_PULSE, playerX, getHeight() - 76, 570));
            nextShot = elapsed + 0.24;
        }

        if (elapsed >= nextSpawn) {
            spawnEntity();
            nextSpawn = elapsed + Math.max(0.36, 0.88 - wave * 0.09);
        }
        moveEntities(delta);
        checkCollisions();

        if (state == GameState.RUNNING && elapsed >= MISSION_SECONDS) {
            finish(GameState.WON, t("Firewall held! Excellent cyber defence. Mission bonus: 50 points."));
            awardPoints.accept(50);
        }
        repaint();
    }

    private void spawnEntity() {
        spawnCount++;
        double x = ThreadLocalRandom.current().nextDouble(24, Math.max(25, getWidth() - 24));
        if (spawnCount % 12 == 0) {
            entities.add(new GameEntity(GameEntity.Type.SHIELD, x, -22, 145 + wave * 12));
            return;
        }
        boolean packet = spawnCount % 3 == 0;
        GameEntity.Type type = packet ? GameEntity.Type.SAFE_PACKET
                : (ThreadLocalRandom.current().nextBoolean() ? GameEntity.Type.MALWARE : GameEntity.Type.PHISHING);
        entities.add(new GameEntity(type, x, -22, 125 + wave * 25));
    }

    private void moveEntities(double delta) {
        Iterator<GameEntity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            GameEntity entity = iterator.next();
            if (entity.getType() == GameEntity.Type.FIREWALL_PULSE) entity.moveUp(delta);
            else entity.move(delta);
            if (!entity.isActive() || entity.getY() < -50 || entity.getY() > getHeight() + 32) iterator.remove();
        }
    }

    private void checkCollisions() {
        double playerY = getHeight() - 64;
        for (GameEntity entity : entities) {
            if (!entity.isActive()) continue;
            if (entity.getType() == GameEntity.Type.FIREWALL_PULSE) {
                for (GameEntity target : entities) {
                    if (target.isActive() && isThreat(target.getType())
                            && Math.abs(entity.getX() - target.getX()) < 21
                            && Math.abs(entity.getY() - target.getY()) < 26) {
                        target.deactivate();
                        entity.deactivate();
                        score += 10;
                        awardPoints.accept(10);
                        message = t("Threat blocked! +10. Check unexpected links before opening them.");
                        break;
                    }
                }
                continue;
            }
            if (Math.abs(playerX - entity.getX()) > 37 || Math.abs(playerY - entity.getY()) > 32) continue;
            switch (entity.getType()) {
                case SAFE_PACKET -> {
                    entity.deactivate();
                    score += 15;
                    awardPoints.accept(15);
                    message = t("Safe backup collected! +15. Keep an offline copy of important files.");
                }
                case SHIELD -> {
                    entity.deactivate();
                    lives = Math.min(5, lives + 1);
                    score += 5;
                    awardPoints.accept(5);
                    message = t("Firewall shield collected. Multi-factor authentication adds another layer of defence.");
                }
                case MALWARE, PHISHING -> {
                    entity.deactivate();
                    lives--;
                    message = entity.getType() == GameEntity.Type.PHISHING
                            ? t("Phishing trap hit! Check sender addresses and avoid surprise links.")
                            : t("Malware breach! Install software updates from official sources.");
                    if (lives <= 0) finish(GameState.LOST,
                            t("Firewall breached. Learn from the alerts and try again with a safer strategy."));
                }
                case FIREWALL_PULSE -> { }
            }
        }
    }

    private static boolean isThreat(GameEntity.Type type) {
        return type == GameEntity.Type.MALWARE || type == GameEntity.Type.PHISHING;
    }

    private void finish(GameState finalState, String finalMessage) {
        if (state != GameState.RUNNING) return;
        state = finalState;
        frameTimer.stop();
        for (GameEntity entity : entities) entity.deactivate();
        message = finalMessage;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawWorld(g);
        if (state == GameState.READY || state == GameState.PAUSED || state == GameState.WON || state == GameState.LOST) {
            drawOverlay(g);
        }
        g.dispose();
    }

    private void drawWorld(Graphics2D g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(UiTheme.GAME_BACKGROUND);
        g.fillRect(0, 0, width, height);
        g.setColor(UiTheme.GAME_GRID);
        for (int x = 0; x < width; x += 40) g.drawLine(x, 48, x, height);
        for (int y = 48; y < height; y += 40) g.drawLine(0, y, width, y);

        g.setColor(new Color(229, 238, 248));
        g.fillRoundRect(15, 12, Math.max(50, width - 30), 31, 12, 12);
        g.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 13));
        g.setColor(UiTheme.TEXT);
        g.drawString(t("SCORE") + "  " + score, 28, 33);
        g.drawString(t("LIVES") + "  " + "♥ ".repeat(Math.max(0, lives)), Math.max(170, width / 2 - 20), 33);
        g.drawString(t("TIME") + "  " + String.format("%02d", Math.max(0, (int) (MISSION_SECONDS - elapsed))), width - 118, 33);
        g.setColor(UiTheme.GAME_CYAN);
        g.drawString(t("WAVE") + " " + wave, 29, 64);

        for (GameEntity entity : entities) drawEntity(g, entity);
        if (state == GameState.RUNNING || state == GameState.PAUSED) drawPlayer(g);
        drawMessage(g);
    }

    private void drawEntity(Graphics2D g, GameEntity entity) {
        if (!entity.isActive()) return;
        int x = (int) entity.getX();
        int y = (int) entity.getY();
        switch (entity.getType()) {
            case SAFE_PACKET -> {
                g.setColor(new Color(81, 176, 255));
                g.fillRoundRect(x - 12, y - 12, 24, 24, 7, 7);
                g.setColor(Color.WHITE);
                g.drawString("+", x - 4, y + 5);
            }
            case MALWARE -> {
                g.setColor(UiTheme.GAME_RED);
                g.fillOval(x - 13, y - 13, 26, 26);
                g.setColor(UiTheme.GAME_BACKGROUND);
                g.drawString("!", x - 3, y + 5);
            }
            case PHISHING -> {
                g.setColor(new Color(210, 103, 245));
                int[] xPoints = {x, x + 14, x, x - 14};
                int[] yPoints = {y - 15, y, y + 15, y};
                g.fillPolygon(xPoints, yPoints, 4);
                g.setColor(UiTheme.GAME_BACKGROUND);
                g.drawString("?", x - 4, y + 5);
            }
            case FIREWALL_PULSE -> {
                g.setColor(UiTheme.GAME_CYAN);
                g.fillRoundRect(x - 3, y - 13, 6, 25, 5, 5);
            }
            case SHIELD -> {
                g.setColor(UiTheme.GAME_GOLD);
                g.fillOval(x - 13, y - 13, 26, 26);
                g.setColor(UiTheme.GAME_BACKGROUND);
                g.drawString("+", x - 4, y + 5);
            }
        }
    }

    private void drawPlayer(Graphics2D g) {
        int x = (int) playerX;
        int y = getHeight() - 64;
        g.setColor(UiTheme.GAME_CYAN);
        g.fill(new RoundRectangle2D.Double(x - PLAYER_WIDTH / 2.0, y - PLAYER_HEIGHT / 2.0,
                PLAYER_WIDTH, PLAYER_HEIGHT, 15, 15));
        g.setColor(UiTheme.GAME_BACKGROUND);
        g.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 12));
        g.drawString("FW", x - 9, y + 5);
    }

    private void drawMessage(Graphics2D g) {
        g.setColor(new Color(229, 238, 248));
        g.fillRoundRect(14, getHeight() - 34, Math.max(60, getWidth() - 28), 25, 9, 9);
        g.setColor(UiTheme.TEXT);
        g.setFont(new Font(UiTheme.FONT_FAMILY, Font.PLAIN, 11));
        g.drawString(message, 24, getHeight() - 17);
    }

    private void drawOverlay(Graphics2D g) {
        g.setColor(new Color(10, 17, 29, 215));
        g.fillRect(0, 48, getWidth(), getHeight() - 48);
        String title = switch (state) {
            case READY -> t("CYBER SHIELD: DEFENDER");
            case PAUSED -> t("MISSION PAUSED");
            case WON -> t("NETWORK SECURED!");
            case LOST -> t("FIREWALL BREACHED");
            case RUNNING -> "";
        };
        g.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 29));
        drawCentered(g, title, getHeight() / 2 - 32, UiTheme.GAME_CYAN);
        g.setFont(new Font(UiTheme.FONT_FAMILY, Font.PLAIN, 14));
        String hint = state == GameState.READY
                ? t("Collect blue backups • Dodge purple phishing • SPACE zaps red malware")
                : message;
        drawCentered(g, hint, getHeight() / 2 + 2, Color.WHITE);
        if (state == GameState.READY) {
            g.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 13));
            drawCentered(g, t("MOVE: ← → or A / D     FIREWALL: SPACE     PAUSE: P     START: ENTER"),
                    getHeight() / 2 + 32, UiTheme.GAME_GOLD);
        } else if (state == GameState.WON || state == GameState.LOST) {
            g.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 15));
            drawCentered(g, t("FINAL SCORE") + "  " + score + t("     •     PRESS ENTER TO PLAY AGAIN"),
                    getHeight() / 2 + 35, Color.WHITE);
        }
    }

    private void drawCentered(Graphics2D g, String text, int y, Color color) {
        g.setColor(color);
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(text, (getWidth() - metrics.stringWidth(text)) / 2, y);
    }

    private String t(String english) {
        return UiText.text(language.get(), english);
    }

    private enum GameState {
        READY, RUNNING, PAUSED, WON, LOST
    }
}
