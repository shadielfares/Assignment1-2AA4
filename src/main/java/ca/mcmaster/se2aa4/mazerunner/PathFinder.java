package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder{
    private static ArrayList<ArrayList<Character>> mazeArray = new ArrayList<ArrayList<Character>>();
    private static final Logger logger = LogManager.getLogger();

    private static String sequence = "";

    //Sequence Methods
    public String getSequence(){
        return sequence;
    }

    public void setSequence(String newValue){
        sequence += newValue;
    }

    //Maze Methods
    public ArrayList<ArrayList<Character>> getMazeArray(){
        return mazeArray;
    }

    public void setMazeArray(ArrayList<ArrayList<Character>> filledMazeArray){
        mazeArray = filledMazeArray; 
    }


    // Path Traversal Methods
    //Method to find entry point
    public ArrayList<Integer> findEntry(){
        ArrayList<Integer> entryPosition = new ArrayList<Integer>();
        for (int i =0; i < mazeArray.size(); i++){
            ArrayList<Character> row = mazeArray.get(i);
            if (row.get(0).equals(' ')){
                logger.info(String.format("Entry is located at: [%d,0]", i));
                entryPosition.add(i);
                entryPosition.add(0);
                return entryPosition;
            }
        }
        return entryPosition; // Return empty if no exit found
    }

    //Method to find exit point
    public ArrayList<Integer> findExit(){
        ArrayList<Integer> exitPosition = new ArrayList<Integer>();

        for (int i =0; i < mazeArray.size(); i++){
            ArrayList<Character> row = mazeArray.get(i);
            if (row.get(mazeArray.size() - 1).equals(' ')){
                logger.info(String.format("Exit is located at: [%d, %d]", i, mazeArray.size() -1));
                exitPosition.add(i);
                exitPosition.add(mazeArray.size() - 1);
                return exitPosition;
            }
        }
        return exitPosition; // Return empty if no exit found
    }

    // Helper method to check if the exit has been reached
    private boolean reachedExit(int currentRow, int currentCol) {
        ArrayList<Integer> exitPosition = findExit();
        return currentRow == exitPosition.get(0) && currentCol == exitPosition.get(1);
    }

    // Method to handle movement logic
    private ArrayList<Integer> move(int i, int j) {
        ArrayList<Integer> newPosition = new ArrayList<>();

        if (canMoveTo(i, j + 1)) { // Check if we can move right
            setSequence("R"); 
            newPosition.add(i);
            newPosition.add(j + 1); 
        } else if (canMoveTo(i + 1, j)) { // Check if we can move down
            setSequence("D"); 
            newPosition.add(i + 1);
            newPosition.add(j); 
        } else if (canMoveTo(i, j - 1)) { // Check if we can move left
            setSequence("L");
            newPosition.add(i);
            newPosition.add(j - 1); 
        } else if (canMoveTo(i - 1, j)) { // Check if we can move up
            setSequence("U"); 
            newPosition.add(i - 1);
            newPosition.add(j); 
        } else {
            throw new IllegalStateException("No valid moves available. Check maze boundaries or logic.");
        }

        return newPosition; // Return the updated position
    }

    // Helper method to check if a move is valid
    private boolean canMoveTo(int i, int j) {
        // Check if the position is within bounds and is a moveable space
        return i >= 0 && i < mazeArray.size() && 
            j >= 0 && j < mazeArray.get(i).size() &&
            mazeArray.get(i).get(j).equals(' '); // Ensure it's a moveable space
    }


    public void pathTraversal() {
        ArrayList<Integer> entryIndex = findEntry(); // Find the entry point
        int currentRow = entryIndex.get(0); // Row index of entry
        int currentCol = entryIndex.get(1); // Column index of entry

        // Traverse the maze until the exit is reached
        while (!reachedExit(currentRow, currentCol)) {
            ArrayList<Integer> newPosition = move(currentRow, currentCol); // Move to the next position
            currentRow = newPosition.get(0); // Update row
            currentCol = newPosition.get(1); // Update column
        }

        logger.info("Traversal complete. Path sequence: " + getSequence());
    }
}
