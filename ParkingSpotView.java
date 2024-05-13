import javax.swing.*;
import java.awt.*;

/**
 * Project 2: ParkingSpotView class that extends JButton.
 * Stores model of parkingSpot.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class ParkingSpotView extends JButton {
    private ParkingSpot parkingSpot;
    private JLabel parkingSpotId = new JLabel();
    private JLabel parkedCarDetails = new JLabel();

    public ParkingSpotView(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;

        // Set view settings
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBackground(Color.GREEN);
        setBorder(BorderFactory.createLineBorder(new Color(79, 79, 82), 1, true));
        setLayout(new BorderLayout());
        setOpaque(true);

        // Add components
        parkingSpotId.setText(parkingSpot.getId());
        add(parkingSpotId, BorderLayout.NORTH);
    }

    /**
     * Gets parking spot model and returns it
     * @return parking spot
     */
    public ParkingSpot getParkingSpot() {
        return this.parkingSpot;
    }

    /**
     * Update parking spot view with parked car in model
     */
    public void updateParkingSpotView() {
        Car parkedCar = parkingSpot.getParkedCar();
        parkedCarDetails.setText("<html>Registration: " + parkedCar.getRegNo() + "<br />"
                + parkedCar.getMake() + " " + parkedCar.getModel() + " " + parkedCar.getYear() + "</html>");
        add(parkedCarDetails, BorderLayout.CENTER);
//        revalidate();
    }
}
