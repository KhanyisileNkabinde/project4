package za.co.wethinkcode.examples.server.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class RobotWorld {

    private final WorldObject[][] landscape;
    private final List<Robot> robots = new ArrayList<>();
    private final List<Obstacles> obstacles = new ArrayList<>();

    public RobotWorld(Properties prop) {
        int height = Integer.parseInt(prop.getProperty("height"));
        int width = Integer.parseInt(prop.getProperty("width"));
        this.landscape = new WorldObject[height][width];
        for (WorldObject[] row : landscape)
            Arrays.fill(row, new WorldObject());
        Obstacles obstacle = new Obstacles("square", Integer.parseInt(prop.getProperty("obst1x")), Integer.parseInt(prop.getProperty("obst1y")));
        Obstacles obstacle1 = new Obstacles("triangle", Integer.parseInt(prop.getProperty("obst2x")), Integer.parseInt(prop.getProperty("obst2y")));
        this.obstacles.add(obstacle);
        this.obstacles.add(obstacle1);
        this.landscape[obstacle.getPosX()][obstacle.getPosY()] = obstacle;
        this.landscape[obstacle1.getPosX()][obstacle1.getPosY()] = obstacle1;
        this.printWorld();
    }

    public void printWorld() {
        System.out.println("==================================================");
        for (WorldObject[] worldObjects : landscape) {
            for (WorldObject worldObject : worldObjects) System.out.print(worldObject.toSymbol() + " ");
            System.out.println();
        }
        System.out.println("==================================================");
    }

    public void addRobot(Robot robot) {
        while (true) {
            if (landscape[robot.getPosX()][robot.getPosY()].toSymbol().equalsIgnoreCase("[ ]")) {
                this.landscape[robot.getPosX()][robot.getPosY()] = robot;
                break;
            }
            robot.setPosY((int) (Math.random() * 4));
            robot.setPosX((int) (Math.random() * 4));
        }
        robots.add(robot);
        this.printWorld();
    }

    public String updateRobotInWorld(Robot oldRobot,Robot newRobot) {
        String results = "Robot state can't be changed due to constraints...!";
        //check if position is empty
        if (landscape[newRobot.getPosX()][newRobot.getPosY()].toSymbol().equalsIgnoreCase("[ ]")) {
            landscape[oldRobot.getPosX()][oldRobot.getPosY()] = new WorldObject();
            this.landscape[newRobot.getPosX()][newRobot.getPosY()] = newRobot;
            results ="Robot state successfully updated...!";
        }
        printWorld();
        return results;
    }

    public WorldObject[][] getLandscape() {
        return landscape;
    }

    public List<Robot> getRobots() {
        return robots;
    }

    @Override
    public String toString() {
        return "dump{" +
                "landscape=" + Arrays.toString(landscape) +
                ", robots=" + robots +
                ", obstacles=" + obstacles +
                '}';
    }
}

