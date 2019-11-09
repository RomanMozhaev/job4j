package ru.job4j.parking;

import java.util.List;

/**
 * the interface for parking.
 */
interface ParkingInterface {
    /**
     * checks the place is free or ont
     * @param placeNum - the place number on the parking
     * @return - true if the place is free.
     */
    boolean checkPlace(int placeNum);

    /**
     * returns the list of the free places.
     * @return - the list
     */
    List<ParkPlace> getAllFreePlaces();

    /**
     * returns the list of the all free car places.
     * @return the list.
     */
    List<ParkPlace> getCarFreePlaces();

    /**
     * returns the list of the all free track places.
     * @return - the list.
     */
    List<ParkPlace> getTrackFreePlaces();

    /**
     * sets the car information in the place.
     * @param car - the object with car information.
     * @return true if the car was parked.
     */
    boolean startParking(ParkingCar car);

    /**
     * sets in the place where the car was parked the empty data.
     * @param car - the car which was parked.
     * @return - true if the parking is finished.
     */
    boolean stopParking(ParkingCar car);

}
