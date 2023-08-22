package p2p.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread{
    private String hostname;
    private int port;

    public WriteThread(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }
    public void run() {
        try {
            Socket socket = new Socket(hostname, port);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            String input = "";

            while(!input.equals("exit")) {
                input = scanner.nextLine();
                output.writeUTF(input);
            }

        } catch (IOException io) {
            System.out.println("An error has occurred " + io.getMessage());
        }
    }


}
