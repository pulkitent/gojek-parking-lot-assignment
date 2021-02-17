package com.parking.models;

import java.util.List;

public interface DispatchingStrategy {
    int dispatchCustomerTo(List<ParkingLot> parkingLots);
}
