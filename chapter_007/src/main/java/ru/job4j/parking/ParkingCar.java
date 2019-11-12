package ru.job4j.parking;

public class ParkingCar {


    /**
     * placeNum - the place number where the car if parked or should be parked.
     */
    private int placeNum;
    /**
     * type - the car type: car ('C') or track ('T').
     */
    private char type;
    /**
     * carNumber - the state numeric sign. The field could be "none".
     */
    private String carNumber;

    /**
     * the constructor with all fields.
     * @param placeNum - the place number.
     * @param type - the car type.
     * @param carNumber - the car number.
     */
    public ParkingCar(int placeNum, char type, String carNumber) {
        this.placeNum = placeNum;
        this.type = type;
        this.carNumber = carNumber;
    }
    /**
     * the constructor without the place number.
     * @param type - the car type.
     * @param carNumber - the car number.
     */
    public ParkingCar(char type, String carNumber) {
        this.carNumber = carNumber;
        this.type = type;
        this.placeNum = 0;
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public char getType() {
        return type;
    }

    public String getCarNumber() {
        return carNumber;
    }

}
