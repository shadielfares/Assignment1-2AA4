package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class PathTraversal implements iPathTraversal {

    private static final Logger logger = LogManager.getLogger();
    MazeArray mazeArray = new MazeArray();
    PathSequence pathSequence = new PathSequence();

    //Method to find exit point
    @Override
    public ArrayList<Integer> findExit(){
        ArrayList<Integer> exitPosition = new ArrayList<Integer>();

        for (int i =0; i < mazeArray.mazeSize(); i++){
            ArrayList<Character> row = mazeArray.currentRow(i);
            if (row.get(mazeArray.mazeSize() - 1).equals(' ')){
                exitPosition.add(i);
                exitPosition.add(mazeArray.mazeSize() - 1);
                return exitPosition;
            }
        }
        return exitPosition; // Return empty if no exit found
    }

    // Path Traversal Methods
    //Method to find entry point
    @Override
    public ArrayList<Integer> findEntry(){
        ArrayList<Integer> entryPosition = new ArrayList<Integer>();
        for (int i =0; i < mazeArray.mazeSize();i++){
            ArrayList<Character> row = mazeArray.currentRow(i);
            if (row.getFirst().equals(' ')){
                logger.info("Entry is located at: [{},0]", i);
                entryPosition.add(i);
                entryPosition.add(0);
                return entryPosition;
            }
        }
        return entryPosition; // Return empty if no exit found
    }

    // Helper method to check if the exit has been reached
    public boolean reachedExit(int currentRow, int currentCol) {
        ArrayList<Integer> exitPosition = findExit();
        return currentRow == exitPosition.get(0) && currentCol == exitPosition.get(1);
    }

    // Method to handle movement logic
    @Override
    public ArrayList<Integer> move(int i, int j) {
        ArrayList<Integer> newPosition = new ArrayList<>();

        if (canMoveTo(i, j + 1)) { // Check if we can move right
            pathSequence.setSequence("R");
            newPosition.add(i);
            newPosition.add(j + 1);
        } else if (canMoveTo(i + 1, j)) { // Check if we can move down
            pathSequence.setSequence("D");
            newPosition.add(i + 1);
            newPosition.add(j);
        } else if (canMoveTo(i, j - 1)) { // Check if we can move left
            pathSequence.setSequence("L");
            newPosition.add(i);
            newPosition.add(j - 1);
        } else if (canMoveTo(i - 1, j)) { // Check if we can move up
            pathSequence.setSequence("U");
            newPosition.add(i - 1);
            newPosition.add(j);
        } else {
            throw new IllegalStateException("No valid moves available. Check maze boundaries or logic.");
        }
        return newPosition; // Return the updated position
    }


    // Helper method to check if a move is valid
    @Override
    public boolean canMoveTo(int i, int j) {
        // Check if the position is within bounds and is a moveable space
        return i >= 0 && i < mazeArray.mazeSize() &&
                j >= 0 && j < mazeArray.currentRow(i).size() &&
                mazeArray.currentRow(i).get(j).equals(' '); // Ensure it's a moveable space
    }

    public void pathTraversal() {
        ArrayList<Integer> entryIndex = findEntry(); // Find the entry point
        int currentRow = entryIndex.get(0); // Row index of entry
        int currentCol = entryIndex.get(1); // Column index of entry

        logger.info("**** Computing path");

        // Traverse the maze until the exit is reached
        while (!reachedExit(currentRow, currentCol)) {
            ArrayList<Integer> newPosition = move(currentRow, currentCol); // Move to the next position
            currentRow = newPosition.get(0); // Update row
            currentCol = newPosition.get(1); // Update column
        }

        pathSequence.printSequence();
    }
}
