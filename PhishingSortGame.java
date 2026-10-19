package com.cyberaware.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import com.cyberaware.backend.StudyLanguage;

public final class PhishingSortGame extends MiniGamePanel {
    private static final long serialVersionUID = 1L;
    private final transient MockEmail[] emails = {
            new MockEmail("delivery-alert@parcel-help.example", "Parcel held — pay $1.50 now",
                    "Unexpected fee • shortened link • delivery pressure", true),
            new MockEmail("library@city-library.example", "Your reserved book is ready",
                    "Expected update • normal sender • no payment link", false),
            new MockEmail("security@paypaI-login.example", "ACCOUNT LOCKED! Verify password now!",
                    "Misspelled domain • urgent threat • password link", true),
            new MockEmail("club@school.example", "Friday club meeting moved to room 4",
                    "Known school address • expected schedule update", false),
            new MockEmail("gift@winner-prize.example", "You won! Send your sign-in code",
                    "Unexpected prize • asks for a private login code", true),
            new MockEmail("coach@school.example", "Practice starts at 3:30 today",
                    "Known sender • expected team schedule", false)
    };
    private int index;
    private Point dragOffset;
    private boolean finished;
    private final DropBoard board = new DropBoard();
    private final JLabel progress = new JLabel("", SwingConstants.CENTER);

    public PhishingSortGame(IntConsumer award, Supplier<StudyLanguage> language) {
        super(UiTheme.PINK_PAGE, award, language);
        progress.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 13));
        progress.setForeground(UiTheme.PINK_INK);
        add(progress, BorderLayout.CENTER);
        add(board, BorderLayout.SOUTH);
        add(new JPanel(), BorderLayout.EAST);
        showStatus(t("🎀 Drag each message into the right inbox. You have three helpful retries."));
    }

    public void startGame() {
        index = 0;
        lives = 3;
        points = 0;
        finished = false;
        feedback = t("Drop into SPAM or SAFE INBOX. Check the sender, link and urgency.");
        showStatus(t("🎀 Inbox mission started!"));
        showEmail();
    }

    private void showEmail() {
        if (index >= emails.length || lives <= 0) {
            finished = true;
            boolean won = index >= emails.length;
            String ending = won ? t("YOU WIN — INBOX SORTED!") : t("MISSION OVER — TRY AGAIN!");
            String details = t("Correctly sorted: ") + index + " / " + emails.length
                    + t(" • Score: ") + points + t(" points.\n")
                    + (won ? t("Great scam spotting. Keep checking senders, links, and unexpected requests.")
                    : t("Your last message was ") + emails[index].sender() + t(". Correct bin: ")
                    + (emails[index].phishing() ? t("SPAM BIN.") : t("SAFE INBOX."))
                    + t(" Check the sender, urgency, and link before choosing."));
            board.showResult(ending + " " + details, won);
            showResult(won, ending, details);
            return;
        }
        progress.setText(t("MESSAGE ") + (index + 1) + t(" OF ") + emails.length
                + t("   •   Inspect the pretend email before sorting"));
        board.showEmail(emails[index]);
        showStatus(t("🎀 Pick up the message card and drag it to one of the two inboxes."));
    }

    private void sort(boolean reportAsPhishing) {
        if (finished) return;
        MockEmail email = emails[index];
        String submittedBin = reportAsPhishing ? t("SPAM BIN") : t("SAFE INBOX");
        String correctBin = email.phishing() ? t("SPAM BIN") : t("SAFE INBOX");
        if (email.phishing() == reportAsPhishing) {
            addPoints(10);
            feedback = t("CORRECT — you submitted ") + submittedBin + t(". Correct answer: ")
                    + correctBin + ". "
                    + (email.phishing()
                    ? t("The message uses suspicious sender/link clues. Do not open unexpected links.")
                    : t("This expected message is from a known sender and has no suspicious request."));
            index++;
        } else {
            lives--;
            feedback = t("INCORRECT — you submitted ") + submittedBin + t(". Correct answer: ")
                    + correctBin + ". "
                    + (email.phishing()
                    ? t("The sender or request is suspicious; check misspellings, urgency, and links.")
                    : t("This is an expected message from a known sender. Check context before reporting."));
        }
        showEmail();
    }

    public void refreshLanguage() {
        board.refreshLanguage();
        if (!finished) {
            if (index == 0) feedback = t("Drop into SPAM or SAFE INBOX. Check the sender, link and urgency.");
            showStatus(t(index == 0 && points == 0
                    ? "🎀 Drag each message into the right inbox. You have three helpful retries."
                    : "🎀 Pick up the message card and drag it to one of the two inboxes."));
            if (index < emails.length) showEmail();
        }
    }

    private record MockEmail(String sender, String subject, String preview, boolean phishing) {
    }

    private final class DropBoard extends JPanel {
        private static final long serialVersionUID = 1L;
        private final JPanel emailCard = new JPanel(new GridLayout(0, 1, 0, 6));
        private final JPanel spamBin = dropBin(t("🗑  SPAM BIN"), UiTheme.PINK_PAGE, UiTheme.PINK_INK);
        private final JPanel safeBin = dropBin(t("💌  SAFE INBOX"), UiTheme.SKY_PAGE, UiTheme.BLUE_INK);
        private final JLabel dropHint = new JLabel(t("DRAG MESSAGE TO A BIN"), SwingConstants.CENTER);
        private int messageHomeX;
        private int messageHomeY;

        private DropBoard() {
            setLayout(null);
            setOpaque(false);
            setPreferredSize(new Dimension(700, 290));
            emailCard.setBackground(Color.WHITE);
            emailCard.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UiTheme.PINK_INK, 2),
                    new EmptyBorder(12, 15, 12, 15)));
            installDrag();
            spamBin.addMouseListener(new MouseAdapter() {
                @Override public void mouseReleased(MouseEvent event) { sort(true); }
            });
            safeBin.addMouseListener(new MouseAdapter() {
                @Override public void mouseReleased(MouseEvent event) { sort(false); }
            });
            dropHint.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 12));
            dropHint.setForeground(UiTheme.PINK_INK);
            add(emailCard);
            add(spamBin);
            add(safeBin);
            add(dropHint);
        }

        private JPanel dropBin(String text, Color fill, Color ink) {
            JPanel bin = new JPanel(new GridBagLayout());
            bin.setBackground(fill);
            bin.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(ink, 2),
                    new EmptyBorder(8, 8, 8, 8)));
            JLabel label = new JLabel(text, SwingConstants.CENTER);
            label.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 13));
            label.setForeground(ink);
            bin.add(label);
            return bin;
        }

        private void installDrag() {
            MouseAdapter drag = new MouseAdapter() {
                @Override public void mousePressed(MouseEvent event) {
                    dragOffset = event.getPoint();
                    emailCard.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                }
                @Override public void mouseDragged(MouseEvent event) {
                    if (dragOffset == null) return;
                    Point mouse = SwingUtilities.convertPoint(emailCard, event.getPoint(), DropBoard.this);
                    int x = Math.max(0, Math.min(getWidth() - emailCard.getWidth(), mouse.x - dragOffset.x));
                    int y = Math.max(0, Math.min(getHeight() - emailCard.getHeight(), mouse.y - dragOffset.y));
                    emailCard.setLocation(x, y);
                }
                @Override public void mouseReleased(MouseEvent event) {
                    emailCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    dragOffset = null;
                    if (emailCard.getBounds().intersects(spamBin.getBounds())) sort(true);
                    else if (emailCard.getBounds().intersects(safeBin.getBounds())) sort(false);
                    else emailCard.setLocation(messageHomeX, messageHomeY);
                }
            };
            emailCard.addMouseListener(drag);
            emailCard.addMouseMotionListener(drag);
            emailCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        private void showEmail(MockEmail email) {
            dropHint.setText(t("DRAG MESSAGE TO A BIN"));
            dropHint.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 12));
            dropHint.setForeground(UiTheme.PINK_INK);
            emailCard.removeAll();
            addLine(t("✉   PRETEND EMAIL — safe classroom simulation"), UiTheme.SECONDARY);
            addLine(t("FROM:  ") + email.sender(), UiTheme.TEXT);
            addLine(t("SUBJECT:  ") + t(email.subject()), UiTheme.TEXT);
            addLine(t(email.preview()), UiTheme.MUTED);
            MouseAdapter drag = currentDrag();
            for (Component child : emailCard.getComponents()) {
                child.addMouseListener(drag);
                child.addMouseMotionListener(drag);
            }
            emailCard.setVisible(true);
            emailCard.setLocation(messageHomeX, messageHomeY);
            spamBin.setVisible(true);
            safeBin.setVisible(true);
            dropHint.setVisible(true);
            revalidate();
            repaint();
        }

        private void refreshLanguage() {
            setDropBinLabel(spamBin, t("🗑  SPAM BIN"));
            setDropBinLabel(safeBin, t("💌  SAFE INBOX"));
            dropHint.setText(t("DRAG MESSAGE TO A BIN"));
        }

        private void setDropBinLabel(JPanel bin, String text) {
            for (Component component : bin.getComponents()) {
                if (component instanceof JLabel label) label.setText(text);
            }
        }

        private MouseAdapter currentDrag() {
            return new MouseAdapter() {
                private Point origin;
                @Override public void mousePressed(MouseEvent event) {
                    origin = SwingUtilities.convertPoint(event.getComponent(), event.getPoint(), emailCard);
                }
                @Override public void mouseDragged(MouseEvent event) {
                    if (origin == null) return;
                    Point point = SwingUtilities.convertPoint(event.getComponent(), event.getPoint(), DropBoard.this);
                    emailCard.setLocation(point.x - origin.x, point.y - origin.y);
                }
                @Override public void mouseReleased(MouseEvent event) {
                    if (emailCard.getBounds().intersects(spamBin.getBounds())) sort(true);
                    else if (emailCard.getBounds().intersects(safeBin.getBounds())) sort(false);
                    else emailCard.setLocation(messageHomeX, messageHomeY);
                    origin = null;
                }
            };
        }

        private void addLine(String text, Color ink) {
            JLabel line = new JLabel(text);
            line.setFont(new Font(UiTheme.FONT_FAMILY, Font.PLAIN, 12));
            line.setForeground(ink);
            emailCard.add(line);
        }

        private void showResult(String text, boolean won) {
            emailCard.setVisible(false);
            spamBin.setVisible(false);
            safeBin.setVisible(false);
            dropHint.setText("<html><div style='width:650px;text-align:center'>"
                    + text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                    + "</div></html>");
            dropHint.setFont(new Font(UiTheme.FONT_FAMILY, Font.BOLD, 16));
            dropHint.setForeground(won ? UiTheme.SUCCESS : UiTheme.DANGER);
        }

        @Override public void doLayout() {
            int width = getWidth();
            int height = getHeight();
            int binWidth = Math.max(145, width / 4 - 12);
            int binHeight = 54;
            int binY = Math.max(185, height - binHeight - 12);
            spamBin.setBounds(12, binY, binWidth, binHeight);
            safeBin.setBounds(width - binWidth - 12, binY, binWidth, binHeight);
            int cardWidth = Math.min(510, Math.max(330, width - 2 * binWidth - 55));
            int cardHeight = 130;
            messageHomeX = (width - cardWidth) / 2;
            messageHomeY = 14;
            emailCard.setBounds(messageHomeX, messageHomeY, cardWidth, cardHeight);
            dropHint.setBounds(18, 18, Math.max(1, width - 36), Math.max(1, height - 36));
        }
    }
}
