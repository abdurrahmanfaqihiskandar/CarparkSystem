# Project Details

PROJECT TITLE: Carpark System

PURPOSE OF PROJECT: Project 2 for Object Oriented Programming module

VERSION or DATE: 15 May 2024

HOW TO START THIS PROJECT: Open up project in BlueJ and run the CarparkSystem java file.

AUTHORS: Abdurrahman Faqih Bin Iskandar 104675143

# User Instructions

## System requirements

Java 21.

## Add parking spot

Add a new parking spot in car park.

Click on add parking spot button. Enter ID for new parking spot.

_Parking spot ID must be an uppercase letter followed by 3 digits._

## Delete parking spot

Delete parking spot from car park.

Click on delete parking spot button. Enter ID of parking spot to delete.

_Parking spot ID must be an uppercase letter followed by 3 digits._

_Parking spot with entered ID must exist._

_Parking spot with entered ID must not be occupied._

## List parking spots

Summary of car park and lists details of each parking spot in car park.

Click on list parking spots button.

## Park a car

Parks a car in an unoccupied spot in the car park.

Click on park a car button. Enter ID of parking spot and car details.

_Parking spot ID must be an uppercase letter followed by 3 digits._

_Parking spot with entered ID must exist._

_Parking spot with entered ID must not be occupied._

_Car registration number must be an uppercase letter followed by 4 digits._

_Car make and model must not be empty._

_Car year must be between 2004 and 2024 inclusive._

_Car must not already be parked in another parking spot._

## Find car by registration number

Finds a car in the car park by registration number.

Click on find car by reg number. Enter registration number of car to find.

_Car registration number must be an uppercase letter followed by 4 digits._

_Shows "Not found" if car is not found_

## Remove car by registration number

Removes a car in the car park by registration number.

Click on remove car by reg number. Enter registration number of car to find.

_Car registration number must be an uppercase letter followed by 4 digits._

_Car must already be parked in car park._

## Find car by make

Finds all cars with the entered make.

Click on find car by make. Enter make of car(s) to find.

_Car make must not be empty._

_Shows "Not found" if no car is found._

## Reset car park

Resets car park by removing all parked cars.

Click on reset carpark button.

## Exit program

Exits application and closes window.

Click on exit program button.

# Developer guide

The application is created using Java libraries AWT and Swing.

The application uses the Model, View, Controller architecture.

The diagram below illustrates the architecture of the Carpark System application

![Carpark System architecture diagram](ComponentDiagram.png "Architecture Diagram")

## Model

The application models handles all business logic such as adding or deleting parking spots and parking a car.

### CarPark

Manages a list of parking spots.

Stores list of parking spots in carpark and number of unoccupied parking spots.

Methods:

- getParkingSpots
- getNoUnoccupiedSpots
- addParkingSpot
- deleteParkingSpot
- parkCarInSpot
- findCarByRegNo
- removeCarByRegNo
- findCarByMake
- resetCarPark

### ParkingSpot

Models a parking spot in a carpark object.

Stores the ID of the parking spot and reference to car parked in the parking spot.

Methods:

- addCar
- removeCar
- getId
- isOccupied
- getParkedCar

### Car

Models a car

Stores details of the car and the time when it is parked.

Methods:

- getRegNo
- getMake
- getModel
- getYear
- getTimeStart
- getDurationOfParking

## View

Handles all display logic with respect to the GUI and user interactions.

View is updated whenever there is a change in its corresponding model.

### CarparkView

A JPanel subclass that displays all parking spots and its details.

### ParkingSpotView

A JButton subclass that displays details of the parking spot and its parked car (if any).

Background colour of ParkingSpotView changes with occupancy. Green -> Unoccupied, Red -> Occupied.

### TimerPanel

A JPanel subclass that displays the current time.

### MenuPanel

A JPanel subclass that displays all menu buttons available.

Each menu button is assigned a corresponding event listener that is defined in the controller class.

The event listeners for each button opens the corresponding dialog component that requires more user inputs. For e.g., addParkingSpotBtn has an event listener that opens the addParkingSpotDialog component.

### MenuButton

A JButton subclass that displays the button text with the styles defined.

### Dialogs

There are a total of 5 dialog classes:

3 multi-purpose dialogs:

- ActionDialog
- FormDialog
- MessageDialog

and 2 other dialogs that do not fit in the multi-purpose dialogs:

- ParkCarDialog, and
- ParkingSpotViewActionDialog.

The event listeners for the multi-purpose dialogs are defined in helper class DialogActionListeners.

#### ActionDialog

Consists of a label, an action button and a close button.
