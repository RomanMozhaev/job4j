package ru.job4j.parking;

/**
 * the class of the park place on the parking.
 */
public class ParkPlace {

    /**
     * placeNum - the place number.
     */
    private int placeNum;
    /**
     * type - parking place type.
     */
    private char type;
    /**
     * status - free or occupied
     */
    private char status;
    /**
     * the state numeric sign of the parked car or "none".
     */
    private String carNumber;

    /**
     * the main constructor.
     * @param placeNum - the place number.
     * @param type - the place type.
     */
    public ParkPlace(int placeNum, char type) {
        this.placeNum = placeNum;
        this.type = type;
        this.status = 'F';
        this.carNumber = "none";
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public char getType() {
        return type;
    }

    public char getStatus() {
        return status;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
