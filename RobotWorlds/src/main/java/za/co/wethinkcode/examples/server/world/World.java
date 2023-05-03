package za.co.wethinkcode.examples.server.world;

public class World implements IWorld {
    private int size;
    public World(){
        this.size = 100;
    }
    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void updateDirection(boolean turnRight) {
        // TODO Auto-generated method stub

    }
    /*@Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        return null;
    }*/
    @Override
    public Direction getCurrentDirection() {
        // TODO Auto-generated method stub
        return null;
    }
    /*@Override
    public boolean isNewPositionAllowed(Position position) {
        // TODO Auto-generated method stub
        return false;
    }*/
    @Override
    public boolean isAtEdge() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void reset() {
        // TODO Auto-generated method stub

    }
    @Override
    public void showObstacles() {
        // TODO Auto-generated method stub

    }

}
