package com.gerrrry.echoclient;

import java.io.IOException;

import com.gerrrry.echoclient.util.PropertyManager;

public class Application {

    private static PropertyManager propertyManager;

    public static void main(String[] args) {
	loadProperties();

	// TODO: Logic

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
