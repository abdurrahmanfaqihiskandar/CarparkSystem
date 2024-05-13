import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Project 2: MenuButton class that extends JButton.
 * Stores attributes of id and parkedCar,
 * getter methods for its attributes, and methods to add and remove a car.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class MenuButton extends JButton {
    public MenuButton(String text, ActionListener actionListener) {
        super(text);

        // Set view settings
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBackground(new Color(62, 61, 69));
        setForeground(Color.white);
        setOpaque(true);
        setBorderPainted(false);
        setFocusPainted(false);

        // Hover effect
        addMouseListener(new MouseAdapter() {
            JButton button;
            @Override
            public void mouseEntered(MouseEvent e) {
                button = (JButton) e.getSource();
                button.setBackground(new Color(79, 79, 82));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(62, 61, 69));
            }
        });
        addActionListener(actionListener);
    }
}
