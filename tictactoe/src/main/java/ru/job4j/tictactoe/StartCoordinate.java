package ru.job4j.tictactoe;

public class StartCoordinate {

    private int coordinateX;
    private int coordinateY;
    private int deltaX;
    private int deltaY;

    public StartCoordinate(int coordinateX, int coordinateY, int deltaX, int deltaY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
