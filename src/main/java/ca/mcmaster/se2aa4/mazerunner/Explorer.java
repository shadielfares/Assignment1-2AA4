package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.commands.ForwardCommand;
import ca.mcmaster.se2aa4.mazerunner.commands.TurnLeftCommand;
import ca.mcmaster.se2aa4.mazerunner.commands.TurnRightCommand;

public class Explorer {

    // private static final Logger logger = LogManager.getLogger();
    protected Position position;
    private Maze maze;
    private CommandHistory history;

    private static final Logger logger = LogManager.getLogger();


    public Explorer(Maze maze, CommandHistory history) {
        this.maze = maze;
        this.position = new Position();
        this.history = history;
    }

    // Helper Methods
    // Leaky Abstraction deliberate Technical Debt
    public void releaseHistory() {
        history.printSequence();
    }

    public CommandHistory getHistory() {
        return history;
    }

    //Deep Copy
    public Position copyPosition() {
        Position copyPosition = position;
        return copyPosition;
    }

    public void setPosition(int newRow, int newCol, Direction newDirection) {
        position.set(newRow, newCol, newDirection);
    }

    public boolean canMoveTo(int i, int j) {
        // Check if the position is within bounds and is a moveable space
        return i >= 0 && i < maze.mazeSize() &&
                j >= 0 && j < maze.currentRow(i).size() &&
                !maze.currentRow(i).get(j).equals('#'); // Ensure it's a moveable space
    }

    // Command
    public void executeCommand(Command command) {
        command.execute();
        history.appendSequence(Character.toString(command.getInstructionID()));
    }

    // Path Traversal Methods

    private boolean tryMove(int currentRow, int currentCol, Direction direction) {
        int nextRow = direction.getNextRow(currentRow);
        int nextCol = direction.getNextCol(currentCol);
    
        logger.info(String.format("Attempting to move from (%d, %d) to (%d, %d) in direction %s", 
            currentRow, currentCol, nextRow, nextCol, direction));
    
        // Check if the move is valid
        if (canMoveTo(nextRow, nextCol)) {
            logger.info(String.format("Move to (%d, %d) is valid. Checking direction adjustments.", nextRow, nextCol));
    
            // Turn in the required direction if necessary
            Direction currentDirection = position.getHeading();
            if (direction == currentDirection.turnRight()) {
                logger.info("Turning right to align with the desired direction.");
                executeCommand(new TurnRightCommand(this));
            } else if (direction == currentDirection.turnLeft()) {
                logger.info("Turning left to align with the desired direction.");
                executeCommand(new TurnLeftCommand(this));
            }
    
            // Move forward
            logger.info("Moving forward.");
            executeCommand(new ForwardCommand(this));
            logger.info(String.format("Successfully moved to (%d, %d) in direction %s", 
                position.getRow(), position.getCol(), position.getHeading()));
            return true; // Move was successful
        }
    
        logger.warn(String.format("Move to (%d, %d) is not valid. Staying at (%d, %d).", nextRow, nextCol, currentRow, currentCol));
        return false; // Move was not successful
    }

    public void move(int currentRow, int currentCol, Direction currentDirection) {
        // 1. Try turning right and moving forward
        if (tryMove(currentRow, currentCol, currentDirection.turnRight()))
            return;

        // 2. Try moving forward in the current direction
        if (tryMove(currentRow, currentCol, currentDirection))
            return;

        // 3. Try turning left and moving forward
        if (tryMove(currentRow, currentCol, currentDirection.turnLeft()))
            return;

        // 4. Turn around if all other options are blocked
        executeCommand(new TurnRightCommand(this));
        executeCommand(new TurnRightCommand(this));
    }
}