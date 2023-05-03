package za.co.wethinkcode.examples.client;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class RobotClient {
    public static void main(String[] args) {
        try {
            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5057
            Socket s = new Socket(ip, 5057);
            JSONObject obj = new JSONObject();

            System.out.println("Please input the robot name :");
            obj.put("name", scn.nextLine());
            String make = "";

            while (true) {
                System.out.println("Please input the robot make : \n1. Sniper \n2. Regular");
                int val = Integer.parseInt(scn.nextLine());
                if (val == 1) {
                    make = "Sniper";
                    break;
                } else if (val == 2) {
                    make = "Regular";
                    break;
                }
                System.out.println("Option"+val+" not available..!");

            }
            obj.put("make", make);
            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true) {
                System.out.println(dis.readUTF());
                String command = scn.nextLine();
                obj.put("command", command);
                dos.writeUTF(obj.toString());

                // If client sends exit,close this connection
                // and then break from the while loop
                if (command.equals("Exit")) {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                String received = dis.readUTF();
                System.out.println(received);
            }

            // closing resources
            scn.close();
            dis.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}