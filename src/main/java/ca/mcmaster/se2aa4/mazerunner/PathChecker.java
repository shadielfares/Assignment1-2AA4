package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class PathChecker extends PathTraversal{
    //For each character in the Provided Path, we will call move
    private static String sequence;
    PathSequence pathSequence = new PathSequence();

    public PathChecker(String path) {
        PathChecker.sequence = path;
    }

    public void checkPath() {
        MovementFactory movementFactory = new MovementFactory();

        ArrayList<Integer> currentPosition = findEntry(); // Start from the entry point
        int currentRow = currentPosition.get(0); // Initialize the current row
        int currentCol = currentPosition.get(1); // Initialize the current column

        for (Character move : pathSequence.normalizeSequence(PathChecker.sequence).toCharArray()) {
            MovementHandler handler = movementFactory.getHandler(move); // Get the handler for the move
            if (handler.canMove(currentRow, currentCol, this)) {
                ArrayList<Integer> newPosition = handler.move(currentRow, currentCol, this); // Execute the move
                currentRow = newPosition.get(0);
                currentCol = newPosition.get(1);
            } else {
                return; // Exit on invalid move
            }
        }

        // After finishing all moves, check if we reached the exit
        if (reachedExit(currentRow, currentCol)) {
            System.out.println("Correct path!");
        } else {
            System.out.println("Incorrect path!");
        }
    }
}
