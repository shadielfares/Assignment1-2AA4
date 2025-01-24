package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder{
    private static ArrayList<ArrayList<Character>> mazeArray = new ArrayList<ArrayList<Character>>();
    private static final Logger logger = LogManager.getLogger();

    public ArrayList<ArrayList<Character>> getMazeArray(){
        return mazeArray;
    }

    public void setMazeArray(ArrayList<ArrayList<Character>> filledMazeArray){
        mazeArray = filledMazeArray; 
    }

    public void findEntry(){
        for (int i =0; i < mazeArray.size(); i++){
            ArrayList<Character> row = mazeArray.get(i);
            if (row.get(0).equals(' ')){
                logger.info(String.format("Entry is located at: [%d,0]", i));
            }
        }
    }
}
