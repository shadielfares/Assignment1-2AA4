package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgorithm {
    private Maze maze;
    private Explorer explorer;
    private final List<Integer> entryPosition;

    private static final Logger logger = LogManager.getLogger();

    public RightHandAlgorithm(Maze maze, CommandHistory history) {
        if (maze == null) {
            throw new IllegalArgumentException("Maze cannot be null");
        }

        this.maze = maze;
        this.entryPosition = maze.findEntry();

        if (entryPosition == null || entryPosition.size() < 2) {
            throw new IllegalStateException("No valid entry point found in the maze.");
        }

        this.explorer = new Explorer(maze, history);
    }

    public void pathTraversal(boolean print) {
        // DUPLICATE CODE
        if (entryPosition.isEmpty()) {
            logger.error("No entry point found in the maze.");
            return; // Abort if no entry point exists
        }

        // Initialize position and direction

        int currentRow = entryPosition.getFirst(); //Starting 2
        int currentCol = entryPosition.getLast(); // Starting Column is arbritary as its always 0

        Direction currentDirection = Direction.RIGHT; // Default starting direction

        // Initialize explorer position as well
        explorer.setPosition( currentRow, currentCol,  currentDirection);

        logger.info("**** Starting path traversal ");
        
        // Traverse the maze until reaching the exit, stop one before the exit
        while (!maze.reachedExit(currentRow, currentCol)) {
            
            // Can get this from Command History, only thing I will get though is the letter
            explorer.move(currentRow, currentCol, currentDirection);
            Position newPosition = explorer.copyPosition();
            currentCol = newPosition.getCol();
            currentRow = newPosition.getRow();
            currentDirection = newPosition.getHeading();
            logger.debug(String.format("Position: (%d, %d), Direction: %s", currentRow, currentCol, currentDirection));
        }

        logger.info("**** Exit reached at (" + currentRow + ", " + currentCol + ")");
        if (print) {
            explorer.releaseHistory(); // Output the path taken
        } else {
            return;
        }
    }
}