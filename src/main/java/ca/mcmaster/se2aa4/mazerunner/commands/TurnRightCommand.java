package ca.mcmaster.se2aa4.mazerunner.commands;

import ca.mcmaster.se2aa4.mazerunner.Command;
import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Explorer;
import ca.mcmaster.se2aa4.mazerunner.Position;

public class TurnRightCommand extends Command {

    public TurnRightCommand(Explorer explorer) {
        super(explorer);
    }

    @Override
    public Character getInstructionID() {
        return 'R';
    }

    @Override
    public void execute() {
        Position pos = explorer.copyPosition();
        Direction rightDirection = pos.getHeading().turnRight();
        int rightCol = rightDirection.getNextCol(pos.getCol());
        int rightRow = rightDirection.getNextRow(pos.getRow());

        explorer.setPosition(rightRow, rightCol, rightDirection);
    }
}