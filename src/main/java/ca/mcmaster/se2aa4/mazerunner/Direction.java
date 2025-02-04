public enum Direction {
    UP, RIGHT, DOWN, LEFT;

    // Turn 90 degrees to the right
    public Direction turnRight() {
        return values()[(this.ordinal() + 1) % 4];
    }

    // Turn 90 degrees to the left
    public Direction turnLeft() {
        return values()[(this.ordinal() + 3) % 4];
    }
}