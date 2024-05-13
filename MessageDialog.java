import javax.swing.*;
import java.awt.*;

/**
 * Project 2: MessageDialog class extends JDialog.
 * Shows general messages to user for information.
 * Stores message string and close action button.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class MessageDialog extends JDialog {
    private JButton closeButton = new JButton("Close");
    public static final String ERROR = "Error";
    public static final String INFO = "Info";

    public MessageDialog(JFrame parent, String message, String type) {
        super(parent, type, true);

        setLayout(new FlowLayout());

        JLabel msg = new JLabel(message);
        add(msg);

        this.closeButton.addActionListener(e -> this.setVisible(false));
        add(closeButton);

        this.pack();
    }
}
