package za.co.wethinkcode.examples.server;


import za.co.wethinkcode.examples.server.robot.Robot;
import za.co.wethinkcode.examples.server.robot.RobotWorld;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Properties;

public class RobotServer {

    public static void main(String[] args) throws IOException {
        // server is listening on port 5057
        ServerSocket ss = new ServerSocket(5057);

        // reading from a config.properties file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("/home/nmondlan/student_work_java/RobotProject/src/config.properties");
        prop.load(fis);

        //creating grid world
        RobotWorld world = new RobotWorld(prop);

        //accepts server side commands
        Runnable task2 = () -> {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {
                    String command = br.readLine();
                    if (command.equalsIgnoreCase("quit")) {
                        System.out.println("quit");
                        System.exit(0);
                    } else if (command.equalsIgnoreCase("robots")) {
                        List<Robot> robots = world.getRobots();
                        int counter = 1;
                        for (Robot robot : robots) {

                            System.out.println("=============|Robot : "+counter+"|================");
                            System.out.println("name : " + robot.getName());
                            System.out.println("make : " + robot.getMake());
                            System.out.println("Position : (" + robot.getPosX() + "," + robot.getPosY() + ")");
                            System.out.println("shield : " + robot.getShield());
                            System.out.println("shots : " + robot.getShots());
                            System.out.println("orientation : " + robot.getOrientation());
                            counter++;
                        }
                    } else if (command.equalsIgnoreCase("dump")) {
                        System.out.println(world);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread th1 = new Thread(task2, "The Command Thread");
        th1.start();

        //accept client connections
        while (true) {
            Socket s = null;
            try {
                // socket object to receive incoming client requests
                s = ss.accept();
                //System.out.println("A new client is connected : " + s);
                //System.out.println("Assigning new thread for this client");
                // create a new thread object
                ServerClientThread serverClientThread = new ServerClientThread(s, world);
                // Invoking the start() method
                serverClientThread.start();
            } catch (Exception e) {
                assert s != null;
                s.close();
                e.printStackTrace();
            }
        }
    }
}