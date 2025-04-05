package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

/*
 * This is a violation of Leaky Abstraction..
 * Prudent, Deliberate Technical Debt.
 */

public class Position {
    private static List<Object> position = new ArrayList<Object>();

    
    public void set(int currentRow, int currentCol,  Direction direction) {
        if (!position.isEmpty()) {
            clear();
        }
        position.add(currentRow);
        position.add(currentCol);
        position.add(direction);
    }

    public static void clear() {
        position.clear();
    }

    public int getRow() {
        return (int) position.getFirst();
    }

    public int getCol() {
        return (int) position.get(1);
    }

    public Direction getHeading() {
        return (Direction) position.getLast();
    }

}
