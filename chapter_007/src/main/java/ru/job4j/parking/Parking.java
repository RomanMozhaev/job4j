package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;


public class Parking implements ParkingInterface {
    /**
     * scheme - the scheme of the parking.
     * For track places are taken n cells.
     */
    private ParkPlace[][] scheme;
    /**
     * n - how many places are occupied by track.
     */
    private int n;
    /**
     * the constant to mark a car.
     */
    private static final char CAR = 'C';
    /**
     * the constant to mark a track.
     */
    private static final char TRK = 'T';
    /**
     * the constant to mark a free place.
     */
    private static final char FRE = 'F';
    /**
     * the constant to mark an occupied place.
     */
    private static final char OCC = 'O';
    /**
     * the constant to mark the car places which are occupied by a track.
     */
    private static final char OCT = 'N';
    /**
     * the constant for cars without number.
     */
    private static final String NONE = "none";

    /**
     * the main constructor for the parking.
     *
     * @param scheme - the scheme of the parking
     * @param n      - the number of the car parking place which is needed for parking a track.
     */
    public Parking(ParkPlace[][] scheme, int n) {
        this.scheme = scheme;
        this.n = n;
    }

    /**
     * checks the place is free or occupied.
     *
     * @param placeNum - the place number on the parking.
     * @return - true if free, false if occupied or does not exist.
     */
    public boolean checkPlace(int placeNum) {
        boolean result = false;
        List<ParkPlace> places = findPlaces(placeNum);
        if (places.size() > 0 && places.get(0).getStatus() == FRE) {
            result = true;
        }
        return result;
    }

    /**
     * the method returns the list of the all free places on the parking.
     *
     * @return - the list with ParkPlaces
     */
    public List<ParkPlace> getAllFreePlaces() {
        return findFree((status, type) -> status == FRE && (type == CAR || type == TRK));
    }

    /**
     * the method returns the list of the all free car places on the parking.
     *
     * @return - the list with ParkPlaces
     */
    public List<ParkPlace> getCarFreePlaces() {
        return findFree((status, type) -> status == FRE && type == CAR);
    }

    /**
     * the method returns the list of the all free track places on the parking.
     *
     * @return - the list with ParkPlaces
     */
    public List<ParkPlace> getTrackFreePlaces() {
        return findFree((status, type) -> status == FRE && type == TRK);
    }

    /**
     * the method starts the parking of the car.
     * if the car has the place number it could be parked only on this place if it is free and .
     * if the car has no place number it is parked on the first free place.
     * The track which has no place number could be parked first on the track place and if there is no track places
     * on the cars places.
     * the track which has the car place number could be parked if the car place has 'n - 1' free places in line.
     *
     * @param car - the object with car information.
     * @return - true if the car was parked.
     */
    public boolean startParking(ParkingCar car) {
        boolean result = false;
        int num = car.getPlaceNum();
        if (num != 0) {
            List<ParkPlace> parkPlaceList = findPlaces(num);
            if (parkPlaceList.size() > 0) {
                ParkPlace placeOnParking = parkPlaceList.get(0);
                if (placeOnParking.getStatus() == FRE) {
                    if (car.getType() == CAR && placeOnParking.getType() == CAR) {
                        setPlaceParam(placeOnParking, OCC, car.getCarNumber());
                        result = true;
                    }
                    if (car.getType() == TRK && placeOnParking.getType() == TRK) {
                        for (ParkPlace place : parkPlaceList) {
                            setPlaceParam(place, OCC, car.getCarNumber());
                        }
                        result = true;
                    }
                    if (car.getType() == TRK && placeOnParking.getType() == CAR) {
                        List<ParkPlace> list = findAdjacentPlace(num);
                        if (list != null) {
                            for (ParkPlace place : list) {
                                setPlaceParam(place, OCT, car.getCarNumber());
                            }
                            result = true;
                        }
                    }
                }
            }
        } else {
            List<ParkPlace> list = new ArrayList<>();
            char status = OCC;
            int placesQuantity = 0;
            if (car.getType() == CAR) {
                list = getCarFreePlaces();
                placesQuantity = 1;
            }
            if (car.getType() == TRK) {
                placesQuantity = this.n;
                list = getTrackFreePlaces();
                if (list.isEmpty()) {
                    for (ParkPlace place : getCarFreePlaces()) {
                        List<ParkPlace> freeAdjacentPlaces = findAdjacentPlace(place.getPlaceNum());
                        if (freeAdjacentPlaces != null) {
                            list = freeAdjacentPlaces;
                            status = OCT;
                            break;
                        }
                    }
                }
            }
            if (!list.isEmpty()) {
                for (int i = 0; i < placesQuantity; i++) {
                    list.get(i).setStatus(status);
                    list.get(i).setCarNumber(car.getCarNumber());
                }
                result = true;
            }
        }
        return result;
    }

    /**
     * the method finds the free adjacent places. it looks first through the places with higher numbers then
     * which lower numbers.
     *
     * @param num - the place number.
     * @return the list of the first 'n' found free places in the line or null.
     */
    private List<ParkPlace> findAdjacentPlace(int num) {
        boolean result = true;
        List<ParkPlace> freeAdjacentPlaces = new ArrayList<>();
        int placeNum = num;
        boolean first = true;
        boolean second = false;
        while (freeAdjacentPlaces.size() != this.n) {
            if (first) {
                if (!addFreeCarPlace(freeAdjacentPlaces, placeNum++)) {
                    first = false;
                    second = true;
                    placeNum = num;
                }
            }
            if (second) {
                if (!addFreeCarPlace(freeAdjacentPlaces, placeNum--)) {
                    result = false;
                    break;
                }
            }
        }
        return result ? freeAdjacentPlaces : null;
    }

    /**
     * the method sets the status and car number on the parking place.
     *
     * @param place      - the place on the parking.
     * @param status     - the new status
     * @param carNumber- the new car number or none.
     */
    private void setPlaceParam(ParkPlace place, char status, String carNumber) {
        place.setStatus(status);
        place.setCarNumber(carNumber);
    }

    /**
     * the method adds to the list the found free car place.
     *
     * @param freeAdjacentPlaces - the list with the free places
     * @param placeNum           - the place number for checking and adding if it is free.
     * @return true if the place is free and was added.
     */
    private boolean addFreeCarPlace(List<ParkPlace> freeAdjacentPlaces, int placeNum) {
        boolean result = false;
        List<ParkPlace> adjacentList = findPlaces(placeNum);
        if (adjacentList.size() > 0) {
            ParkPlace place = adjacentList.get(0);
            if (place.getType() == CAR && place.getStatus() == FRE) {
                freeAdjacentPlaces.add(place);
                result = true;
            }
        }
        return result;
    }

    /**
     * the method stops the parking.
     * if the car has the parking place it may have no car number.
     * if the car has no parking place it must have car number.
     *
     * @param car - the car which was parked.
     * @return - true if the parking was stopped.
     */
    public boolean stopParking(ParkingCar car) {
        boolean result = false;
        if (car.getPlaceNum() == 0) {
            for (ParkPlace[] row : this.scheme) {
                for (ParkPlace place : row) {
                    if (place.getCarNumber().equals(car.getCarNumber())
                            && place.getType() == car.getType()) {
                        setPlaceParam(place, FRE, NONE);
                        result = true;
                    }
                }
            }
        } else {
            int num = car.getPlaceNum();
            for (ParkPlace[] row : this.scheme) {
                for (ParkPlace place : row) {
                    if (place.getPlaceNum() == num) {
                        String carNum = car.getCarNumber();
                        if ((carNum.equals(NONE) || carNum.equals(place.getCarNumber()))
                                && place.getType() == car.getType()) {
                            setPlaceParam(place, FRE, NONE);
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * the method returns the list of the places with incoming number.
     * For car place should be one place in the list.
     * for track place should be 'n' places in the list.
     *
     * @param placeNum - the place number.
     * @return - the list.
     */
    private List<ParkPlace> findPlaces(int placeNum) {
        List<ParkPlace> result = new ArrayList<>();
        for (ParkPlace[] row : scheme) {
            for (ParkPlace place : row) {
                if (place.getPlaceNum() == placeNum) {
                    result.add(place);
                }
            }
        }
        return result;
    }

    /**
     * the method is used as a template for methods getAllFreePlaces, getAllCarFreePlaces and getAllTrackFreePlaces.
     *
     * @param function - the parameters for searching.
     * @return - the list of the found park places.
     */
    private List<ParkPlace> findFree(BiFunction<Character, Character, Boolean> function) {
        List<ParkPlace> list = new ArrayList<>();
        for (ParkPlace[] row : this.scheme) {
            for (ParkPlace place : row) {
                if (function.apply(place.getStatus(), place.getType())) {
                    list.add(place);
                }
            }
        }
        return list;
    }
}
