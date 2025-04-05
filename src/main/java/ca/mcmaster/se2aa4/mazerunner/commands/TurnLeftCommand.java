package ca.mcmaster.se2aa4.mazerunner.commands;

import ca.mcmaster.se2aa4.mazerunner.Command;
import ca.mcmaster.se2aa4.mazerunner.Explorer;
import ca.mcmaster.se2aa4.mazerunner.Position;
import ca.mcmaster.se2aa4.mazerunner.Direction;

public class TurnLeftCommand extends Command {

    public TurnLeftCommand(Explorer explorer) {
        super(explorer);
    }

    @Override
    public Character getInstructionID() {
        return 'L';
    }

    @Override
    public void execute() {
        Position pos = explorer.copyPosition();
        Direction leftDirection = pos.getHeading().turnLeft();

        explorer.setPosition(pos.getRow(), pos.getCol(), leftDirection);
    }
}