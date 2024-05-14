import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Project 2: CarparkView class that extends JPanel.
 * Stores list of parkingSpotViews.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class CarparkView extends JPanel {
    private ArrayList<ParkingSpotView> parkingSpotViews = new ArrayList<ParkingSpotView>();

    public CarparkView() {
        // Set view settings
        this.setBackground(new Color(240, 242, 245));
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.setLayout(new GridLayout(0, 4));
    }

    /**
     * Create a new parking spot view from a parking spot.
     * @param parkingSpot newly created parking spot
     */
    public void addParkingSpotView(ParkingSpot parkingSpot) {
        // Create parking spot view
        ParkingSpotView parkingSpotView = new ParkingSpotView(parkingSpot);

        // Add parking spot view into array list
        parkingSpotViews.add(parkingSpotView);

        // Add new parking spot view into CarparkView
        this.add(parkingSpotView);
        revalidate();
    }

    /**
     * Delete parking spot view.
     * @param spotId parking spot to delete
     */
    public void deleteParkingSpotView(String spotId) {
        // Iterator of parking spot view
        Iterator<ParkingSpotView> it = this.parkingSpotViews.iterator();
        while(it.hasNext()) {
            ParkingSpotView current = it.next(); // Assign parking spot view as current
            // only remove parking spot if
            // parking spot matches the id
            if (current.getParkingSpot().getId().equals(spotId)) {
                this.remove(current); // Remove parking spot view from carpark view
                it.remove(); // Remove parking spot view from array list
                revalidate();
                repaint();
                break;
            }
        }
    }

    /**
     * Update parking spot view.
     * @param spotId parking spot to update
     */
    public void updateParkingSpotView(String spotId) {
        // Iterate through parking spots
        for (ParkingSpotView current : parkingSpotViews) {
            if (current.getParkingSpot().getId().equals(spotId)) {
                current.updateParkingSpotView();
                revalidate();
                repaint();
                break;
            }
        }
    }

    /**
     * Reset parking spot views
     */
    public void resetParkingSpotViews() {
        // Iterate through parking spot views and update the view
        for (ParkingSpotView current : parkingSpotViews) {
            current.updateParkingSpotView();
        }
        revalidate();
        repaint();
    }
}
