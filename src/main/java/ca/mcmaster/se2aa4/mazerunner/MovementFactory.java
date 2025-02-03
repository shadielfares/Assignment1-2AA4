package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.Map;

class MovementFactory {
    private final Map<Character, MovementHandler> movements;

    public MovementFactory() {
        movements = new HashMap<>();
        movements.put('R', new MoveRightHandler());
        movements.put('D', new MoveDownHandler());
        movements.put('L', new MoveLeftHandler());
        movements.put('U', new MoveUpHandler());
    }

    public MovementHandler getHandler(Character direction) {
        MovementHandler handler = movements.get(direction);
        if (handler == null) {
            throw new IllegalArgumentException("Invalid move direction: " + direction);
        }
        return handler;
    }
}