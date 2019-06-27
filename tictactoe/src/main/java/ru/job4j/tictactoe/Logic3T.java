package ru.job4j.tictactoe;

import java.util.List;
import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;
    List<StartCoordinate> coordinates = List.of(
            new StartCoordinate(0, 0, 1, 0),
            new StartCoordinate(0, 0, 0, 1),
            new StartCoordinate(0, 0, 1, 1),
            new StartCoordinate(1, 0, 0, 1),
            new StartCoordinate(0, 1, 1, 0),
            new StartCoordinate(2, 0, 0, 1),
            new StartCoordinate(0, 2, 1, 0),
            new StartCoordinate(2, 0, -1, 1)
    );

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean checkWinner(Predicate<Figure3T> predicate) {
        boolean result = false;
        int i = 0;
        while (result == false && i < coordinates.size()) {
            int startX = coordinates.get(i).getCoordinateX();
            int startY = coordinates.get(i).getCoordinateY();
            int deltaX = coordinates.get(i).getDeltaX();
            int deltaY = coordinates.get(i).getDeltaY();
            result = this.fillBy(predicate, startX, startY, deltaX, deltaY);
            i++;
        }
        return result;
    }

    public boolean isWinnerX() {
        return checkWinner(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return checkWinner(Figure3T::hasMarkO);
    }

    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i < this.table.length - 1; i++) {
            for (int j = 0; j < this.table.length - 1; j++) {
                Figure3T cell = this.table[i][j];
                if (!cell.hasMarkO() || !cell.hasMarkX()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
