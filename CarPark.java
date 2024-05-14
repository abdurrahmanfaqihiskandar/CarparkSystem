import java.util.ArrayList;
import java.util.Iterator;

/**
 * Project 2: CarPark class to manage a list of parking spots.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 10 May 2024
 */

public class CarPark {
    private ArrayList<ParkingSpot> parkingSpots;
    private int noUnoccupiedSpots; // Keep track of number of unoccupied spots

    public CarPark() {
        this.parkingSpots = new ArrayList<ParkingSpot>();
        this.noUnoccupiedSpots = 0;
    }

    /**
     * Gets list of parking spots and returns it.
     * @return list of parkingSpots
     */
    public ArrayList<ParkingSpot> getParkingSpots() {
        return this.parkingSpots;
    }

    /**
     * Gets number of unoccupied spots and returns it.
     * @return number of unoccupied spots
     */
    public int getNoUnoccupiedSpots() {
        return this.noUnoccupiedSpots;
    }

    /**
     * Adds new parking spot and returns the created parking spot if new parking spot id is valid
     * and added to car park. Returns null otherwise.
     * @param id id of new parking spot
     * @return the newly created parking spot if successfully added. null otherwise.
     */
    public ParkingSpot addParkingSpot(String id) {
        ParkingSpot parkingSpot = null; // Initialize parking spot variable
        // Check if parking spot id is unique
        boolean isUnique = Validators.checkSpotIdUnique(id, this.parkingSpots);
        if (isUnique) {
            parkingSpot = new ParkingSpot(id); // Create new parking spot
            this.parkingSpots.add(parkingSpot); // Add new parking spot to car park
            this.noUnoccupiedSpots++; // Increase number of unoccupied spots
        }
        return parkingSpot;
    }

    /**
     * Deletes a parking spot from the car park
     * if it is not occupied
     * @param spotId ID of parking spot to be deleted
     * @return result of deletion
     */
    public String deleteParkingSpot(String spotId) {
        String message = "Spot does not exist."; // Initialize message
        // Iterator of parking spot
        Iterator<ParkingSpot> it = this.parkingSpots.iterator();
        while(it.hasNext()) {
            ParkingSpot current = it.next(); // Assign parking spot as current
            // Find parking spot that matches spotId
            if (current.getId().equals(spotId)) {
                // Check if spot is occupied
                if (current.isOccupied()) {
                    message = "Spot is occupied."; // Update result message
                } else {
                    it.remove(); // Remove parking spot
                    this.noUnoccupiedSpots--; // Reduce number of unoccupied spots
                    message = "Parking spot deleted successfully."; // Update result message
                }
                break;
            }
        }
        return message;
    }

    /**
     * Parks a car in a parking spot from the car park
     * if it is not occupied
     * @param spotId ID of parking spot
     * @param car Car to be parked
     * @return result of parking car
     */
    public String parkCarInSpot(String spotId, Car car) {
        String message = "Spot does not exist."; // Initialize message

        // Check if car is unique
        boolean isCarUnique = Validators.checkCarParkedIsUnique(car.getRegNo(), this.parkingSpots);
        if (!isCarUnique) {
            message = "Car is already parked in another parking slot."; // Update result message
            return message;
        }

        // Iterate through parking spots
        for (ParkingSpot current : this.parkingSpots) {
            // Find parking spot that matches spotId
            if (current.getId().equals(spotId)) {
                // Check if spot is occupied
                if (current.isOccupied()) {
                    message = "Spot is occupied.";
                } else {
                    current.addCar(car);
                    this.noUnoccupiedSpots--; // Reduce number of unoccupied spots
                    message = "Car parked successfully"; // Update result message
                }
                break;
            }
        }
        return message;
    }

    /**
     * Finds a car by registration number and returns the parking spot ID
     * with car in it.
     * @param regNo Registration number of car to find
     * @return result of finding car by registration number
     */
    public String findCarByRegNo(String regNo) {
        String message = "Car not parked here."; // Initialize message
        for (ParkingSpot current : this.parkingSpots) {
            // Check if parking spot is occupied
            if (current.isOccupied()) {
                // Check if parked car matches registration number
                if (current.getParkedCar().getRegNo().equals(regNo)) {
                    message = "<html>Car with registration " + regNo + " found on spot " + current.getId()
                            + "<br />Duration of parking: " + current.getParkedCar().getDurationOfParking();
                    break;
                }
            }
        }
        return message;
    }

    /**
     * Removes a car by registration number and returns the result of operation
     * @param regNo Registration number of car to remove
     * @return spot ID that is used to hold parked car
     */
    public String removeCarByRegNo(String regNo) {
        String message = "Car not parked here."; // Initialize message
        for (ParkingSpot current : this.parkingSpots) {
            // Check if parking spot is occupied
            if (current.isOccupied()) {
                // Check if parked car matches registration number
                if (current.getParkedCar().getRegNo().equals(regNo)) {
                    current.removeCar(); // Remove car from parking spot
                    this.noUnoccupiedSpots++; // Increase number of unoccupied spots
                    message = current.getId(); // Update result message
                    break;
                }
            }
        }
        return message;
    }

    /**
     * Find car(s) by make and returns the result
     * @param make Make of car to find
     * @return result of finding car(s) by make
     */
    public ArrayList<String> findCarByMake(String make) {
        ArrayList<String> result = new ArrayList<String>(); // Initialize array list
        for (ParkingSpot current : this.parkingSpots) {
            // Check if parking spot is occupied
            if (current.isOccupied()) {
                // Check if parked car matches make
                if (current.getParkedCar().getMake().equals(make)) {
                    Car parkedCar = current.getParkedCar();
                    String carDetails = parkedCar.getRegNo() + " " + parkedCar.getMake() + " "
                            + parkedCar.getModel() + " " + parkedCar.getYear() + " parked at spot " + current.getId()
                            + "<br />Duration of parking: " + parkedCar.getDurationOfParking();
                    result.add(carDetails);
                }
            }
        }
        return result;
    }

    /**
     * Resets all cars from parking spots in the car park
     */
    public void resetCarPark() {
        // Iterate through parking spots
        for (ParkingSpot current : this.parkingSpots) {
            // If parking spot is occupied, remove car from parking spot
            if (current.isOccupied()) {
                current.removeCar();
                this.noUnoccupiedSpots++;
            }
        }
    }
}
