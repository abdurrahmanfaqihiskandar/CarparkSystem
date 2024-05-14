import javax.swing.*;
import java.awt.*;

/**
 * Project 2: ResetCarParkDialog class extends JDialog.
 * Shows info on reset car park action.
 * Stores reference to controller CarparkSystem, action buttons and label.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class ResetCarParkDialog extends JDialog {
    private CarparkSystem controller;
    private JButton resetButton = new JButton("Reset");
    private JButton closeButton = new JButton("Cancel");
    private JLabel resetMessage;

    public ResetCarParkDialog(CarparkSystem controller, JFrame parent, String message) {
        super(parent, "Reset Carpark", true);
        this.controller = controller;

        setLayout(new FlowLayout());

        // Add label
        resetMessage = new JLabel(message);
        add(resetMessage);

        // Add reset button
        this.resetButton.addActionListener(e -> DialogActionListeners.resetCarParkActionListener(this.controller));
        add(resetButton);

        // Add close button
        this.closeButton.addActionListener(e -> this.setVisible(false));
        add(this.closeButton);

        this.pack();
    }
}
