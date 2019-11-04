package ru.job4j.parking;

public class ParkPlace {

    /**
     * type - parking place type.
     * status - free or occupied and what type of vehicle.
     * carNumber - the state numeric sign.
     * placeNum - the place number.
     */
    private int placeNum;
    private char type;
    private char status;
    private String carNumber;

    public ParkPlace(int placeNum, char type, char status) {
        this.placeNum = placeNum;
        this.type = type;
        this.status = status;
    }

    public ParkPlace(int placeNum, char type, String carNumber) {
        this.placeNum = placeNum;
        this.type = type;
        this.carNumber = carNumber;
    }

    public ParkPlace(String carNumber) {
        this.carNumber = carNumber;
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

    public void setPlaceNum(int placeNum) {
        this.placeNum = placeNum;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
