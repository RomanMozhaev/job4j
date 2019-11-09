package ru.job4j.parking;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * tests for Parking.
 */
public class ParkingTest {
    /**
     * the parking scheme.
     */
    private ParkPlace[][] scheme;

    /**
     * initiation of the scheme for parking.
     */
    @Before
    public void init() {
        this.scheme = new ParkPlace[4][9];
        int num = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                num++;
                this.scheme[i][j] = new ParkPlace(num, 'C');
            }
        }
        int k = 0;
        for (int i = 0; i < 3; i++) {
            num++;
            for (int j = 0; j < 3; j++) {
                this.scheme[3][k++] = new ParkPlace(num, 'T');
            }
        }
    }

    /**
     * checks that place is free.
     */
    @Test
    public void whenCheckPlaceThanFree() {
        ParkingInterface parking = new Parking(this.scheme, 3);
        assertTrue(parking.checkPlace(1));
    }

    /**
     * the place 4 has parked car. it checks that place 4 is occupied
     */
    @Test
    public void whenCheckPlaceThanOccupied() {
        ParkingInterface parking = new Parking(this.scheme, 3);
        ParkingCar place = new ParkingCar(4, 'C', "T111BB11");
        parking.startParking(place);
        assertFalse(parking.checkPlace(4));
    }

    /**
     * the first place is occupied, the first free place in the list has number 2.
     */
    @Test
    public void whenGetAllFreeThanFirstIsSecond() {
        ParkingInterface parking = new Parking(this.scheme, 3);
        ParkingCar place = new ParkingCar(1, 'C', "T111BB11");
        parking.startParking(place);
        List<ParkPlace> allFreePlaces = parking.getAllFreePlaces();
        int expect = 2;
        assertThat(allFreePlaces.get(0).getPlaceNum(), is(expect));
    }

    /**
     * the car was parked and than the parking stopped. The place is free than.
     */
    @Test
    public void whenStopParkingThanFree() {
        ParkingInterface parking = new Parking(this.scheme, 3);
        ParkingCar place1 = new ParkingCar(28, 'T', "T111BB11");
        parking.startParking(place1);
        ParkingCar place2 = new ParkingCar('T', "T111BB11");
        parking.stopParking(place2);
        assertTrue(parking.checkPlace(28));
    }

    /**
     * the car w\o number was parked
     */
    @Test
    public void whenStopParkingNoneThanFree() {
        ParkingInterface parking = new Parking(this.scheme, 3);
        ParkingCar place1 = new ParkingCar(28, 'T', "none");
        parking.startParking(place1);
        ParkingCar place2 = new ParkingCar(28, 'T', "none");
        parking.stopParking(place2);
        assertTrue(parking.checkPlace(28));
    }

    /**
     * the track is parked on the cars' places.
     */
    @Test
    public void whenTrackParkedOnCarsPlacesThanTreePlacesOccupied() {
        ParkingInterface parking = new Parking(this.scheme, 3);
        ParkingCar place = new ParkingCar(1, 'T', "T111BB11");
        parking.startParking(place);
        assertFalse(parking.checkPlace(1));
        assertFalse(parking.checkPlace(2));
        assertFalse(parking.checkPlace(3));
    }

}