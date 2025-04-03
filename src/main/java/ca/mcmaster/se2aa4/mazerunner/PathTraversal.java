package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PathTraversal implements iPathTraversal {

    private static final Logger logger = LogManager.getLogger();
    MazeArray mazeArray = new MazeArray();
    PathSequence pathSequence = new PathSequence();
    List<Integer> exitPosition = new ArrayList<>();
    List<Integer> entryPosition = new ArrayList<>();

    public PathTraversal(){
        this.exitPosition = findExit();
        this.entryPosition = findEntry();
    }
    
    //Method to find exit point
    @Override
    public List<Integer> findExit(){
        for (int i =0; i < mazeArray.mazeSize(); i++){
            List<Character> row = mazeArray.currentRow(i);
            if (!row.getLast().equals('#')){
                exitPosition.add(i);
                exitPosition.add(mazeArray.currentRow(i).size() - 1);
                return exitPosition;
            }
        }
        return exitPosition; // Return empty if no exit found
    }

    // Path Traversal Methods
    //Method to find entry point
    @Override
    public List<Integer> findEntry(){
        for (int i =0; i < mazeArray.mazeSize(); i++){
            List<Character> row = (ArrayList<Character>) mazeArray.currentRow(i);
            if (!row.getFirst().equals('#')){
                entryPosition.add(i);
                return entryPosition;
            }
        }
        return entryPosition; // Return empty if no exit found
    }

    // Helper method to check if the exit has been reached
    public boolean reachedExit(int currentRow, int currentCol) {
        return currentRow == exitPosition.get(0) && currentCol == exitPosition.get(1);
    }

    // Helper method to check if a move is valid
    @Override
    public boolean canMoveTo(int i, int j) {
        // Check if the position is within bounds and is a moveable space
        return i >= 0 && i < mazeArray.mazeSize() &&
                j >= 0 && j < mazeArray.currentRow(i).size() &&
                !mazeArray.currentRow(i).get(j).equals('#'); // Ensure it's a moveable space
    }

    public int getNextRow(int currentRow, Direction direction) {
        if (direction == Direction.UP) return currentRow - 1;
        if (direction == Direction.DOWN) return currentRow + 1;
        return currentRow; // No change for LEFT or RIGHT
    }

    public int getNextCol(int currentCol, Direction direction) {
        if (direction == Direction.LEFT) return currentCol - 1;
        if (direction == Direction.RIGHT) return currentCol + 1;
        return currentCol; // No change for UP or DOWN
    }

    public ArrayList<Object> move(int currentRow, int currentCol, Direction currentDirection) {
        ArrayList<Object> moveResult = new ArrayList<>();

        // 1. Try turning right and moving forward
        Direction rightDirection = currentDirection.turnRight();
        int rightRow = getNextRow(currentRow, rightDirection);
        int rightCol = getNextCol(currentCol, rightDirection);
        if (canMoveTo(rightRow, rightCol)) {
            pathSequence.appendSequence("R"); // Log the right turn
            pathSequence.appendSequence("F"); // Log moving forward
            moveResult.add(rightRow); // New row
            moveResult.add(rightCol); // New column
            moveResult.add(rightDirection); // New direction
            return moveResult;
        }

        // 2. Try moving forward in the current direction
        int forwardRow = getNextRow(currentRow, currentDirection);
        int forwardCol = getNextCol(currentCol, currentDirection);
        if (canMoveTo(forwardRow, forwardCol)) {
            pathSequence.appendSequence("F"); // Log moving forward
            moveResult.add(forwardRow); // New row
            moveResult.add(forwardCol); // New column
            moveResult.add(currentDirection); // Keep direction
            return moveResult;
        }

        // 3. Try turning left and moving forward
        Direction leftDirection = currentDirection.turnLeft();
        int leftRow = getNextRow(currentRow, leftDirection);
        int leftCol = getNextCol(currentCol, leftDirection);
        if (canMoveTo(leftRow, leftCol)) {
            pathSequence.appendSequence("L"); // Log the left turn
            pathSequence.appendSequence("F"); // Log moving forward
            moveResult.add(leftRow); // New row
            moveResult.add(leftCol); // New column
            moveResult.add(leftDirection); // New direction
            return moveResult;
        }

        // 4. Turn around if all other options are blocked
        Direction reverseDirection = currentDirection.turnRight().turnRight();
        pathSequence.appendSequence("R"); // Log the first right turn
        pathSequence.appendSequence("R"); // Log the second right turn (180° turn)
        moveResult.add(currentRow); // Stay in the same row
        moveResult.add(currentCol); // Stay in the same column
        moveResult.add(reverseDirection); // Update to reversed direction
        return moveResult;
    }

    public void pathTraversal(boolean print) {
        // DUPLICATE CODE
        List<Integer> entryIndex = entryPosition; // Find the entry position
        if (entryIndex.isEmpty()) {
            logger.error("No entry point found in the maze.");
            return; // Abort if no entry point exists
        }

        // Initialize position and direction
        int currentRow = entryIndex.getFirst();
        int currentCol = 0; // Starting Column is arbritary

        Direction currentDirection = Direction.RIGHT; // Default starting direction

        logger.info("**** Starting path traversal ");

        // Traverse the maze until reaching the exit
        while (!reachedExit(currentRow, currentCol)) {
            // System.out.printf("X: {0}, Y: {1}, Direction: {2}\n", currentRow, currentCol, currentDirection);
            ArrayList<Object> moveResult = move(currentRow, currentCol, currentDirection);
            logger.info("Is this run1?");
            // Update current position and direction
            currentRow = (int) moveResult.get(0); // New row
            currentCol = (int) moveResult.get(1); // New column
            logger.info("Is this run2?");
            currentDirection = (Direction) moveResult.get(2); // New direction
            if (currentRow == 0 && currentCol == 1){
                break;
            }
        }

        logger.info("**** Exit reached at (" + currentRow + ", " + currentCol + ")");
        if (print) {
            pathSequence.printSequence(); // Output the path taken
        } else{
            return;
        }
    }
}