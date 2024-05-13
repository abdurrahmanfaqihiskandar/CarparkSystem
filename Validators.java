import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;

/** Project 2: Validators class stores the different validation
 * methods to be used in the application.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class Validators {
    private static final String PARKING_SPOT_REGEX = "^[A-Z]{1}\\d{3}$";
    private static final String CAR_REG_NO_REGEX = "^[A-Z]{1}\\d{4}$";

    /**
     * Validates parking spot id and returns if it is valid.
     *
     * @param id Parking spot id to be validated
     * @return true if valid, false otherwise
     */
    public static boolean validateParkingSpotId(String id) {
        return id.matches(PARKING_SPOT_REGEX);
    }

    /**
     * Checks if spot id for new parking spot is unique
     * @param spotId ID of new parking spot
     * @return whether the ID is unique
     */
    public static boolean checkSpotIdUnique(String spotId, ArrayList<ParkingSpot> parkingSpots) {
        boolean isUnique = true; // Initialize unique variable

        // Iterate through parking spots
        for (ParkingSpot current : parkingSpots) {
            // If parking spot has same spot ID,
            // return false
            if (current.getId().equals(spotId)) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }

    /**
     * Checks if spot id exists
     * @param spotId ID of parking spot
     * @return whether the ID exists
     */
    public static boolean checkSpotIdExists(String spotId, ArrayList<ParkingSpot> parkingSpots) {
        boolean exists = false; // Initialize unique variable

        // Iterate through parking spots
        for (ParkingSpot current : parkingSpots) {
            // If parking spot has same spot ID,
            // return true
            if (current.getId().equals(spotId)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * Checks if car is already parked in a parking slot
     * @param regNo Registration number of car to be parked
     * @return whether registration number of car is unique
     */
    public static boolean checkCarParkedIsUnique(String regNo, ArrayList<ParkingSpot> parkingSpots) {
        boolean isUnique = true; // Initialize unique variable

        // Iterate through parking spots
        for (ParkingSpot current : parkingSpots) {
            // If parking spot is occupied and
            // car has same registration number,
            // return false
            if (current.isOccupied() && current.getParkedCar().getRegNo().equals(regNo)) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }

    /**
     * Validates car registration number and returns if it is valid.
     *
     * @param carRegNo user input car registration number
     * @return true if valid, false otherwise
     */
    public static boolean validateCarRegNo(String carRegNo) {
        return carRegNo.matches(CAR_REG_NO_REGEX);
    }

    /**
     * Validates car year and returns if it is valid.
     *
     * @param carYear user input car year
     * @return true if valid, false otherwise
     */
    public static boolean validateCarYear(int carYear) {
        return carYear >= 2004 && carYear <= 2024;
    }
}
