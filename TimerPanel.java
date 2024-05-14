import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Project 2: TimerPanel class that extends JPanel.
 * Shows the current time on the application.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class TimerPanel extends JPanel {
    private JLabel timeLabel = new JLabel();
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public TimerPanel() {
        add(timeLabel);
        Timer timer = new Timer(1000, e -> this.timerListener());
        timer.setInitialDelay(0);
        timer.start();
    }

    public void timerListener() {
        LocalDateTime now = LocalDateTime.now();
        String timeString = now.format(FORMATTER);
        this.timeLabel.setText("Current time: " + timeString);
    }
}
