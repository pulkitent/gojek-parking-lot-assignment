package com.parking.io.input;

import com.parking.models.Car;
import com.parking.models.Driver;
import com.parking.models.ParkingLot;

import java.util.List;
import java.util.Scanner;

import static com.parking.constant.Constants.*;
import static com.parking.io.output.ConsoleOutputUtility.print;
import static com.parking.io.output.ConsoleOutputUtility.printInNextLine;

public class CommonUtility {
    static void executeCommands() {
        Scanner scanner = new Scanner(System.in);
        String command = EMPTY_STRING;

        ParkingLot parkingLot = null;
        printInNextLine(WELCOME_MESSAGE);

        while (!EXIT.equalsIgnoreCase(command)) {
            printInNextLine(PLEASE_ENTER_YOUR_COMMAND);

            command = scanner.nextLine();
            String[] words = command.split(SINGLE_SPACE);
            String commandFirstWord = words[0];

            switch (commandFirstWord.toLowerCase()) {
                case CREATE_PARKING_LOT_COMMAND:
                    String size = words[1];
                    parkingLot = new ParkingLot(Integer.parseInt(size));
                    printInNextLine(CREATED_PARKING_LOT_MESSAGE + size + SLOTS_MESSAGE);
                    break;

                case PARK:
                    String registrationNumber = words[1];
                    String color = words[2];
                    Driver driver = new Driver();
                    Car car = new Car(registrationNumber, color, driver);
                    try {
                        assert parkingLot != null;
                        int slotId = parkingLot.park(car);
                        printInNextLine(ALLOCATED_SLOT_NUMBER_MESSAGE + slotId);
                    } catch (RuntimeException exception) {
                        printInNextLine(exception.getMessage());
                    }
                    break;

                case LEAVE_COMMAND:
                    int slotIdToBeEmpty = Integer.parseInt(words[1]);
                    assert parkingLot != null;
                    try {
                        parkingLot.unPark(slotIdToBeEmpty);
                        printInNextLine(SLOT_NUMBER_MESSAGE + slotIdToBeEmpty + IS_FREE_MESSAGE);
                    } catch (RuntimeException exception) {
                        printInNextLine(exception.getMessage());
                    }
                    break;

                case STATUS_COMMAND:
                    printInNextLine(STATUS_COLUMN_MESSAGE);
                    assert parkingLot != null;
                    parkingLot.printStatus();
                    break;

                case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND:
                    String colorToBeSearched = words[1];
                    assert parkingLot != null;
                    List<String> allCarsRegistrationByColor = parkingLot
                            .getAllCarsRegistrationByColor(colorToBeSearched);

                    for (String number : allCarsRegistrationByColor) {
                        print(number + SINGLE_SPACE);
                    }
                    printInNextLine(EMPTY_STRING);
                    break;

                case EXIT:
                    break;

                default:
                    printInNextLine(COMMAND_DOESNT_EXIST_MESSAGE);
            }
        }
    }

    static void executeCommandsFromFile(List<String> commands) {
        ParkingLot parkingLot = null;
        printInNextLine(WELCOME_MESSAGE);

        for (String command : commands) {
            printInNextLine(command);
            String[] words = command.split(SINGLE_SPACE);
            String commandFirstWord = words[0];

            switch (commandFirstWord.toLowerCase()) {
                case CREATE_PARKING_LOT_COMMAND:
                    String size = words[1];
                    parkingLot = new ParkingLot(Integer.parseInt(size));
                    print(CREATED_PARKING_LOT_MESSAGE + size + SLOTS_MESSAGE);
                    break;

                case PARK:
                    String registrationNumber = words[1];
                    String color = words[2];
                    Driver driver = new Driver();
                    Car car = new Car(registrationNumber, color, driver);
                    try {
                        assert parkingLot != null;
                        int slotId = parkingLot.park(car);
                        print(ALLOCATED_SLOT_NUMBER_MESSAGE + slotId);
                    } catch (RuntimeException exception) {
                        print(exception.getMessage());
                    }
                    break;

                case LEAVE_COMMAND:
                    int slotIdToBeEmpty = Integer.parseInt(words[1]);
                    assert parkingLot != null;
                    try {
                        parkingLot.unPark(slotIdToBeEmpty);
                        print(SLOT_NUMBER_MESSAGE + slotIdToBeEmpty + IS_FREE_MESSAGE);
                    } catch (RuntimeException exception) {
                        printInNextLine(exception.getMessage());
                    }
                    break;

                case STATUS_COMMAND:
                    print(STATUS_COLUMN_MESSAGE);
                    assert parkingLot != null;
                    parkingLot.printStatus();
                    break;

                case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND:
                    String colorToBeSearched = words[1];
                    assert parkingLot != null;
                    List<String> allCarsRegistrationByColor = parkingLot
                            .getAllCarsRegistrationByColor(colorToBeSearched);

                    for (String number : allCarsRegistrationByColor) {
                        print(number + SINGLE_SPACE);
                    }
                    print(EMPTY_STRING);
                    break;

                case EXIT:
                    break;

                default:
                    print(COMMAND_DOESNT_EXIST_MESSAGE);
            }
        }
    }
}
