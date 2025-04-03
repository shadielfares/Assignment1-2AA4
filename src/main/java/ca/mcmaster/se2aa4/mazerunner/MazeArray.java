package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Function: Encapsulate the MazeArray to its own class to support Open-Closed and SR principle
 * Param None
 */
public class MazeArray {
    private static final List<List<Character>> mazeArray = new ArrayList<List<Character>>();

    public List<Character> currentRow(int rowIdx) {
        return mazeArray.get(rowIdx);
    }

    public Integer mazeSize() {
        return mazeArray.size();
    }

    public List<List<Character>> getMazeArray(){
        return mazeArray;
    }
}
