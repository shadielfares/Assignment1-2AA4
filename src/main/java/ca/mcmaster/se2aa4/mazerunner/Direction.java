package ca.mcmaster.se2aa4.mazerunner;

import java.util.EnumMap;
import java.util.Map;

public enum Direction {
    UP {
        @Override
        public int getNextRow(int currentRow) {
            return currentRow - 1;
        }

        @Override
        public int getNextCol(int currentCol) {
            return currentCol;
        }
    },
    DOWN {
        @Override
        public int getNextRow(int currentRow) {
            return currentRow + 1;
        }

        @Override
        public int getNextCol(int currentCol) {
            return currentCol;
        }
    },
    LEFT {
        @Override
        public int getNextRow(int currentRow) {
            return currentRow;
        }

        @Override
        public int getNextCol(int currentCol) {
            return currentCol - 1;
        }
    },
    RIGHT {
        @Override
        public int getNextRow(int currentRow) {
            return currentRow;
        }

        @Override
        public int getNextCol(int currentCol) {
            return currentCol + 1;
        }
    };

    // Static mappings for turnRight and turnLeft operations
    private static final Map<Direction, Direction> rightMap = new EnumMap<>(Direction.class);
    private static final Map<Direction, Direction> leftMap = new EnumMap<>(Direction.class);

    // Static initialization block
    static {
        // Fill the rightMap
        rightMap.put(UP, RIGHT);
        rightMap.put(RIGHT, DOWN);
        rightMap.put(DOWN, LEFT);
        rightMap.put(LEFT, UP);

        // Fill the leftMap
        leftMap.put(UP, LEFT);
        leftMap.put(LEFT, DOWN);
        leftMap.put(DOWN, RIGHT);
        leftMap.put(RIGHT, UP);
    }

    /**
     * Get the direction to the right of this one.
     * 
     * @return The direction to the right.
     */
    public Direction turnRight() {
        return rightMap.get(this); // Lookup for the right turn
    }

    /**
     * Get the direction to the left of this one.
     * 
     * @return The direction to the left.
     */
    public Direction turnLeft() {
        return leftMap.get(this); // Lookup for the left turn
    }

    public abstract int getNextRow(int currentRow);

    public abstract int getNextCol(int currentCol);
}