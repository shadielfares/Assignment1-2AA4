package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Function: PathChecker validates a given path through the CLI.
 * It processes moves, checks validity, and determines if the exit is reached.
 * Param path The sequence of moves to be validated.
 */
public class PathChecker {
    private Maze maze;
    private final String sequence;
    private Explorer explorer; // constructor in Main

    public PathChecker(String path, Maze maze, CommandHistory history) {
        this.sequence = path;
        this.maze = maze;
        this.explorer = new Explorer(maze, history);
    }

    public void checkPath() {
        // Start from the maze's entry point
        List<Integer> currentPosition = maze.findEntry();
        if (currentPosition.isEmpty()) {
            System.out.println("No entry point found in the maze!");
            return; // Exit if no entry is found
        }

        int currentRow = currentPosition.get(0);
        int currentCol = currentPosition.get(1);
        Direction currentDirection = Direction.RIGHT; // Default direction for starting

        // Parse the sequence into individual steps
        ArrayList<String> commands = explorer.getHistory().parseSequence(sequence);

        for (String command : commands) {
            int steps = 1; // Default to 1 step for non-factorized moves
            char moveType = command.charAt(command.length() - 1);

            // Extract number of steps if factorized (e.g., 4F)
            if (command.length() > 1) {
                steps = Integer.parseInt(command.substring(0, command.length() - 1));
            }

            // Process each step
            for (int i = 0; i < steps; i++) {
                if (moveType == 'F') {
                    // Move forward in the current direction
                    int nextRow = currentDirection.getNextRow(currentRow);
                    int nextCol = currentDirection.getNextCol(currentCol);

                    if (!explorer.canMoveTo(nextRow, nextCol)) {
                        System.out.println("Incorrect path!");
                        return;
                    }

                    // Update position
                    currentRow = nextRow;
                    currentCol = nextCol;
                } else if (moveType == 'R') {
                    // Turn right
                    currentDirection = currentDirection.turnRight();
                } else if (moveType == 'L') {
                    // Turn left
                    currentDirection = currentDirection.turnLeft();
                }
            }
        }

        // Check if the final position matches the exit
        // EDIT: Remove this to exist for the Unit Tests
        // Reuse from RHA
        if (maze.reachedExit(currentRow, currentCol)) {
            System.out.println("Correct path!");
        } else {
            System.out.println("Incorrect path!");
        }
    }
}