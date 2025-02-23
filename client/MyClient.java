/**
 * NAME        : Eric Kim
 *  
 * HOMEWORK    : 3
 * 
 * CLASS       : ICS 451
 * 
 * INSTRUCTOR  : Ravi Narayan
 * 
 * DATE        : 02/22/2025
 * 
 * FILE        : MyClient.java
 * 
 * DESCRIPTION : This file contains the client-side code for homework 3.
 *
 */
import java.io.*;
import java.net.*;

    /**
     * The main method to start the client.
     *
     * @param args Command line arguments. Expects the port number of the server.
     */
public class MyClient {
    public static void main(String[] args) {
        // Check if the user provided the correct number of arguments
        if (args.length != 1) {
            System.out.println("Usage: java MyClient <port>");
            return;
        }

        // Parse the port number from the command line arguments
        int port = Integer.parseInt(args[0]);
        File receivedFile = new File("client_file.txt");

        // Try to connect to the server and receive the file
        try (Socket socket = new Socket("localhost", port);
             InputStream inputStream = socket.getInputStream();
             FileOutputStream fileOutput = new FileOutputStream(receivedFile)) {

            System.out.println("Connected to server. Receiving file...");

            // Buffer to hold file data
            byte[] buffer = new byte[4096];
            int bytesRead;
            // Read file data and save it to the file
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutput.write(buffer, 0, bytesRead);
            }

            System.out.println("File received successfully and saved as 'client_file.txt'.");
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
