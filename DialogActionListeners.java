import javax.swing.*;

/** Project 2: DialogActionListeners class stores the different
 * action listeners to be used in the application dialogs.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class DialogActionListeners {
    /**
     * Handles add button click. Gets text input from text field by user
     * and validates the input. If there is an input error, the error dialog is shown.
     * @param formTextField form text field
     * @param dialogParent parent of dialog component
     * @param controller controller class
     */
    public static void addActionListener(JTextField formTextField, JFrame dialogParent, CarparkSystem controller) {
        String userInputId = formTextField.getText(); // Get user input id

        // Check if spot ID is valid
        boolean isIdValid = Validators.validateParkingSpotId(userInputId);
        if (!isIdValid) {
            // Show error if invalid
            MessageDialog messageDialog = new MessageDialog(dialogParent,
                    "Parking spot ID must be an uppercase letter followed by 3 digits.", MessageDialog.ERROR);
            messageDialog.setLocationRelativeTo(dialogParent);
            messageDialog.setVisible(true);
        } else {
            // Call handler
            controller.addParkingSpotHandler(userInputId);
        }

        // Reset fields
        formTextField.setText("");
        formTextField.grabFocus();
    }

    /**
     * Handles delete button click. Gets text input from text field by user
     * and validates the input. If there is an input error, the error dialog is shown.
     * @param formTextField form text field
     * @param dialogParent parent of dialog component
     * @param controller controller class
     */
    public static void deleteActionListener(JTextField formTextField, JFrame dialogParent, CarparkSystem controller) {
        String userInputId = formTextField.getText(); // Get user input id

        // Check if spot ID is valid
        boolean isIdValid = Validators.validateParkingSpotId(userInputId);
        if (!isIdValid) {
            // Show error if invalid
            MessageDialog messageDialog = new MessageDialog(dialogParent,
                    "Parking spot ID must be an uppercase letter followed by 3 digits.", MessageDialog.ERROR);
            messageDialog.setLocationRelativeTo(dialogParent);
            messageDialog.setVisible(true);
        } else {
            // Call handler
            controller.deleteParkingSpotHandler(userInputId);
        }

        // Reset fields
        formTextField.setText("");
        formTextField.grabFocus();
    }

    /**
     * Handles find by registration number button click. Gets text input from text field by user
     * and validates the input. If there is an input error, the error dialog is shown.
     * @param formTextField form text field
     * @param dialogParent parent of dialog component
     * @param controller controller class
     */
    public static void findByRegNoActionListener(JTextField formTextField, JFrame dialogParent, CarparkSystem controller) {
        String userInputCarRegNo = formTextField.getText(); // Get user input reg number

        // Check if car reg number is valid
        boolean isIdValid = Validators.validateCarRegNo(userInputCarRegNo);
        if (!isIdValid) {
            // Show error if invalid
            MessageDialog messageDialog = new MessageDialog(dialogParent,
                    "Invalid car registration number. Car registration number must be an uppercase letter followed by 4 digits.", MessageDialog.ERROR);
            messageDialog.setLocationRelativeTo(dialogParent);
            messageDialog.setVisible(true);
        } else {
            // Call handler
            controller.findCarByRegNoHandler(userInputCarRegNo);
        }
        // Reset fields
        formTextField.setText("");
        formTextField.grabFocus();
    }

    /**
     * Handles find by make button click. Gets text input from text field by user
     * and validates the input. If there is an input error, the error dialog is shown.
     * @param formTextField form text field
     * @param dialogParent parent of dialog component
     * @param controller controller class
     */
    public static void findByMakeActionListener(JTextField formTextField, JFrame dialogParent, CarparkSystem controller) {
        String userInputMake = formTextField.getText(); // Get user input make

        // Check if user input is empty
        if (userInputMake.isEmpty()) {
            // Show error if empty
            MessageDialog messageDialog = new MessageDialog(dialogParent,
                    "Car make cannot be empty.", MessageDialog.ERROR);
            messageDialog.setLocationRelativeTo(dialogParent);
            messageDialog.setVisible(true);
        } else {
            // Call handler
            controller.findCarByMakeHandler(userInputMake);
        }
        // Reset fields
        formTextField.setText("");
        formTextField.grabFocus();
    }
}
