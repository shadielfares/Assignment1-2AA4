package ca.mcmaster.se2aa4.mazerunner;

public abstract class Command {
    protected Explorer explorer;
    protected Position position;
    protected Character instructionID;

    abstract public void execute();

    abstract public Character getInstructionID();

    public Command(Explorer explorer) {
        this.explorer = explorer;
    }
}