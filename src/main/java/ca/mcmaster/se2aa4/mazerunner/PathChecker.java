package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Function: PathChecker validates a given path through the CLI.
 * It processes moves, checks validity, and determines if the exit is reached.
 * Param path The sequence of moves to be validated.
 */
public class PathChecker extends PathTraversal {
    private final String sequence;

    public PathChecker(String path) {
        this.sequence = path;
    }

    public void checkPath() {
        // Start from the maze's entry point
        ArrayList<Integer> currentPosition = findEntry();
        if (currentPosition.isEmpty()) {
            System.out.println("No entry point found in the maze!");
            return; // Exit if no entry is found
        }

        int currentRow = currentPosition.get(0);
        int currentCol = currentPosition.get(1);
        Direction currentDirection = Direction.RIGHT; // Default direction for starting

        // Parse the sequence into individual steps
        ArrayList<String> commands = parseSequence(sequence);

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
                    int nextRow = getNextRow(currentRow, currentDirection);
                    int nextCol = getNextCol(currentCol, currentDirection);

                    if (!canMoveTo(nextRow, nextCol)) {
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
        if (reachedExit(currentRow, currentCol)) {
            System.out.println("Correct path!");
        } else {
            System.out.println("Incorrect path!");
        }
    }

    private ArrayList<String> parseSequence(String sequence) {
        ArrayList<String> commands = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d*[FRL]"); // Match commands for F, R, L with optional numeric prefix
        Matcher matcher = pattern.matcher(sequence);

        // Extract commands from the input sequence
        while (matcher.find()) {
            commands.add(matcher.group());
        }

        return commands;
    }
}