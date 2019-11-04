package ru.job4j.parking;

import java.util.List;

interface ParkingInterface {

    boolean checkPlace(int placeNum);

    List<ParkPlace> getAllFreePlaces();

    List<ParkPlace> getCarFreePlaces();

    List<ParkPlace> getTrackFreePlaces();

    boolean startParking(ParkPlace place);

    boolean stopParking(ParkPlace place);

}
