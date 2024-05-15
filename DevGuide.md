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

Each dialog is a subclass of the JDialog class. The event listeners for the multi-purpose dialogs are defined in helper class DialogActionListeners.

#### ActionDialog

Consists of a label, an action button and a close button.
