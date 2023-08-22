package p2p.chat;

import java.net.ServerSocket;
import java.util.Scanner;

public class Main {
    private static ServerSocket serverSocket;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please set the listening port number: ");
        int port = Integer.parseInt(scanner.nextLine());

        try {
            new ReadThread(port).start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.print("Please specify the host address: ");
        String hostname = scanner.nextLine();
        System.out.print("Please specify the remote port number: ");
        int remotePort = scanner.nextInt();

        try {
            new WriteThread(hostname, remotePort).start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
