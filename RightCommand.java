package za.co.wethinkcode.examples.server;

public class RightCommand extends Command {

    public RightCommand() {
        super("right");
    }

    @Override
    public boolean execute(Robots target) {
        if (target.getCurrentDirection() == Direction.NORTH) {
            target.setCurrentDirection(Direction.WEST);

        } else if (target.getCurrentDirection() == Direction.WEST) {
            target.setCurrentDirection(Direction.SOUTH);

        } else if (target.getCurrentDirection() == Direction.SOUTH) {
            target.setCurrentDirection(Direction.EAST);

        } else
            target.setCurrentDirection(Direction.NORTH);

        target.setStatus("Turned right.");
        return true;
    }
}
