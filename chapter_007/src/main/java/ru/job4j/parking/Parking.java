package ru.job4j.parking;

import java.util.List;

public class Parking implements ParkingInterface{
    /**
     * scheme - the scheme of the parking.
     * n - how many places are occupied by track
     */
    private ParkPlace[][] scheme;
    private int n;
    private static final char CAR = 'C';
    private static final char TRK = 'T';
    private static final char FRE = 'F';
    private static final char OCC = 'O';
    private static final char OCT = 'N';

    public Parking(ParkPlace[][] scheme, int n) {
        this.scheme = scheme;
        this.n = n;
    }

    public boolean checkPlace(int placeNum) {
        return false;
    }

    public List<ParkPlace> getAllFreePlaces() {
        return null;
    }

    public List<ParkPlace> getCarFreePlaces() {
        return null;
    }

    public List<ParkPlace> getTrackFreePlaces() {
        return null;
    }
    public boolean startParking(ParkPlace place) {
        return false;
    }

    public boolean stopParking(ParkPlace place) {
        return false;
    }
}
