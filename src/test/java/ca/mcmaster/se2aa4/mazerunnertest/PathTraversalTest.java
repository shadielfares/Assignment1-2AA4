package ca.mcmaster.se2aa4.mazerunnertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.RightHandAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.CommandHistory;
import ca.mcmaster.se2aa4.mazerunner.Explorer;
import ca.mcmaster.se2aa4.mazerunner.Position;

/*
With the current implementation, the tests were not working as expected.
The reason is that the maze is not being initialized properly.

The tests should be able to run independently.
 */
public class PathTraversalTest {
    @Test
    @DisplayName("Find Entrance")
    public void testFindEntrance() {
        Maze maze = new Maze("./examples/straight.maz.txt"); // Initialize maze here
        List<Integer> entry = maze.getEntryPosition();

        assertNotNull(entry, "List should not be null.");
        assertEquals(Arrays.asList(2,0), entry);
    }

    @Test
    @DisplayName("Find Exit")
    public void testFindExit() {
        Maze maze = new Maze("./examples/straight.maz.txt"); // Initialize maze here
        List<Integer> exit = maze.getExitPosition();

        assertNotNull(exit, "List should not be null.");
        assertEquals(Arrays.asList(2, 4), exit);
    }

    @Test
    @DisplayName("Path Traversal: Factorized Sequence Test on Direct Maze")
    public void testFactorizedSequenceDirectMaze() {
        Maze maze = new Maze("./examples/straight.maz.txt"); // Initialize maze here
        CommandHistory history = new CommandHistory();

        RightHandAlgorithm explorer = new RightHandAlgorithm(maze, history);

        explorer.pathTraversal(false); // Now inside a constructor
        String sequence = history.convertToFactorize();

        assertNotNull(sequence, "Sequence should not be null.");
        assertEquals("4F", sequence);
    }

    @Test
    @DisplayName("Reached Exit")
    public void testReachedExit() {
        List<Integer> exitPosition = Arrays.asList(2, 4);
        Maze maze = new Maze("./examples/straight.maz.txt"); // Initialize maze here

        boolean result = maze.reachedExit(exitPosition.get(0), exitPosition.get(1)); // Now inside a constructor

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Checking for Valid Move: Moving Condition on Direct Maze")
    public void testMovingCondition() {
        Maze maze = new Maze("./examples/straight.maz.txt"); // Initialize maze here
        Explorer explorer = new Explorer(maze, new CommandHistory());
        List<Integer> movePosition = Arrays.asList(2, 0);
        boolean result = explorer.canMoveTo(movePosition.get(0), movePosition.get(1));

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Performing for Valid Move: Moving on Direct Maze")
    public void testMoveAction() {
        Position oldPos = new Position(0, 1, Direction.RIGHT);

        Maze maze = new Maze("./examples/straight.maz.txt"); // Initialize maze here
        Explorer explorer = new Explorer(maze, new CommandHistory());

        explorer.move(2, 1, Direction.LEFT);
        Position newPos = explorer.copyPosition();

        assertEquals(oldPos.getRow(), newPos.getRow());
        assertEquals(oldPos.getCol(), newPos.getCol());
        assertEquals(oldPos.getHeading(), newPos.getHeading());
    }

    @Test
    @DisplayName("Checking for Valid Move: Next Column")
    public void testGetNextCol() {
        Integer currentColumn = 0;
        Direction direction = Direction.RIGHT;
        Integer nextColumn = direction.getNextCol(currentColumn);

        assertEquals(1, nextColumn);
    }

    @Test
    @DisplayName("Checking for Valid Move: Next Row")
    public void testGetNextRow() {
        Integer currentRow = 0;
        Direction direction = Direction.DOWN;
        Integer nextRow = direction.getNextRow(currentRow);

        assertEquals(1, nextRow);
    }

    @Test
    @DisplayName("Direction: Turn Left")
    public void testDirectionLeft() {
        Direction direction = Direction.UP;
        assertEquals(Direction.LEFT, direction.turnLeft());
    }

    @Test
    @DisplayName("Direction: Turn Right")
    public void testDirectionRight() {
        Direction direction = Direction.UP;
        assertEquals(Direction.RIGHT, direction.turnRight());
    }

}