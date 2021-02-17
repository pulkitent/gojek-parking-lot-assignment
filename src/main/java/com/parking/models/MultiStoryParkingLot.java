package com.parking.models;

import com.parking.exception.ParkingLotGenericException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.parking.constant.Constants.CAR_DOESNT_EXIST;

public class MultiStoryParkingLot {
    private final List<ParkingLot> parkingLots;
    private DispatchingStrategy dispatchingStrategy;

    public MultiStoryParkingLot(List<ParkingLot> parkingLotList, DispatchingStrategy dispatchingStrategy) {
        this.parkingLots = parkingLotList;
        this.dispatchingStrategy = dispatchingStrategy;
    }

    //Command
    public void park(Car car) {
        int parkingLotId = dispatchingStrategy.dispatchCustomerTo(parkingLots);
        ParkingLot parkingLot = parkingLots.get(parkingLotId);
        parkingLot.park(car);
    }

    //Query
    public Map<Integer, List<String>> getAllCarsRegistrationByColor(String color) {
        Map<Integer, List<String>> carsRegistrationNumberMap = new HashMap<>();

        int index = 0;
        for (ParkingLot parkingLot : parkingLots) {
            carsRegistrationNumberMap.put(index, parkingLot.getAllCarsRegistrationByColor(color));
            index++;
        }
        return carsRegistrationNumberMap;
    }

    //Query
    public SlotAndParkingNumberPair getSlotNumberWithCarRegistrationNumber(String registrationNumber) {
        int index = 0;
        for (ParkingLot parkingLot : parkingLots) {
            int slotNumber = parkingLot.getSlotNumberWithCarRegistrationNumber(registrationNumber);

            if (slotNumber != Integer.MIN_VALUE) {
                index++;
                return new SlotAndParkingNumberPair(slotNumber, index - 1);
            }
        }
        throw new ParkingLotGenericException(CAR_DOESNT_EXIST);
    }

    //Query
    public Map<Integer, List<Integer>> getAllSlotNumbersWithGivenColor(String color) {
        Map<Integer, List<Integer>> allSlotNumbersMap = new HashMap<>();

        int index = 0;
        for (ParkingLot parkingLot : parkingLots) {
            allSlotNumbersMap.put(index, parkingLot.getAllSlotNumbersWithGivenColor(color));
            index++;
        }
        return allSlotNumbersMap;
    }
}

class SlotAndParkingNumberPair {
    private final int slotNumber;
    private final int parkingNumber;

    public SlotAndParkingNumberPair(int slotNumber, int parkingNumber) {
        this.slotNumber = slotNumber;
        this.parkingNumber = parkingNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public int getParkingNumber() {
        return parkingNumber;
    }
}
