import java.io.*;
import java.net.*;

public class MyServer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MyServer <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        File fileToSend = new File("server_file.txt");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for connections on port " + port + "...");

            while (true) {  
                try (Socket clientSocket = serverSocket.accept();
                     FileInputStream fileInput = new FileInputStream(fileToSend);
                     OutputStream outputStream = clientSocket.getOutputStream()) {

                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileInput.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    System.out.println("File sent successfully. Closing connection.");
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }

                break; 
            }

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
