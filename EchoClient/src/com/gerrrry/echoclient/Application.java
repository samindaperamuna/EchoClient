package com.gerrrry.echoclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.gerrrry.echoclient.util.PropertyManager;

public class Application {

    private static PropertyManager propertyManager;

    public static void main(String[] args) {
	String host = null;
	int port = 0;
	loadProperties();

	// Temporary initialise block.
	if (args.length != 2) {
	    host = "37.59.51.221";
	    port = 5107;
	} else {
	    host = args[0];
	    port = Integer.parseInt(args[1]);
	}

	System.out.println("Trying to connect to remote host : " + host + " " + port);

	try (Socket socket = new Socket(host, port)) {
	    try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
		    System.out.println(in.readLine());
		}
	    }
	} catch (UnknownHostException e) {
	    System.err.println("Cannot connect to the remote host : " + host);
	} catch (ConnectException e) {
	    System.err.println("Connection refused on the remote port.");
	} catch (IOException e) {
	    System.err.println("Cannot read from the remote port : " + port);
	}

	saveProperties();
    }

    /**
     * Read the properties into the memory.
     */
    private static void loadProperties() {
	try {
	    propertyManager = PropertyManager.getInstance();
	} catch (IOException e) {
	    System.err.println("Cannot load the property file.");
	}
    }

    /**
     * Save the properties in memory to the disk.
     */
    private static void saveProperties() {
	try {
	    propertyManager.storeProperties();
	} catch (IOException e) {
	    System.err.println("Cannot store the property file.");
	}
    }
}
