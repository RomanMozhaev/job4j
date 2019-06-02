package ru.job4j.chess;

import ru.job4j.chess.exception.FigureNotFoundException;
import ru.job4j.chess.exception.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.firuges.movement.WayOccupation;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {

    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        Cell[] steps;
        boolean rst = false;
        int index;
        try {
             index = this.findBy(source);
            try {
                steps = this.figures[index].way(source, dest);

                try {
                    final WayOccupation occupation = new WayOccupation(steps, this.figures);
                    occupation.isOccupied();
                    if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                        rst = true;
                        this.figures[index] = this.figures[index].copy(dest);
                    }
                } catch (OccupiedWayException e) {
                    System.out.println("OccupiedWayException");
                }
            } catch (ImpossibleMoveException e) {
                System.out.println("ImpossibleMoveException");
            }
        } catch (FigureNotFoundException fnfe) {
            System.out.println("Figure was not found");
            }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        if (rst == -1) {
            throw new FigureNotFoundException();
        }
        return rst;
    }
}
