package p2p.chat;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A thread that handles messages incoming at the listening port.
 */
public class ReadThread extends Thread {
    private final int port;

    public ReadThread(int port){ this.port = port; }
	
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(port)) {

            Socket socket = serverSocket.accept();

            String remoteIP = socket.getInetAddress().getHostAddress();
            int remotePort = socket.getPort();

            String text = "";

            DataInputStream input;
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            while(!text.equals("exit")) {
                try {
                    text = "[" + remoteIP + ":"+ remotePort + "]: " + input.readUTF();
                    System.out.println(text);
                } catch (IOException io) {
                    System.out.println("An error has occurred while receiving a message");
                    break;
                }
            }

            input.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println("An error occurred while instantiating the listening server");
        }
    }
}
