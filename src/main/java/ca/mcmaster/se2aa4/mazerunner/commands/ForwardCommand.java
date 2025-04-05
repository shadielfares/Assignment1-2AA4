package ca.mcmaster.se2aa4.mazerunner.commands;

import ca.mcmaster.se2aa4.mazerunner.Command;
import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Explorer;
import ca.mcmaster.se2aa4.mazerunner.Position;

public class ForwardCommand extends Command {
    public ForwardCommand(Explorer explorer) {
        super(explorer);
    }

    @Override
    public Character getInstructionID() {
        return 'F';
    }

    @Override
    public void execute() {
        Position pos = explorer.copyPosition();
        Direction currentDirection = pos.getHeading();
        int forwardCol = currentDirection.getNextCol(pos.getCol());
        int forwardRow = currentDirection.getNextRow(pos.getRow());

        explorer.setPosition(forwardRow, forwardCol, currentDirection);
    }
}