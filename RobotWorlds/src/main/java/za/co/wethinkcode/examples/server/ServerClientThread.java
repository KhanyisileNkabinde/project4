package za.co.wethinkcode.examples.server;

import org.json.JSONObject;
import za.co.wethinkcode.examples.server.robot.Robot;
import za.co.wethinkcode.examples.server.robot.RobotWorld;


import java.io.*;
import java.net.*;


public class ServerClientThread extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    RobotWorld world;

    // Constructor
    public ServerClientThread(Socket s, RobotWorld world) throws IOException {
        this.world = world;
        this.s = s;
        this.dis = new DataInputStream(s.getInputStream());
        this.dos = new DataOutputStream(s.getOutputStream());
    }

    @Override
    public void run() {
        String jsonReceive;

        while (true) {
            try {
                // Ask user what he wants
                dos.writeUTF("Select a command by numbers :\n" + "0. Exit\n" +
                        "1. launch\n" +
                        "2. Look\n" +
                        "3. forward\n" +
                        "4. back\n" +
                        "5. turn left\n" +
                        "6. turn right\n" +
                        "7. repair\n" +
                        "8. reload\n" +
                        "9. fire\n" +
                        "10. state\n");
                // receive the answer from client
                jsonReceive = dis.readUTF();
                String command;
                System.out.println("received : " + jsonReceive);
                JSONObject json = new JSONObject(jsonReceive);

                String name = json.get("name").toString();
                String make = json.get("make").toString();
                String received = json.get("command").toString();

                if (received.equalsIgnoreCase("0")) {
                    break;
                } else if (received.equalsIgnoreCase("1")) {
                    command = "launch";
                    Robot robot = new Robot(name,make,world.getLandscape().length/2,world.getLandscape()[0].length/2);
                    world.addRobot(robot);
                    System.out.println("received : " + command);
                    dos.writeUTF(command);
                } else if (received.equalsIgnoreCase("2")) {
                    command = "Look";
                    System.out.println("received : " + command);
                    Robot look = world.getRobots().stream()
                            .filter(robot -> name.equals(robot.getName()))
                            .findAny()
                            .orElse(null);
                    System.out.println(look);
                    dos.writeUTF("received : " + command);
                } else if (received.equalsIgnoreCase("3")) {
                    command = "forward";
                    System.out.println("received : " + command);
                    Robot forward = world.getRobots().stream()
                            .filter(robot -> name.equalsIgnoreCase(robot.getName()))
                            .findAny()
                            .orElse(null);
                    System.out.println("before command + "+forward);
                    assert forward != null;
                    Robot oldCopy = forward.clone();
                    forward.move(command);
                    world.updateRobotInWorld(oldCopy,forward);
                    System.out.println("before command + "+forward);
                    dos.writeUTF("received : " + command);
                } else if (received.equalsIgnoreCase("4")) {
                    command = "back";
                    System.out.println("received : " + command);
                    Robot back = world.getRobots().stream()
                            .filter(robot -> name.equals(robot.getName()))
                            .findAny()
                            .orElse(null);
                    System.out.println("before command + "+back);
                    assert back != null;
                    Robot oldCopy = back.clone();
                    back.move(command);
                    world.updateRobotInWorld(oldCopy,back);
                    System.out.println("before command + "+back);
                    dos.writeUTF("received : " + command);
                } else if (received.equalsIgnoreCase("5")) {
                    command = "turn left";
                    System.out.println("received : " + command);
                    Robot turnLeft = world.getRobots().stream()
                            .filter(robot -> name.equals(robot.getName()))
                            .findAny()
                            .orElse(null);
                    System.out.println("before command + "+turnLeft);
                    assert turnLeft != null;
                    turnLeft.turnLeft();
                    System.out.println("before command + "+turnLeft);
                    dos.writeUTF("received : " + command);
                } else if (received.equalsIgnoreCase("6")) {
                    command = "turn right";
                    System.out.println("received : " + command);
                    Robot turnRight = world.getRobots().stream()
                            .filter(robot -> name.equals(robot.getName()))
                            .findAny()
                            .orElse(null);
                    System.out.println("before command + "+turnRight);
                    assert turnRight != null;
                    turnRight.turnRight();
                    System.out.println("before command + "+turnRight);
                    dos.writeUTF("received : " + command);
                }else if (received.equalsIgnoreCase("7")) {
                    command = "repair";
                    System.out.println("received : " + command);
                    Robot repair = world.getRobots().stream()
                            .filter(robot -> name.equals(robot.getName()))
                            .findAny()
                            .orElse(null);
                    System.out.println("before command + "+repair);
                    assert repair != null;
                    repair.repair();
                    System.out.println("before command + "+repair);
                    dos.writeUTF("received : " + command);
                } else if (received.equalsIgnoreCase("8")) {
                    command = "repair";
                    System.out.println("received : " + command);
                    Robot reload = world.getRobots().stream()
                            .filter(robot -> name.equals(robot.getName()))
                            .findAny()
                            .orElse(null);
                    System.out.println("before command + "+reload);
                    assert reload != null;
                    reload.reload();
                    System.out.println("before command + "+reload);
                    json.getJSONArray(reload.toString());
                    dos.writeUTF("received : " + command);
                } else if (received.equalsIgnoreCase("9")) {
                    command = "fire";
                    System.out.println("received : " + command);
                    Robot fire = world.getRobots().stream()
                            .filter(robot -> name.equals(robot.getName()))
                            .findAny()
                            .orElse(null);
                    System.out.println("before command + "+fire);
                    assert fire != null;
                    fire.fire();
                    System.out.println("before command + "+fire);
                    dos.writeUTF("received : " + command);
                } else if (received.equalsIgnoreCase("10")) {
                    command = "state";
                    System.out.println("received : " + command);
                    Robot state = world.getRobots().stream()
                            .filter(robot -> name.equals(robot.getName()))
                            .findAny()
                            .orElse(null);
                    System.out.println("before command + "+state);
                    assert state != null;
                    state.state();
                    System.out.println("before command + "+state);
                    dos.writeUTF("received : " + command);
                }


            } catch (IOException | InterruptedException | CloneNotSupportedException e) {
                e.printStackTrace();

            }

        }
        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
