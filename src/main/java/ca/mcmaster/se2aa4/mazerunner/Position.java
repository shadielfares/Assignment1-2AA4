package ca.mcmaster.se2aa4.mazerunner;

/*
 * This is a violation of Leaky Abstraction..
 * Prudent, Deliberate Technical Debt.
 */

public class Position {
    private int row;
    private int col;
    private Direction heading;

    public Position(int row, int col, Direction heading) {
        this.row = row;
        this.col = col;
        this.heading = heading;
    }


    // Getters
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Direction getHeading() {
        return heading;
    }

    // Setters
    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setHeading(Direction heading) {
        this.heading = heading;
    }

}