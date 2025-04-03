package ca.mcmaster.se2aa4.mazerunnertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.MazeArray;
import ca.mcmaster.se2aa4.mazerunner.MazeReader;
import ca.mcmaster.se2aa4.mazerunner.PathSequence;
import ca.mcmaster.se2aa4.mazerunner.PathTraversal;

/*
TestSuite is a group of bundled tests.
The bundled tests should address specific tests.

Currently, we have two groups:

PathVerifierTests
PathSequenceTests
 */
public class PathTraversalTest {

    private PathTraversal traversal = new PathTraversal();
    private PathSequence pathSequence = new PathSequence();
    private String sequence;


    @Test
    @DisplayName("Path Traversal: Factorized Sequence Test on Direct Maze")
    public void testFactorizedSequenceDirectMaze() {
        try {
            new MazeReader("./examples/direct.maz.txt");
        } catch (Exception e) {
            fail("MazeReader failed to find the file" + e.getMessage());
        }
        PathTraversal factorizedTraversal = new PathTraversal();

        factorizedTraversal.pathTraversal(false); // Now inside a constructor
        sequence = pathSequence.convertToFactorize();

        assertNotNull(sequence, "Sequence should not be null.");
        assertEquals("F R 2F L 3F R F L F R F L 2F", sequence);
    }

    @Test
    @DisplayName("Find Entrance")
    public void testFindEntrance() {
        List<Integer> entry = traversal.findEntry();

        assertNotNull(entry, "List should not be null.");
        assertEquals(Arrays.asList(1), entry);
    }

    @Test
    @DisplayName("Find Exit")
    public void testFindExit() {
        List<Integer> entry = traversal.findExit();

        assertNotNull(entry, "List should not be null.");
        assertEquals(Arrays.asList(5,7), entry);
    }

    @Test
    @DisplayName("Reached Exit")
    public void testReachedExit() {
        List<Integer> exitPosition = Arrays.asList(5,7);
        boolean result = traversal.reachedExit(exitPosition.get(0), exitPosition.get(1)); // Now inside a constructor
        
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Checking for Valid Move: Moving Condition on Direct Maze")
    public void testMovingCondition() {
        try {
            new MazeReader("./examples/direct.maz.txt");
        } catch (Exception e) {
            fail("MazeReader failed to find the file" + e.getMessage());
        }

        List<Integer> movePosition = Arrays.asList(1,1);
        boolean result = traversal.canMoveTo(movePosition.get(0), movePosition.get(1));

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Performing for Valid Move: Moving on Direct Maze")
    public void testMoveAction() {
        ArrayList<Object> mockMoveResult = new ArrayList<Object>();
        Integer currentRow = 1;
        Integer currentCol = 0;
        Direction direction = Direction.DOWN;
        mockMoveResult.add(currentRow);
        mockMoveResult.add(currentCol);
        mockMoveResult.add(direction);

        ArrayList<Object> moveResult = traversal.move(0, 0, direction);

        assertEquals(mockMoveResult, moveResult);
    }

    @Test
    @DisplayName("Checking for Valid Move: Next Column")
    public void testGetNextCol() {
        Integer currentColumn = 0;
        Direction direction = Direction.RIGHT;
        Integer nextColumn = traversal.getNextCol(currentColumn, direction);

        assertEquals(1, nextColumn);
    }

    @Test
    @DisplayName("Checking for Valid Move: Next Row")
    public void testGetNextRow() {
        Integer currentRow = 0;
        Direction direction = Direction.DOWN;
        Integer nextRow = traversal.getNextRow(currentRow, direction);

        assertEquals(1, nextRow);
    }

    @Test
    @DisplayName("Direction: Turn Left")
    public void testDirectionLeft(){
        Direction direction = Direction.UP;
        assertEquals(Direction.LEFT, direction.turnLeft());
    }

    @Test
    @DisplayName("Direction: Turn Right")
    public void testDirectionRight(){
        Direction direction = Direction.UP;
        assertEquals(Direction.RIGHT, direction.turnRight());
    }

}