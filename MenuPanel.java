import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project 2: MenuPanel class that extends JPanel.
 * Stores reference to parent class, and the different buttons that make up the menu
 * of the GUI.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class MenuPanel extends JPanel {
    private CarparkSystem controller;
    private MenuButton addParkingSpotBtn = new MenuButton("Add Parking Spot", e -> controller.openAddParkingSpotDialog());
    private MenuButton delParkingSpotBtn = new MenuButton("Delete Parking Spot", e -> controller.openDeleteParkingSpotDialog());
    private MenuButton listSpotsBtn = new MenuButton("List Parking Spots", e -> controller.listParkingSpots());
    private MenuButton parkCarBtn = new MenuButton("Park A Car", e -> controller.openParkCarDialog());
    private MenuButton findCarByRegNoBtn = new MenuButton("Find Car By Reg Number", e -> controller.openFindCarByRegNoDialog());
    private MenuButton removeCarByRegNoBtn = new MenuButton("Remove Car By Reg Number", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.openAddParkingSpotDialog();
        }
    });
    private MenuButton findCarByMakeBtn = new MenuButton("Find Car By Make", e -> controller.openFindCarByMakeDialog());
    private MenuButton resetCarparkBtn = new MenuButton("Reset Carpark", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.openAddParkingSpotDialog();
        }
    });
    private MenuButton exitBtn = new MenuButton("Exit Program", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.openAddParkingSpotDialog();
        }
    });

    public MenuPanel(CarparkSystem controller) {
        this.controller = controller;
        this.setBackground(new Color(32, 32, 32));
        this.setBorder(BorderFactory.createLineBorder(new Color(71, 71, 71), 2));
        this.add(addParkingSpotBtn);
        this.add(delParkingSpotBtn);
        this.add(listSpotsBtn);
        this.add(parkCarBtn);
        this.add(findCarByRegNoBtn);
        this.add(removeCarByRegNoBtn);
        this.add(findCarByMakeBtn);
        this.add(resetCarparkBtn);
        this.add(exitBtn);
        this.setLayout(new GridLayout(9, 1));
    }
}