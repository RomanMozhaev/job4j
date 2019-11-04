package ru.job4j.parking;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParkingTest {
    private ParkPlace[][] scheme;

    @Before
    public void init() {
        this.scheme = new ParkPlace[4][9];
        int num = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                num++;
                this.scheme[i][j] = new ParkPlace(num, 'C', 'F');
            }
        }
        int k = 0;
        for (int i = 0; i < 3; i++) {
            num++;
            for (int j = 0; j < 3; j++) {
                this.scheme[3][k++] = new ParkPlace(num, 'T', 'F');
            }
        }
    }

    @Test
    public void whenCheckPlaceThanFree() {
        ParkingInterface parking = new Parking(this.scheme, 3);
        assertTrue(parking.checkPlace(1));
    }

    @Test
    public void whenCheckPlaceThanOccupied() {
        ParkingInterface parking = new Parking(this.scheme, 3);
        ParkPlace place = new ParkPlace(4, 'C', "T111BB11");
        parking.startParking(place);
        assertFalse(parking.checkPlace(4));
    }
    @Test
    public void whenGetAllFreeThanFirstIsSecond() {
        ParkingInterface parking = new Parking(this.scheme, 3);
        ParkPlace place = new ParkPlace(1, 'C', "T111BB11");
        parking.startParking(place);
        List<ParkPlace> allFreePlaces = parking.getAllFreePlaces();
        int expect = 2;
        assertThat(allFreePlaces.get(1).getPlaceNum(), is(expect));
    }
    @Test
    public void whenStopParkingThanFree() {
        ParkingInterface parking = new Parking(this.scheme, 3);
        ParkPlace place1 = new ParkPlace(28, 'T', "T111BB11");
        parking.startParking(place1);
        ParkPlace place2 = new ParkPlace("T111BB11");
        parking.stopParking(place2);
        assertTrue(parking.checkPlace(28));
    }
    @Test
    public void whenTrackParkedOnCarsPlacesThanTreePlacesOccupied() {
        ParkingInterface parking = new Parking(this.scheme, 3);
        ParkPlace place = new ParkPlace(1, 'T', "T111BB11");
        parking.startParking(place);
        assertFalse(parking.checkPlace(1));
        assertFalse(parking.checkPlace(2));
        assertFalse(parking.checkPlace(3));
    }

}