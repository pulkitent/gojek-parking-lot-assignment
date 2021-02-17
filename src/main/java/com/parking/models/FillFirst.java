package com.parking.models;

import com.parking.exception.ParkingLotGenericException;

import java.util.List;

public class FillFirst implements DispatchingStrategy {
    private final static String ALL_PARKING_LOTS_ARE_FULL = "All Parking lots are full";

    @Override
    public int dispatchCustomerTo(List<ParkingLot> parkingLots) {

        int index = 0;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.isParkingLotEmpty()) {
                return index;
            }
            index++;
        }
        throw new ParkingLotGenericException(ALL_PARKING_LOTS_ARE_FULL);
    }
}
