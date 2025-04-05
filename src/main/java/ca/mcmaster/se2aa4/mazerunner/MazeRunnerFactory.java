package ca.mcmaster.se2aa4.mazerunner;

public class MazeRunnerFactory {

    public static Object createMazeRunner(String filepath, String sequence, CommandHistory history) {
        if (sequence != null) {
            return new PathChecker(sequence, new Maze(filepath), history);
        } else {
            return new RightHandAlgorithm(new Maze(filepath), history);

        }
    }
}