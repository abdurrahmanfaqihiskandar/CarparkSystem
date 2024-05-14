import javax.swing.*;
import java.awt.*;

/**
 * Project 2: ActionDialog class extends JDialog.
 * General dialog component for car park actions.
 * Stores reference to controller CarparkSystem, action buttons and label.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class ActionDialog extends JDialog {
    private CarparkSystem controller;
    private JButton actionButton;
    private JButton closeButton = new JButton("Cancel");
    private JLabel resetMessage;
    public static final String RESET = "Reset";
    public static final String EXIT = "Exit";

    public ActionDialog(CarparkSystem controller, JFrame parent, String title, String action, String message) {
        super(parent, title, true);
        this.controller = controller;

        setLayout(new FlowLayout());

        // Add label
        resetMessage = new JLabel(message);
        add(resetMessage);

        // Add reset button
        actionButton = new JButton(action);
        if (action.equals(ActionDialog.RESET)) {
            this.actionButton.addActionListener(e -> DialogActionListeners.resetCarParkActionListener(this.controller));
        } else {
            this.actionButton.addActionListener(e -> DialogActionListeners.exitProgramActionListener(this.controller));
        }
        add(actionButton);

        // Add close button
        this.closeButton.addActionListener(e -> this.setVisible(false));
        add(this.closeButton);

        this.pack();
    }
}
