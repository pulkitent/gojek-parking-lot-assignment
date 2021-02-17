package com.parking.models;

import com.parking.exception.ParkingLotGenericException;

import java.util.List;

import static com.parking.constant.Constants.ALL_PARKING_LOTS_ARE_FULL;

public class FillFirst implements DispatchingStrategy {
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
