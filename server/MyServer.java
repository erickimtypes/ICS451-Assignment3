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
 * FILE        : MyServer.java
 * 
 * DESCRIPTION : This file contains the server-side code for homework 3.
 *
 */

import java.io.*;
import java.net.*;

public class MyServer {

    /**
     * The main method to start the server.
     *
     * @param args Command line arguments. Expects the port number for which the server will be on.
     */
    public static void main(String[] args) {
        // Check if the user provided the correct number of arguments
        if (args.length != 1) {
            System.out.println("Usage: java MyServer <port>");
            return;
        }
        
        // Parse the port number from the command line arguments
        int port = Integer.parseInt(args[0]);
        File fileToSend = new File("server_file.txt");

        // Try to create a server socket and listen for incoming connections
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for connections on port " + port + "...");

            // Accept incoming connections and send the file
            while (true) { 
                try (Socket clientSocket = serverSocket.accept();
                     FileInputStream fileInput = new FileInputStream(fileToSend);
                     OutputStream outputStream = clientSocket.getOutputStream()) {

                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    // Buffer to hold file data
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    // Read file data and send it to the client
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
