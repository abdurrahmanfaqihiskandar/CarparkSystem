import javax.swing.*;
import java.awt.*;

/**
 * Project 2: FormDialog class extends JDialog.
 * General dialog component for dialogs that requires a field input.
 * Stores parent frame, action buttons, form label and input.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class FormDialog extends JDialog {
    private CarparkSystem controller;
    private JFrame parent;
    private JButton actionButton;
    private JButton closeButton = new JButton("Cancel");
    private JLabel formLabel;
    private JTextField formTextField = new JTextField(10);

    public FormDialog(CarparkSystem controller, JFrame parent, String title, String action, String formLabel) {
        super(parent, title, true);
        this.controller = controller;
        this.parent = parent;
        this.actionButton = new JButton(action);
        this.formLabel = new JLabel(formLabel);

        // Set layout of form
        setLayout(new GridLayout(0, 2));

        add(this.formLabel);
        add(this.formTextField);

        // Add action listener to action button
        switch (action) {
            case "Add":
                this.actionButton.addActionListener(e -> DialogActionListeners.addActionListener(this.formTextField, this.parent, this.controller));
                break;
            case "Delete":
                this.actionButton.addActionListener(e -> DialogActionListeners.deleteActionListener(this.formTextField, this.parent, this.controller));
                break;
            case "Find by registration number":
                this.actionButton.addActionListener(e -> DialogActionListeners.findByRegNoActionListener(this.formTextField, this.parent, this.controller));
                break;
            case "Find by make":
                this.actionButton.addActionListener(e -> DialogActionListeners.findByMakeActionListener(this.formTextField, this.parent, this.controller));
                break;
        }
        add(this.actionButton);

        // Add action listener to close button
        this.closeButton.addActionListener(e -> this.setVisible(false));
        add(this.closeButton);

        this.pack();
    }
}
