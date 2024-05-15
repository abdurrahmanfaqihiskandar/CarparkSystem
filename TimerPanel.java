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
    private JLabel timeLabel = new JLabel(); // Initialize view component
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss"); // Format datetime object

    public TimerPanel() {
        add(timeLabel);
        Timer timer = new Timer(1000, e -> this.timerListener()); // Create timer with 1000ms delay
        timer.setInitialDelay(0); // Set initial delay to 0
        timer.start(); // Start timer
    }

    /**
     * Action listener for timer
     */
    public void timerListener() {
        LocalDateTime now = LocalDateTime.now(); // Get time now
        String timeString = now.format(FORMATTER); // Format time
        this.timeLabel.setText("Current time: " + timeString); // Set label text
    }
}
