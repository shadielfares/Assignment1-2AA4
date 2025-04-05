package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Function: Encapsulate the Maze to its own class to support Open-Closed and SR
 * principle
 * Param None
 */
public class Maze {
    private final List<List<Character>> maze = new ArrayList<List<Character>>();
    private static final Logger logger = LogManager.getLogger();

    private List<Integer> entryPosition = new ArrayList<>();
    private List<Integer> exitPosition = new ArrayList<>();

    public Maze(String filepath) {
        read(filepath);
    }

    private void read(String filepath) {
        try {
            logger.info("**** Reading the maze from file " + filepath);
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            while ((line = reader.readLine()) != null) {
                maze.add(new ArrayList<>());

                // Get the index of the current row
                int rowIdx = maze.size() - 1;

                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.trace("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.trace("PASS ");
                    }
                    // Fill each row index with its respective tile
                    maze.get(rowIdx).add(line.charAt(idx));
                }
                logger.trace(System.lineSeparator());
            }
            // Create the entry and exit positions
            findEntry();
            findExit();
            reader.close();
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred while reading the file/!: ");
        }
    }

    public List<Character> currentRow(int rowIdx) {
        return maze.get(rowIdx);
    }

    public Integer mazeSize() {
        return maze.size();
    }

    // Method to find entry point
    public List<Integer> findEntry() {
        for (int i = 0; i < mazeSize(); i++) {
            List<Character> row = currentRow(i);
            if (!row.getFirst().equals('#')) {
                entryPosition.add(i);
                entryPosition.add(0);
                
            }
        }
        return entryPosition; // Return empty if no exit found
    }

    // Method to find exit point
    public List<Integer> findExit() {
        for (int i = 0; i < mazeSize(); i++) {
            List<Character> row = currentRow(i);
            if (!row.getLast().equals('#')) {
                exitPosition.add(i);
                exitPosition.add(currentRow(i).size() - 1);
            }
        }
        return exitPosition; // Return empty if no exit found
    }

    public List<Integer> getEntryPosition() {
        return entryPosition;
    }

    public List<Integer> getExitPosition() {
        return exitPosition;
    }

    // Helper method to check if the exit has been reached
    public boolean reachedExit(int currentRow, int currentCol) {
        return currentRow == exitPosition.get(0) && currentCol == exitPosition.get(1);
    }
}