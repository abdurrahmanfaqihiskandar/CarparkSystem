import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * Project 2: ParkingSpotView class that extends JButton.
 * Stores reference to controller CarparkSystem and model of parkingSpot.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class ParkingSpotView extends JButton {
    private CarparkSystem controller; // Store reference to controller

    // Initialize view components
    private ParkingSpot parkingSpot;
    private JLabel parkingSpotId = new JLabel();
    private JLabel parkedCarDetails = new JLabel();

    public ParkingSpotView(CarparkSystem controller, ParkingSpot parkingSpot) {
        this.controller = controller;
        this.parkingSpot = parkingSpot;

        // Set view settings
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBackground(new Color(137, 232, 138));
        setBorder(BorderFactory.createLineBorder(new Color(79, 79, 82), 3, true));
        setLayout(new BorderLayout());
        setOpaque(true);

        // Add components
        parkingSpotId.setText(parkingSpot.getId());
        parkingSpotId.setHorizontalAlignment(SwingConstants.CENTER);
        add(parkingSpotId, BorderLayout.NORTH);

        // Add action listener to parking spot
        this.addActionListener(e -> this.clickActionListener());
    }

    /**
     * Gets parking spot model and returns it
     * @return parking spot
     */
    public ParkingSpot getParkingSpot() {
        return this.parkingSpot;
    }

    /**
     * Update parking spot view with changes in parking spot model
     */
    public void updateParkingSpotView() {
        if (parkingSpot.isOccupied()) {
            // Add car details to parking spot view
            Car parkedCar = parkingSpot.getParkedCar(); // Get parked car
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            parkedCarDetails.setText("<html>Registration: " + parkedCar.getRegNo() + "<br />"
                    + parkedCar.getMake() + " " + parkedCar.getModel() + " " + parkedCar.getYear()
                    + "<br />Car parked at: " + parkedCar.getTimeStart().format(formatter) + "</html>");
            parkedCarDetails.setHorizontalAlignment(SwingConstants.CENTER);
            add(parkedCarDetails, BorderLayout.CENTER);
            setBackground(new Color(242, 90, 90));
        } else {
            // Remove car details from parking spot view
            remove(parkedCarDetails);
            setBackground(new Color(137, 232, 138));
        }
    }

    /**
     * Action listener for button clicks on parking spot view
     */
    public void clickActionListener() {
        this.controller.openParkingSpotViewActionDialog(this);
    }
}
