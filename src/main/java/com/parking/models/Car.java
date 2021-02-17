package com.parking.models;

import com.parking.exception.ParkingLotGenericException;

import java.util.Objects;

import static com.parking.constant.Constants.*;

public class Car {
    private final String registrationNumber;
    private final String color;
    private final Driver driver;

    public Car(String registrationNumber, String color, Driver driver) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.driver = driver;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String toString() {
        return registrationNumber + SPACE_STRING + color;
    }

    void assignTicket(Ticket ticket) {
        driver.assignTicket(ticket);
    }

    boolean hasGivenColor(String color) {
        if (Objects.isNull(color)) {
            throw new ParkingLotGenericException(COLOR_CAN_NOT_BE_EMPTY);
        }
        return color.equalsIgnoreCase(this.color);
    }

    boolean hasGivenRegistrationNumber(String registrationNumber) {
        if (Objects.isNull(registrationNumber)) {
            throw new ParkingLotGenericException(REGISTRATION_NUMBER_CAN_NOT_BE_EMPTY);
        }
        return registrationNumber.equalsIgnoreCase(this.registrationNumber);
    }
}
