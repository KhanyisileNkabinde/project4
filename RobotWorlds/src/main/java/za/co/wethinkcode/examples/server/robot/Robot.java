package za.co.wethinkcode.examples.server.robot;


public class Robot extends WorldObject {
    private String name;
    private String make;
    private int posX;
    private int posY;
    private int shield;
    private int shots;


    private Direction orientation;

    public Robot(String name, String make, int posX, int posY) {
        this.name = name;
        this.make = make;
        this.orientation = Direction.EAST;
        this.shield = 5;
        this.posX = posX;
        this.posY = posY;
        this.shots = 3;
    }

    public void move(String moveCommand) {
        if (moveCommand.equalsIgnoreCase("forward")) {
            switch (orientation.name().toLowerCase()) {
                case "south":
                    posX = posX+ 1;
                    break;
                case "north":
                    posX = posX - 1;
                    break;
                case "west":
                    posY = posY+ 1;
                    break;
                case "east":
                    posY = posY - 1;
                    break;
                default: throw new IllegalStateException("Unexpected value: " + orientation.name().toLowerCase());
            }
        } else if (moveCommand.equalsIgnoreCase("back")) {
            switch (orientation.name().toLowerCase()) {
                case "south": posX = posX - 1;
                case "north": posX = posX + 1;
                case "west": posY= posY - 1;
                case "east": posY = posY+ 1;
            }
        }
    }

    public void turnLeft() {
        if (orientation.equals(Direction.EAST)) {
            orientation = Direction.NORTH;
        } else if (orientation.equals(Direction.WEST)) {
            orientation = Direction.SOUTH;
        } else if (orientation.equals(Direction.NORTH)) {
            orientation = Direction.WEST;
        } else if (orientation.equals(Direction.SOUTH)) {
            orientation = Direction.EAST;
        }
    }

    public void turnRight() {
        if (orientation.equals(Direction.EAST)) {
            orientation = Direction.SOUTH;
        } else if (orientation.equals(Direction.WEST)) {
            orientation = Direction.NORTH;
        } else if (orientation.equals(Direction.NORTH)) {
            orientation = Direction.EAST;
        } else if (orientation.equals(Direction.SOUTH)) {
            orientation = Direction.WEST;
        }
    }

    public void repair() {
        shield = 5;
    }

    public void reload() throws InterruptedException {
        Thread.sleep(5000);
        shots = 3;
    }

    public void fire() {
        shots--;
    }

    public Object look(Object world) {
        return world;
    }

    public Robot state() {
        return this;
    }

    public String toSymbol() {
        return "[R]";
    }

    public String getName() {
        return name;
    }

    public String getMake() {
        return make;
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

    public int getShield() {
        return shield;
    }

    public int getShots() {
        return shots;
    }


    public Direction getOrientation() {
        return orientation;
    }

    public Robot clone() throws CloneNotSupportedException {
        Robot clone = (Robot) super.clone();
        return clone;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "name='" + name + '\'' +
                ", make='" + make + '\'' +
                ", posX=" + posX +
                ", posY=" + posY +
                ", shield=" + shield +
                ", shots=" + shots +
                ", orientation=" + orientation +
                '}';
    }
}
