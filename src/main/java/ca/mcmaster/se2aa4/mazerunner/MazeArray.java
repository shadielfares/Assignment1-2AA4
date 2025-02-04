package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

/**
 * Function: Encapsulate the MazeArray to its own class to support Open-Closed and SR principle
 * Param None
 */
public class MazeArray {
    private static final ArrayList<ArrayList<Character>> mazeArray = new ArrayList<ArrayList<Character>>();

    public ArrayList<Character> currentRow(int rowIdx) {
        return mazeArray.get(rowIdx);
    }

    public Integer mazeSize() {
        return mazeArray.size();
    }

    public ArrayList<ArrayList<Character>> getMazeArray(){
        return mazeArray;
    }
}
