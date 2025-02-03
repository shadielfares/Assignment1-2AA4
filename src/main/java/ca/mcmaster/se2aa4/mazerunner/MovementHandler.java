package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

interface MovementHandler {
    boolean canMove(int currentRow, int currentCol, PathTraversal pathTraversal);
    ArrayList<Integer> move(int currentRow, int currentCol, PathTraversal pathTraversal);
}