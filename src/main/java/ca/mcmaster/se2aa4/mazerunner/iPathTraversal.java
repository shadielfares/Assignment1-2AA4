package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public interface iPathTraversal {
    ArrayList<Integer> findEntry();
    ArrayList<Integer> findExit();
    boolean reachedExit(int currentRow, int currentCol);
    ArrayList<Integer> move(int i, int j);
    boolean canMoveTo(int i, int j);
}
