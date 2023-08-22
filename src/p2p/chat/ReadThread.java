package p2p.chat;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReadThread extends Thread
{
    private int port;

    public ReadThread(int port){
        this.port = port;
    }
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            //System.out.println("Listening on port " + port);
            Socket socket = serverSocket.accept();

            //System.out.println("Connection has been established");

            String text = "";

            DataInputStream input;
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            while(!text.equals("exit")) {
                try {
                    text = input.readUTF();
                    System.out.println(text);
                } catch (IOException io) {
                    System.out.println("An error has occurred while receiving a message");
                }
            }

            socket.close();
            input.close();

        } catch (IOException ex) {
            System.out.println("An error occurred while instantiating the listening server");
        }
    }
}
