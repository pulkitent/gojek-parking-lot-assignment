package com.parking.models;

import java.util.Objects;

import static com.parking.constant.Constants.*;
import static java.util.Objects.isNull;

public class Slot {
    private final int slotNumber;
    private Car allottedCar;

    public Slot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return slotNumber == slot.slotNumber && allottedCar.equals(slot.allottedCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotNumber, allottedCar);
    }

    @Override
    public String toString() {
        return slotNumber + SPACE_STRING + ONE_TABS + TWO_SPACES + allottedCar;
    }

    public void assignCarToSlot(Car car) {
        this.allottedCar = car;
    }

    boolean isEmpty() {
        return isNull(this.allottedCar);
    }

    boolean hasCarOfGivenColor(String color) {
        return allottedCar.hasGivenColor(color);
    }

    String getAllottedCarRegistrationNumber() {
        return this.allottedCar.getRegistrationNumber();
    }

    boolean hasCarOfGivenNumber(String registrationNumber) {
        return allottedCar.hasGivenRegistrationNumber(registrationNumber);
    }

    boolean hasGivenSlotId(int slotNumber) {
        return this.slotNumber == slotNumber;
    }
}
