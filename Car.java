import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Project 2: Car class that has attributes of id, isEmpty, parkedCar,
 * and parked time.
 * Includes methods to add and remove a car, start of parking time and duration
 * of parking.
 *
 * @author Abdurrahman Faqih 104675143
 * @version 0.1 9 May 2024
 */

public class Car {
    // Initialize details
    private String regNo;
    private String make;
    private String model;
    private int year;
    private LocalDateTime timeStart;

    public Car(String regNo, String make, String model, int year) {
        this.regNo = regNo;
        this.make = make;
        this.model = model;
        this.year = year;
        this.timeStart = LocalDateTime.now();
    }

    /**
     * Gets registration number of car and returns it
     * 
     * @return registration number of car
     */
    public String getRegNo() {
        return this.regNo;
    }

    /**
     * Gets make of car and returns it
     * 
     * @return make of car
     */
    public String getMake() {
        return this.make;
    }

    /**
     * Gets model of car and returns it
     * 
     * @return model of car
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Gets year of car and returns it
     * 
     * @return year of car
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Gets start time of parking of car and returns it
     * 
     * @return start time of parking of car
     */
    public LocalDateTime getTimeStart() {
        return this.timeStart;
    }

    /**
     * Get duration of parking
     * 
     * @return duration of car parked
     */
    public String getDurationOfParking() {
        String duration;
        LocalDateTime timeStart = this.getTimeStart();
        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(timeStart, now);
        long hours = diff.toHours();
        long mins = diff.minusHours(hours).toMinutes();
        long secs = diff.minusHours(hours).minusMinutes(mins).toSeconds();
        duration = hours + " hours " + mins + " minutes " + secs + " seconds.";
        return duration;
    }
}
