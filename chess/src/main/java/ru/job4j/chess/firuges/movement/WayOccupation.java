package ru.job4j.chess.firuges.movement;

import ru.job4j.chess.exception.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * check if it possible to move along the way.
 */
public class WayOccupation {

    private Cell[] cellWay;
    private Figure[] figures;

    public WayOccupation(Cell[] cellWay, Figure[] figures) {
        this.cellWay = cellWay;
        this.figures = figures;
    }

    /**
     * if there is a Figure on the way it throws an exception.
     * @throws OccupiedWayException
     */
    public void isOccupied() throws OccupiedWayException {
        for (int i = 0; i < this.cellWay.length; i++) {
            for (int j = 0; j < figures.length; j++) {
                if (cellWay[i] == figures[j].position()) {
                    throw (new OccupiedWayException());
                }
            }
        }
    }
}
