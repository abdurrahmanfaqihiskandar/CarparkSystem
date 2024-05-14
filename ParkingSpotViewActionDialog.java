import javax.swing.*;
import java.awt.*;

/**
 * Project 2: ParkingSpotViewActionDialog class extends JDialog.
 * Dialog component for parking spot view user clicks.
 * Stores reference to controller CarparkSystem, source parking spot view and action buttons.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class ParkingSpotViewActionDialog extends JDialog {
    private CarparkSystem controller;
    private ParkingSpotView source;
    private JButton addCarButton = new JButton("Add car");
    private JButton removeCarButton = new JButton("Remove car");
    private JButton removeParkingSpotButton = new JButton("Remove parking spot");
    private JButton closeButton = new JButton("Cancel");

    public ParkingSpotViewActionDialog(CarparkSystem controller, JFrame parent, ParkingSpotView source) {
        super(parent, "Parking spot actions", true);
        this.controller = controller;
        this.source = source;

        setLayout(new FlowLayout());

        // Check if parking spot is occupied
        if (source.getParkingSpot().isOccupied()) {
            // If parking spot is occupied, only add remove car button
            this.removeCarButton.addActionListener(e -> this.removeCarActionListener());
            add(removeCarButton);
        } else {
            // else, add add car button and remove parking spot button
            this.addCarButton.addActionListener(e -> this.addCarActionListener());
            add(addCarButton);
            this.removeParkingSpotButton.addActionListener(e -> this.removeParkingSpotActionListener());
            add(removeParkingSpotButton);
        }
        this.closeButton.addActionListener(e -> this.setVisible(false));
        add(closeButton);

        this.pack();
    }

    /**
     * Action listener for add car button
     */
    public void addCarActionListener() {
        this.setVisible(false);
        this.controller.openParkCarDialog();
    }

    /**
     * Action listener for remove car button
     */
    public void removeCarActionListener() {
        String carRegNo = this.source.getParkingSpot().getParkedCar().getRegNo();
        this.setVisible(false);
        this.controller.removeCarByRegNoHandler(carRegNo);
    }

    /**
     * Action listener for remove parking spot button
     */
    public void removeParkingSpotActionListener() {
        String parkingSpotId = this.source.getParkingSpot().getId();
        this.setVisible(false);
        this.controller.deleteParkingSpotHandler(parkingSpotId);
    }
}
