/**
 * Project 2: ParkingSpot class that has attributes of id, and parkedCar,
 * getter methods for its attributes,
 * and methods to add and remove a car.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 10 May 2024
 */

public class ParkingSpot {
    // Initialize details
    private String id;
    private Car parkedCar;

    public ParkingSpot(String id) {
        this.id = id;
        this.parkedCar = null;
    }

    /**
     * Adds a car to the parking spot
     * @param car Car to be added to the parking spot
     */
    public void addCar(Car car) {
        this.parkedCar = car;
    }

    /**
     * Removes a car from the parking spot
     */
    public void removeCar() {
        this.parkedCar = null;
    }

    /**
     * Gets id of parking spot and returns it
     * @return id of parking spot
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns whether the parking spot is occupied
     * @return true if parkedCar is not null, false otherwise
     */
    public boolean isOccupied() {
        return this.parkedCar != null;
    }

    /**
     * Returns the parked car object of car parked in parking spot
     * @return car parked in parking spot
     */
    public Car getParkedCar() {
        return this.parkedCar;
    }
}
