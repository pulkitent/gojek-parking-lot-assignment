package com.parking.models;

import java.math.BigDecimal;
import java.util.List;

public class EvenDistribution implements DispatchingStrategy {
    @Override
    public int dispatchCustomerTo(List<ParkingLot> parkingLots) {
        BigDecimal maximumEmptyParkingSLots = new BigDecimal(Integer.MIN_VALUE);
        int maximumEmptyParkingSlotId = 0;

        int index = 0;

        for (ParkingLot parkingLot : parkingLots) {
            BigDecimal percentageOfEmptiness = parkingLot.getPercentageOfEmptiness();

            if (percentageOfEmptiness.compareTo(maximumEmptyParkingSLots) == 1) {
                maximumEmptyParkingSLots = percentageOfEmptiness;
                maximumEmptyParkingSlotId = index;
            }
        }
        return maximumEmptyParkingSlotId;
    }
}
