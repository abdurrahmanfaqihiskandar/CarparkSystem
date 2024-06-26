import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Project 2: CarparkSystem class. Acts as the controller of the application.
 * Stores carPark, frame of GUI, carparkView, menuPanel and the form dialogs.
 * Main class which starts the GUI is defined in this class.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class CarparkSystem {
    // Initialize carpark model
    private CarPark carPark = new CarPark();

    // Initialize view components
    private JFrame frame = new JFrame("Carpark");
    private CarparkView carparkView = new CarparkView(this);
    private MenuPanel menuPanel = new MenuPanel(this);
    private TimerPanel timerPanel = new TimerPanel();

    // Initialize dialogs
    private FormDialog addParkingSpotDialog = new FormDialog(this, frame, "Add parking spot", "Add", "Enter spot ID: ");
    private FormDialog deleteParkingSpotDialog = new FormDialog(this, frame, "Delete parking spot", "Delete",
            "Enter spot ID: ");
    private ParkCarDialog parkCarDialog;
    private FormDialog findCarByRegNoDialog = new FormDialog(this, frame, "Find car by reg number",
            "Find by registration number", "Enter registration number: ");
    private FormDialog removeCarByRegNoDialog = new FormDialog(this, frame, "Remove car by reg number",
            "Remove by registration number", "Enter registration number: ");
    private FormDialog findCarByMakeDialog = new FormDialog(this, frame, "Find car by make", "Find by make",
            "Enter make: ");
    private ActionDialog resetCarParkDialog;
    private ActionDialog exitProgramDialog = new ActionDialog(this, frame, "Exit program", ActionDialog.EXIT,
            "Program ends!");
    private ParkingSpotViewActionDialog parkingSpotViewActionDialog;

    public CarparkSystem() {
        frame.setSize(1000, 500); // Set dimensions of frame

        frame.add(menuPanel, BorderLayout.WEST); // Place menu panel on the left side of frame
        frame.add(carparkView, BorderLayout.CENTER); // Place carpark panel on the right side of the frame
        frame.add(timerPanel, BorderLayout.NORTH); // Place timer panel on the top side of the frame

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // Show frame
    }

    /**
     * Handles opening the add parking spot dialog
     */
    public void openAddParkingSpotDialog() {
        addParkingSpotDialog.setLocationRelativeTo(frame); // Center dialog in the middle of the frame
        addParkingSpotDialog.setVisible(true); // Show dialog
    }

    /**
     * Handles adding a parking spot.
     * CarparkSystem will pass user input to CarPark model
     * and the newly created parking spot to carpark view.
     * 
     * @param spotId id input from dialog text field
     */
    public void addParkingSpotHandler(String spotId) {
        // Add parking spot to carpark
        ParkingSpot parkingSpot = carPark.addParkingSpot(spotId);
        if (parkingSpot == null) {
            // If there is no parking spot returned, show the error
            String message = "Each spot should have a unique spot number.";
            showInfo(message, "Error");
            return; // To not close the dialog
        } else {
            // else create a parking spot view
            carparkView.addParkingSpotView(parkingSpot);
        }
        addParkingSpotDialog.setVisible(false); // Close add parking spot dialog
    }

    /**
     * Handles opening the delete parking spot dialog
     */
    public void openDeleteParkingSpotDialog() {
        deleteParkingSpotDialog.setLocationRelativeTo(frame); // Center dialog in the middle of the frame
        deleteParkingSpotDialog.setVisible(true); // Show dialog
    }

    /**
     * Handles deleting a parking spot.
     * CarparkSystem will pass user input to CarPark model
     * to delete parking spot.
     * 
     * @param spotId id input from dialog text field
     */
    public void deleteParkingSpotHandler(String spotId) {
        // Delete parking spot
        String message = carPark.deleteParkingSpot(spotId);

        if (!message.equals("Parking spot deleted successfully.")) {
            // If deletion is not successful, show error
            showInfo(message, "Error");
            return; // To not close the dialog
        } else {
            // else delete parking spot view
            // and show info dialog
            carparkView.deleteParkingSpotView(spotId);
            showInfo(message, "Info");
        }
        deleteParkingSpotDialog.setVisible(false); // Close delete parking spot dialog
    }

    /**
     * Prints id, whether occupied, and if occupied, car's registration, make
     * and duration of parking of all spots in a well-defined format.
     * Also prints summary of total spots, number of occupied spots and number
     * of unoccupied spots in car park
     */
    public void listParkingSpots() {
        String message; // Initialize message

        // Get summary info
        ArrayList<ParkingSpot> parkingSpots = this.carPark.getParkingSpots();
        int carParkSize = parkingSpots.size(); // Total number of parking spots
        int noUnoccupiedSpots = this.carPark.getNoUnoccupiedSpots();
        int noOccupiedSpots = carParkSize - noUnoccupiedSpots; // Calculate number of occupied spots

        // Add summary info
        message = "<html>Summary<br />=====================<br />";
        message = message + "Total number of parking spots: " + carParkSize + "<br />Occupied spots: "
                + noOccupiedSpots + "<br />Unoccupied spots: " + noUnoccupiedSpots;

        // Add info about parking spots
        if (!parkingSpots.isEmpty()) {
            message += "<br /><br />List of parking spots<br />=====================";

            // Iterate through parking spots
            for (ParkingSpot current : parkingSpots) {
                // Loop through parking spots and print out
                // spot id, whether it is occupied,
                // if occupied, registration and make of car
                message = message + "<br />Spot ID: " + current.getId() + ", " + (current.isOccupied()
                        ? "Parked car: " + current.getParkedCar().getRegNo() + " " + current.getParkedCar().getMake()
                                + "<br />Duration of parking: " + current.getParkedCar().getDurationOfParking()
                                + "<br />"
                        : "Empty");
            }
        }

        // Close html tag
        message += "</html>";

        // Show message to user
        showInfo(message, "Info");
    }

    /**
     * Handles opening the select parking spot dialog
     */
    public void openParkCarDialog(String spotId) {
        parkCarDialog = new ParkCarDialog(this, frame, spotId);
        parkCarDialog.setLocationRelativeTo(frame); // Center dialog in the middle of the frame
        parkCarDialog.setVisible(true); // Show dialog
    }

    /**
     * Handles parking a car in a parking spot.
     * CarparkSystem will pass spot ID and car to CarPark model
     * to park car in parking spot.
     * 
     * @param spotId user input from dialog text field
     */
    public void parkCarHandler(String spotId, Car car) {
        // Park car
        String message = this.carPark.parkCarInSpot(spotId, car);

        if (!message.equals("Car parked successfully")) {
            // If parking is not successful, show error
            showInfo(message, "Error");
        } else {
            // else update parking spot view
            // and show info dialog
            carparkView.updateParkingSpotView(spotId);
            showInfo(message, "Info");
        }
        parkCarDialog.setVisible(false); // Close dialog
    }

    /**
     * Handles opening the find car by registration number dialog
     */
    public void openFindCarByRegNoDialog() {
        findCarByRegNoDialog.setLocationRelativeTo(frame); // Center dialog in the middle of the frame
        findCarByRegNoDialog.setVisible(true); // Show dialog
    }

    /**
     * Handles finding car by registration number.
     * CarparkSystem will pass registration number to CarPark model
     * to find car by registration number.
     * 
     * @param regNo user input from dialog text field
     */
    public void findCarByRegNoHandler(String regNo) {
        String result = carPark.findCarByRegNo(regNo); // Find car
        if (result.equals("Car not parked here.")) {
            // If car is not found, show error
            showInfo(result, "Error");
            return; // To not close the dialog
        } else {
            // Show result
            showInfo(result, "Info");
        }
        findCarByRegNoDialog.setVisible(false);
    }

    /**
     * Handles opening the remove car by registration number dialog
     */
    public void openRemoveCarByRegNoDialog() {
        removeCarByRegNoDialog.setLocationRelativeTo(frame); // Center dialog in the middle of the frame
        removeCarByRegNoDialog.setVisible(true); // Show dialog
    }

    /**
     * Handles removing car by registration number.
     * CarparkSystem will pass registration number to CarPark model
     * to remove car by registration number.
     * 
     * @param regNo user input from dialog text field
     */
    public void removeCarByRegNoHandler(String regNo) {
        String result = carPark.removeCarByRegNo(regNo); // Remove car
        if (result.equals("Car not parked here.")) {
            // If car is not found, show error
            showInfo(result, "Error");
            return; // To not close the dialog
        } else {
            // Update parking spot view
            carparkView.updateParkingSpotView(result);
            // Show result
            showInfo("Car with registration number " + regNo + " removed from spot " + result, "Info");
        }
        removeCarByRegNoDialog.setVisible(false);
    }

    /**
     * Handles opening the find car by make dialog
     */
    public void openFindCarByMakeDialog() {
        findCarByMakeDialog.setLocationRelativeTo(frame); // Center dialog in the middle of the frame
        findCarByMakeDialog.setVisible(true); // Show dialog
    }

    /**
     * Handles closing the find car by make dialog.
     * CarparkSystem will pass make string to CarPark model
     * to find car by make.
     * 
     * @param make user input from dialog text field
     */
    public void findCarByMakeHandler(String make) {
        ArrayList<String> result = carPark.findCarByMake(make);
        if (result.isEmpty()) {
            // If no car is found, show error
            showInfo("No cars found with make " + make, "Error");
            return; // To not close the dialog
        } else {
            // Show result
            String message = "<html>" + result.size() + " car(s) found with make " + make
                    + "<br />====================="; // Initialize message
            // Iterate through array list and print result
            for (String current : result) {
                message = message + "<br />" + current + "<br />";
            }

            // Close html tag
            message += "</html>";

            // Show message to user
            showInfo(message, "Info");
        }
        findCarByMakeDialog.setVisible(false);
    }

    /**
     * Handles opening reset car park dialog
     */
    public void openResetCarparkDialog() {
        // Get number of cars in carpark
        int numberOfCarsParked = this.carPark.getParkingSpots().size() - this.carPark.getNoUnoccupiedSpots();

        // Initialize message
        String message = numberOfCarsParked + " car(s) is being removed to reset the carpark.";

        // Open reset car park dialog
        resetCarParkDialog = new ActionDialog(this, frame, "Reset carpark", "Reset", message); // Create dialog
        resetCarParkDialog.setLocationRelativeTo(frame); // Center dialog in the middle of the frame
        resetCarParkDialog.setVisible(true); // Show dialog
    }

    /**
     * Handles resetting car park
     */
    public void resetCarparkHandler() {
        // Reset carpark model
        this.carPark.resetCarPark();

        // Update carpark view
        this.carparkView.resetParkingSpotViews();

        // Close dialog
        resetCarParkDialog.setVisible(false);
    }

    /**
     * Handles opening reset car park dialog
     */
    public void openExitProgramDialog() {
        this.exitProgramDialog.setLocationRelativeTo(frame); // Center dialog in the middle of the frame
        this.exitProgramDialog.setVisible(true);
    }

    /**
     * Function to exit the program
     */
    public void exitProgramHandler() {
        System.exit(0); // Exit the program
    }

    /**
     * Handles opening parking spot view dialog
     */
    public void openParkingSpotViewActionDialog(ParkingSpotView source) {
        this.parkingSpotViewActionDialog = new ParkingSpotViewActionDialog(this, frame, source);
        this.parkingSpotViewActionDialog.setLocationRelativeTo(frame); // Center dialog in the middle of the frame
        this.parkingSpotViewActionDialog.setVisible(true);
    }

    /**
     * Helper function to create a message dialog.
     * 
     * @param message message to inform user
     * @param type    type of message
     */
    public void showInfo(String message, String type) {
        MessageDialog messageDialog;
        if (type.equals(MessageDialog.ERROR)) {
            messageDialog = new MessageDialog(frame,
                    message, MessageDialog.ERROR);
        } else {
            messageDialog = new MessageDialog(frame,
                    message, MessageDialog.INFO);
        }
        messageDialog.setLocationRelativeTo(frame);
        messageDialog.setVisible(true);
    }

    /**
     * Main function to run the application
     * 
     * @param args arguments passed to application at execution
     */
    public static void main(String[] args) {
        new CarparkSystem(); // Start GUI
    }
}
