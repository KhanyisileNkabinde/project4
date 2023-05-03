package za.co.wethinkcode.examples.server.robot;

public class Obstacles extends WorldObject{
    private String shape;
    private int posX;
    private int posY;

    public Obstacles(String shape, int posX, int posY) {
        this.shape = shape;
        this.posX = posX;
        this.posY = posY;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public String toSymbol() {
        return "[#]";
    }

    @Override
    public String toString() {
        return "Obstacle{" +
                "shape='" + shape + '\'' +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}

