package com.cyberaware.frontend;

import javax.swing.*;
import java.awt.*;

public final class UiTheme {
    public static final String FONT_FAMILY = "Nirmala UI";
    public static final Color BACKGROUND = new Color(241, 245, 249);
    public static final Color SURFACE = Color.WHITE;
    public static final Color SURFACE_ALT = new Color(225, 232, 240);
    public static final Color PRIMARY = new Color(0, 78, 89);
    public static final Color PRIMARY_DARK = new Color(0, 48, 62);
    public static final Color SECONDARY = new Color(28, 54, 122);
    public static final Color TEXT = new Color(12, 22, 36);
    public static final Color MUTED = new Color(42, 52, 66);
    public static final Color BORDER = new Color(205, 216, 228);
    public static final Color SUCCESS = new Color(0, 91, 48);
    public static final Color WARNING = new Color(105, 51, 0);
    public static final Color DANGER = new Color(137, 24, 34);
    public static final Color MINT_PAGE = new Color(229, 248, 241);
    public static final Color PINK_PAGE = new Color(255, 237, 241);
    public static final Color LAVENDER_PAGE = new Color(241, 236, 255);
    public static final Color SKY_PAGE = new Color(231, 243, 255);
    public static final Color PEACH_PAGE = new Color(255, 241, 224);
    public static final Color SUN_PAGE = new Color(255, 249, 220);
    public static final Color PINK_INK = new Color(92, 18, 43);
    public static final Color PURPLE_INK = new Color(54, 28, 102);
    public static final Color BLUE_INK = new Color(20, 45, 99);
    public static final Color PEACH_INK = new Color(90, 43, 5);
    public static final Color GAME_BACKGROUND = new Color(18, 30, 51);
    public static final Color GAME_GRID = new Color(39, 58, 83);
    public static final Color GAME_CYAN = new Color(79, 226, 222);
    public static final Color GAME_RED = new Color(248, 102, 112);
    public static final Color GAME_GOLD = new Color(255, 205, 94);

    private UiTheme() {
    }

    public static void install() {
        UIManager.put("defaultFont", new Font(FONT_FAMILY, Font.PLAIN, 13));
        UIManager.put("Panel.background", BACKGROUND);
        UIManager.put("Label.foreground", TEXT);
        UIManager.put("Label.disabledForeground", TEXT);
        UIManager.put("OptionPane.background", SURFACE);
        UIManager.put("OptionPane.messageBackground", SURFACE);
        UIManager.put("OptionPane.messageForeground", TEXT);
        UIManager.put("Button.background", PRIMARY_DARK);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.disabledText", TEXT);
        UIManager.put("Button.focus", SECONDARY);
        UIManager.put("RadioButton.background", SURFACE_ALT);
        UIManager.put("RadioButton.foreground", TEXT);
        UIManager.put("RadioButton.disabledText", TEXT);
        UIManager.put("ToggleButton.background", PRIMARY_DARK);
        UIManager.put("ToggleButton.foreground", Color.WHITE);
        UIManager.put("ToggleButton.disabledText", TEXT);
        UIManager.put("TextField.background", SURFACE);
        UIManager.put("TextField.foreground", TEXT);
        UIManager.put("TextField.caretForeground", PRIMARY);
        UIManager.put("TextArea.background", SURFACE);
        UIManager.put("TextArea.foreground", TEXT);
        UIManager.put("TextArea.disabledForeground", TEXT);
        UIManager.put("TextPane.background", SURFACE);
        UIManager.put("TextPane.foreground", TEXT);
        UIManager.put("CheckBox.background", SURFACE_ALT);
        UIManager.put("CheckBox.foreground", TEXT);
        UIManager.put("CheckBox.disabledText", TEXT);
        UIManager.put("List.background", SURFACE);
        UIManager.put("List.foreground", TEXT);
        UIManager.put("ComboBox.background", SURFACE);
        UIManager.put("ComboBox.foreground", TEXT);
        UIManager.put("ComboBox.disabledForeground", TEXT);
        UIManager.put("ScrollBar.thumb", PRIMARY_DARK);
        UIManager.put("ScrollPane.background", BACKGROUND);
    }
}
