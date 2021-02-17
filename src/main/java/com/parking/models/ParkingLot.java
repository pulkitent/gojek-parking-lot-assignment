package com.parking.models;

import com.parking.exception.ParkingLotGenericException;

import java.math.BigDecimal;
import java.util.*;

import static com.parking.constant.Constants.*;
import static java.lang.System.out;

public class ParkingLot {
    private final List<Slot> emptyParkingSlots;
    private final List<Slot> occupiedParkingSlots;
    private final int size;

    public ParkingLot(int size) {
        this.size = size;
        this.emptyParkingSlots = new LinkedList<>();
        this.occupiedParkingSlots = new LinkedList<>();
        allocateEmptySpots();
    }

    public int park(Car car) {
        if (occupiedParkingSlots.size() == size) {
            throw new ParkingLotGenericException(SORRY_PARKING_LOT_IS_FULL);
        }

        Slot slot = emptyParkingSlots.get(0);
        int slotId = slot.getSlotNumber();

        issueTicket(car, slotId);
        slot.assignCarToSlot(car);

        emptyParkingSlots.remove(slot);
        occupiedParkingSlots.add(slot);

        return slotId;
    }

    public void unPark(int slotId) {
        if (slotId > size) {
            throw new ParkingLotGenericException(PARKING_SLOT_DOESNT_EXIST);
        }

        Slot slotToBeRemoved = null;

        for (Slot slot : occupiedParkingSlots) {
            if (slot.hasGivenSlotId(slotId)) {
                slotToBeRemoved = slot;
            }
        }

        if (slotToBeRemoved.isEmpty()) {
            throw new ParkingLotGenericException(PARKING_SLOT_WAS_ALREADY_EMPTY);
        }

        slotToBeRemoved.removeCarFromSlot();
        occupiedParkingSlots.remove(slotToBeRemoved);
        emptyParkingSlots.add(slotToBeRemoved);

        emptyParkingSlots.sort(Comparator.comparingInt(Slot::getSlotNumber));
    }

    public void printStatus() {
        for (Slot slot : occupiedParkingSlots) {
            out.println(slot);
        }
    }

    public List<String> getAllCarsRegistrationByColor(String color) {
        List<String> carsRegistrationNumber = new ArrayList<>();

        if (occupiedParkingSlots.isEmpty())
            return new ArrayList<>();

        for (Slot occupiedSlot : occupiedParkingSlots) {
            if (occupiedSlot.hasCarOfGivenColor(color)) {
                String allottedCarRegistrationNumber = occupiedSlot.getAllottedCarRegistrationNumber();
                carsRegistrationNumber.add(allottedCarRegistrationNumber);
            }
        }
        return carsRegistrationNumber;
    }

    public int getSlotNumberWithCarRegistrationNumber(String registrationNumber) {
        for (Slot occupiedParkingSlot : occupiedParkingSlots) {
            if (occupiedParkingSlot.hasCarOfGivenNumber(registrationNumber)) {
                return occupiedParkingSlot.getSlotNumber();
            }
        }
//        throw new ParkingLotGenericException(CAR_DOESNT_EXIST);
        return Integer.MIN_VALUE;
    }

    public List<Integer> getAllSlotNumbersWithGivenColor(String color) {
        List<Integer> allSlotNumbersWithGivenColor = new ArrayList<>();

        for (Slot occupiedParkingSlot : occupiedParkingSlots) {
            if (occupiedParkingSlot.hasCarOfGivenColor(color)) {
                int slotNumberWithGivenColor = occupiedParkingSlot.getSlotNumber();
                allSlotNumbersWithGivenColor.add(slotNumberWithGivenColor);
            }
        }
        return allSlotNumbersWithGivenColor;
    }

    boolean isParkingLotEmpty() {
        return this.emptyParkingSlots.isEmpty();
    }

    int getNumberOfEmptySlots() {
        return this.emptyParkingSlots.size();
    }

    BigDecimal getPercentageOfEmptiness() {
        String sizeInString = String.valueOf(this.emptyParkingSlots.size());
        String totalSizeInString = String.valueOf(this.size);

        BigDecimal sizeInBigDecimal = new BigDecimal(sizeInString);
        BigDecimal totalSizeInBigDecimal = new BigDecimal(totalSizeInString);
        BigDecimal HUNDRED = new BigDecimal("100");

        BigDecimal percentage = sizeInBigDecimal.divide(totalSizeInBigDecimal).multiply(HUNDRED);
        return percentage;
    }

    private void allocateEmptySpots() {
        for (int index = 0; index < size; index++) {
            int slotNumber = index + 1;
            emptyParkingSlots.add(new Slot(slotNumber));
        }
    }

    private void issueTicket(Car car, int slotId) {
        String registrationNumber = car.getRegistrationNumber();
        Ticket ticket = new Ticket(registrationNumber, slotId);

        car.assignTicket(ticket);
    }
}
//TODO: emptyParkingSlots DS needs to be changed for optimization