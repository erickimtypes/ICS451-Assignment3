import java.io.*;
import java.net.*;

public class MyClient {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MyClient <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        File receivedFile = new File("client_file.txt");

        try (Socket socket = new Socket("localhost", port);
             InputStream inputStream = socket.getInputStream();
             FileOutputStream fileOutput = new FileOutputStream(receivedFile)) {

            System.out.println("Connected to server. Receiving file...");

            
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutput.write(buffer, 0, bytesRead);
            }

            System.out.println("File received successfully and saved as 'client_file.txt'.");
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
