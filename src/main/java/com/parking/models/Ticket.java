package com.parking.models;

import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final String carRegistrationNumber;
    private final int slotNumber;

    public Ticket(String carRegistrationNumber, int slotNumber) {
        this.ticketId = UUID.randomUUID().toString();
        this.carRegistrationNumber = carRegistrationNumber;
        this.slotNumber = slotNumber;
    }
}
