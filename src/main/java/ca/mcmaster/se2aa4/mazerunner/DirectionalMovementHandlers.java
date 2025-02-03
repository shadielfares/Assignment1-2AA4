package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

class MoveRightHandler implements MovementHandler {
    @Override
    public boolean canMove(int currentRow, int currentCol, PathTraversal pathTraversal) {
        return pathTraversal.canMoveTo(currentRow, currentCol + 1);
    }

    @Override
    public ArrayList<Integer> move(int currentRow, int currentCol, PathTraversal pathTraversal) {
        // Update the position directly for a right move
        ArrayList<Integer> newPosition = new ArrayList<>();
        newPosition.add(currentRow);
        newPosition.add(currentCol + 1);
        return newPosition;
    }
}

class MoveDownHandler implements MovementHandler {
    @Override
    public boolean canMove(int currentRow, int currentCol, PathTraversal pathTraversal) {
        return pathTraversal.canMoveTo(currentRow + 1, currentCol);
    }

    @Override
    public ArrayList<Integer> move(int currentRow, int currentCol, PathTraversal pathTraversal) {
        // Update the position directly for a down move
        ArrayList<Integer> newPosition = new ArrayList<>();
        newPosition.add(currentRow + 1);
        newPosition.add(currentCol);
        return newPosition;
    }
}

class MoveLeftHandler implements MovementHandler {
    @Override
    public boolean canMove(int currentRow, int currentCol, PathTraversal pathTraversal) {
        return pathTraversal.canMoveTo(currentRow, currentCol - 1);
    }

    @Override
    public ArrayList<Integer> move(int currentRow, int currentCol, PathTraversal pathTraversal) {
        // Update the position directly for a left move
        ArrayList<Integer> newPosition = new ArrayList<>();
        newPosition.add(currentRow);
        newPosition.add(currentCol - 1);
        return newPosition;
    }
}

class MoveUpHandler implements MovementHandler {
    @Override
    public boolean canMove(int currentRow, int currentCol, PathTraversal pathTraversal) {
        return pathTraversal.canMoveTo(currentRow - 1, currentCol);
    }

    @Override
    public ArrayList<Integer> move(int currentRow, int currentCol, PathTraversal pathTraversal) {
        // Update the position directly for an up move
        ArrayList<Integer> newPosition = new ArrayList<>();
        newPosition.add(currentRow - 1);
        newPosition.add(currentCol);
        return newPosition;
    }
}