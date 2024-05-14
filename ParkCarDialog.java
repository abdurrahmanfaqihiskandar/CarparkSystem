import javax.swing.*;
import java.awt.*;

/**
 * Project 2: ParkCarDialog class extends JDialog.
 * Dialog component for parking a car command.
 * Stores parent frame, action buttons, form labels and inputs.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class ParkCarDialog extends JDialog {
    private CarparkSystem controller;
    private JFrame parent;
    private JButton parkButton = new JButton("Park car");
    private JButton closeButton = new JButton("Cancel");
    private JLabel spotIdLabel = new JLabel("Spot ID: ");
    private JLabel carRegLabel = new JLabel("Car registration number: ");
    private JLabel carMakeLabel = new JLabel("Car make: ");
    private JLabel carModelLabel = new JLabel("Car model: ");
    private JLabel carYearLabel = new JLabel("Car year: ");
    private JTextField spotIdTextField;
    private JTextField carRegTextField = new JTextField(10);
    private JTextField carMakeTextField = new JTextField(10);
    private JTextField carModelTextField = new JTextField(10);
    private JTextField carYearTextField = new JTextField(10);

    public ParkCarDialog(CarparkSystem controller, JFrame parent, String spotId) {
        super(parent, "Park a car", true);
        this.controller = controller;
        this.parent = parent;

        // Initialize spot ID text field
        spotIdTextField = new JTextField(spotId, 10);

        // Set layout of form
        setLayout(new GridLayout(0, 2));
        add(this.spotIdLabel);
        add(this.spotIdTextField);
        add(this.carRegLabel);
        add(this.carRegTextField);
        add(this.carMakeLabel);
        add(this.carMakeTextField);
        add(this.carModelLabel);
        add(this.carModelTextField);
        add(this.carYearLabel);
        add(this.carYearTextField);
        this.parkButton.addActionListener(e -> this.parkCarListener());
        add(this.parkButton);
        this.closeButton.addActionListener(e -> this.setVisible(false));
        add(this.closeButton);

        this.pack();
    }

    /**
     * Handles park car button click. Gets text input into text field by user
     * and validates the input. If there is an input error, the error dialog is shown.
     */
    public void parkCarListener() {
        // Extract values
        String userInputId = this.spotIdTextField.getText();
        String carRegNo = this.carRegTextField.getText();
        String carMake = this.carMakeTextField.getText();
        String carModel = this.carModelTextField.getText();
        String carYear = this.carYearTextField.getText();

        // Validate user inputs
        try {
            boolean isIdValid = Validators.validateParkingSpotId(userInputId); // Validate parking spot ID
            if (!isIdValid) {
                MessageDialog messageDialog = new MessageDialog(this.parent,
                        "Parking spot ID must be an uppercase letter followed by 3 digits.", MessageDialog.ERROR);
                messageDialog.setLocationRelativeTo(this.parent);
                messageDialog.setVisible(true);
                return;
            }
            boolean isCarRegValid = Validators.validateCarRegNo(carRegNo); // Validate car reg number
            if (!isCarRegValid) {
                MessageDialog messageDialog = new MessageDialog(this.parent,
                        "Invalid car registration number. Car registration number must be an uppercase letter followed by 4 digits.", MessageDialog.ERROR);
                messageDialog.setLocationRelativeTo(this.parent);
                messageDialog.setVisible(true);
                return;
            }
            if (carMake.isEmpty()) {
                MessageDialog messageDialog = new MessageDialog(this.parent,
                        "Car make cannot be empty.", MessageDialog.ERROR);
                messageDialog.setLocationRelativeTo(this.parent);
                messageDialog.setVisible(true);
                return;
            }
            if (carModel.isEmpty()) {
                MessageDialog messageDialog = new MessageDialog(this.parent,
                        "Car model cannot be empty.", MessageDialog.ERROR);
                messageDialog.setLocationRelativeTo(this.parent);
                messageDialog.setVisible(true);
                return;
            }
            int carYearInt;
            carYearInt = Integer.parseInt(carYear); // Parse car year into integer
            boolean isCarYearValid = Validators.validateCarYear(carYearInt); // Validate car year
            if (!isCarYearValid) {
                MessageDialog messageDialog = new MessageDialog(this.parent,
                        "Invalid car year. Car year must be an integer between 2004-2024.", MessageDialog.ERROR);
                messageDialog.setLocationRelativeTo(this.parent);
                messageDialog.setVisible(true);
                return;
            }

            // Create car
            Car car = new Car(carRegNo, carMake, carModel, carYearInt);
            this.controller.parkCarHandler(userInputId, car);

            // Reset fields
            this.spotIdTextField.setText("");
            this.carRegTextField.setText("");
            this.carModelTextField.setText("");
            this.carMakeTextField.setText("");
            this.carYearTextField.setText("");

            this.spotIdTextField.grabFocus();
        } catch (NumberFormatException err) {
            MessageDialog messageDialog = new MessageDialog(this.parent,
                    "Invalid car year. Car year must be an integer between 2004-2024.", MessageDialog.ERROR);
            messageDialog.setLocationRelativeTo(this.parent);
            messageDialog.setVisible(true);
        }
    }
}
