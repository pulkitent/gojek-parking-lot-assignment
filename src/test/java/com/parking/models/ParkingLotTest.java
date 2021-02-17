package com.parking.models;

import com.parking.exception.ParkingLotGenericException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.parking.constant.Constants.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {

    @BeforeEach
    void setup() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void park_shouldParkTheGivenCarAndAssertSlotId() {
        //Arrange
        int size = 6;
        ParkingLot parkingLot = new ParkingLot(size);
        String registrationNumber = "123";
        String color = "white";
        Driver driver = new Driver();
        Car car = new Car(registrationNumber, color, driver);
        int expectedSlotId = 1;

        //Action
        int slotId = parkingLot.park(car);

        //Assert
        assertThat(slotId, is(expectedSlotId));
    }

    @Test
    void park_shouldAssertParkingFullException() {
        //Arrange
        int size = 0;
        ParkingLot parkingLot = new ParkingLot(size);
        String registrationNumber = "123";
        String color = "white";
        Driver driver = new Driver();
        Car car = new Car(registrationNumber, color, driver);
        int expectedSlotId = 1;

        //Action
        Exception exception = assertThrows(ParkingLotGenericException.class, () -> parkingLot.park(car));

        //Assert
        String actualMessage = exception.getMessage();
        assertThat(actualMessage, is(SORRY_PARKING_LOT_IS_FULL));
    }

    @Test
    void unPark_shouldUnParkTheCarAndAssertExceptionAndItsMessage() {
        //Arrange
        int size = 6;
        ParkingLot parkingLot = new ParkingLot(size);
        String registrationNumber = "123";
        String anotherRegistrationNumber = "1234";
        String color = "white";
        Driver driver = new Driver();
        Driver anotherDriver = new Driver();
        Car car = new Car(registrationNumber, color, driver);
        Car anotherCar = new Car(anotherRegistrationNumber, color, anotherDriver);
        int slotId = parkingLot.park(car);
        int anotherSlotId = parkingLot.park(anotherCar);

        //Action
        parkingLot.unPark(anotherSlotId);


        //Assert
        Exception exception = assertThrows(ParkingLotGenericException.class,
                () -> parkingLot.getSlotNumberWithCarRegistrationNumber(anotherRegistrationNumber));
        String actualMessage = exception.getMessage();
        assertThat(actualMessage, is(CAR_DOESNT_EXIST));
    }

    @Test
    void unPark_shouldAssertParkingSlotDoesNotExistExceptionAndItsMessage() {
        //Arrange
        int size = 6;
        ParkingLot parkingLot = new ParkingLot(size);
        String registrationNumber = "123";
        String anotherRegistrationNumber = "1234";
        String color = "white";
        Driver driver = new Driver();
        Driver anotherDriver = new Driver();
        Car car = new Car(registrationNumber, color, driver);
        Car anotherCar = new Car(anotherRegistrationNumber, color, anotherDriver);
        parkingLot.park(car);
        parkingLot.park(anotherCar);

        //Action
        Exception actualException = assertThrows(ParkingLotGenericException.class,
                () -> parkingLot.unPark(100));

        //Assert
        String actualMessage = actualException.getMessage();
        assertThat(actualMessage, is(PARKING_SLOT_DOESNT_EXIST));
    }

    @Test
    void getAllCarsRegistrationByColor_shouldAssertAllRegistrationNumbersForWhiteColor() {
        //Arrange
        int size = 6;
        ParkingLot parkingLot = new ParkingLot(size);
        String registrationNumber = "123";
        String anotherRegistrationNumber = "1234";
        String color = "white";
        Driver driver = new Driver();
        Driver anotherDriver = new Driver();
        Car car = new Car(registrationNumber, color, driver);
        Car anotherCar = new Car(anotherRegistrationNumber, color, anotherDriver);
        parkingLot.park(car);
        parkingLot.park(anotherCar);

        List<String> expectedRegistrationNumbers = asList("123", "1234");

        //Action
        List<String> allActualCarsRegistrationNumbers = parkingLot.getAllCarsRegistrationByColor(color);

        //Assert
        assertThat(allActualCarsRegistrationNumbers.size(), is(2));
        assertThat(allActualCarsRegistrationNumbers, containsInAnyOrder(expectedRegistrationNumbers.toArray()));
    }

    @Test
    void getAllCarsRegistrationByColor_shouldAssertEmptyRegistrationNumbersForWhiteColor() {
        //Arrange
        int size = 6;
        ParkingLot parkingLot = new ParkingLot(size);
        String color = "white";

        //Action
        List<String> actualCarsRegistrationNumbers = parkingLot.getAllCarsRegistrationByColor(color);

        //Assert
        assertThat(actualCarsRegistrationNumbers.size(), is(0));
    }

    @Test
    void getSlotNumberWithCarRegistrationNumber_ShouldAssertSlotNumberOfGivenParkedCars() {
        //Arrange
        int size = 6;
        ParkingLot parkingLot = new ParkingLot(size);
        String registrationNumber = "123";
        String anotherRegistrationNumber = "1234";
        String color = "white";
        Driver driver = new Driver();
        Driver anotherDriver = new Driver();
        Car car = new Car(registrationNumber, color, driver);
        Car anotherCar = new Car(anotherRegistrationNumber, color, anotherDriver);
        parkingLot.park(car);
        parkingLot.park(anotherCar);

        //Action
        int slotNumber = parkingLot.getSlotNumberWithCarRegistrationNumber(registrationNumber);
        int anotherSlotNumber = parkingLot.getSlotNumberWithCarRegistrationNumber(anotherRegistrationNumber);

        //Assert
        assertThat(slotNumber, is(1));
        assertThat(anotherSlotNumber, is(2));
    }

    @Test
    void getAllSlotNumbersWithGivenColor_ShouldAssertSlotNumberForGivenColor() {
        //Arrange
        int size = 6;
        ParkingLot parkingLot = new ParkingLot(size);
        String registrationNumber = "123";
        String anotherRegistrationNumber = "1234";
        String color = "white";
        Driver driver = new Driver();
        Driver anotherDriver = new Driver();
        Car car = new Car(registrationNumber, color, driver);
        Car anotherCar = new Car(anotherRegistrationNumber, color, anotherDriver);
        parkingLot.park(car);
        parkingLot.park(anotherCar);
        List<Integer> expectedSlotNumbers = asList(1, 2);

        //Action
        List<Integer> actualSlotNumbers = parkingLot.getAllSlotNumbersWithGivenColor(color);

        //Assert
        assertThat(actualSlotNumbers.size(), is(2));
        assertThat(actualSlotNumbers, containsInAnyOrder(expectedSlotNumbers.toArray()));
    }

    @Test
    void getAllSlotNumbersWithGivenColor_ShouldAssertEmptySlotNumberForGivenColor() {
        //Arrange
        int size = 6;
        ParkingLot parkingLot = new ParkingLot(size);
        String registrationNumber = "123";
        String anotherRegistrationNumber = "1234";
        String color = "white";
        Driver driver = new Driver();
        Driver anotherDriver = new Driver();
        Car car = new Car(registrationNumber, color, driver);
        Car anotherCar = new Car(anotherRegistrationNumber, color, anotherDriver);
        parkingLot.park(car);
        parkingLot.park(anotherCar);

        //Action
        List<Integer> actualSlotNumbers = parkingLot.getAllSlotNumbersWithGivenColor("RED");

        //Assert
        assertThat(actualSlotNumbers.size(), is(0));
    }
}
