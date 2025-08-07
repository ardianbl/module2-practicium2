package core;

import Exceptions.CommandException;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * The Receiver class contains some business logic. Almost any object may act as a receiver.
 * Most commands only handle the details of how a request is passed to the receiver, while the
 * receiver itself does the actual work.
 * <p>
 * Include a storeToFile() method in your class that handles the data store.
 */
public class Receiver {
    /**
     * An ArrayList of employee details
     */
    private ArrayList<String> employeeList = new ArrayList<>();
    /**
     * Variable for storing the state of dataStore.txt
     */
    private boolean isExist = false;
    /**
     * File path constant
     */
    private static final Path FILE_PATH = Paths.get("./src/dataStore.txt");

    /**
     * Receiver constructor <p>
     * Read from the {@code dataStore.txt} and store the elements into employeeList (if any)
     */
    public Receiver() {
        if (Files.exists(FILE_PATH)) {
            try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    employeeList.add(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Getter method to obtain the employeeList
     * @return ArrayList<> employeeList
     */
    public ArrayList<String> getEmployeeList() {
        return employeeList;
    }

    /**
     * Getter method to obtain the employee detail
     * @param index employee index from employeeList
     * @return String
     */
    public String getEmployeeDetails(int index) {
        return employeeList.get(index);
    }

    /**
     * Getter method to obtain the number of recorded employees
     * @return int
     */
    public int getEmployeeCount() {
        return employeeList.size();
    }

    /**
     * Method for adding an employee record into the employeeList
     * @param first_name String
     * @param last_name String
     * @param emailAddress String
     */
    public void addEntry(String first_name, String last_name, String emailAddress) {
        String currentEntry = String.format("%s %s %s", first_name, last_name, emailAddress);
        employeeList.add(currentEntry);
    }

    /**
     * Method for updating an employee details in the employeeList
     * @param index int
     * @param first_name String
     * @param last_name String (optional)
     * @param emailAddress String (optional)
     */
    public void updateEntry(int index, String first_name, String last_name, String emailAddress) {
        String updatedValue = String.format("%s %s %s", first_name, last_name, emailAddress);
        employeeList.set(index, updatedValue);
    }

    /**
     * Method for deleting an employee record from the employeeList
     * @param index int
     */
    public void deleteEntry(int index) {
        employeeList.remove(index);
    }

    /**
     * Method for printing the list of employees from the employeeList
     */
    public void list() {
        if (employeeList.isEmpty()) {
            throw new CommandException("(List) There are no employees in the datastore");
        }

        int number = 1;
        System.out.println("List");
        for (String s : employeeList) {
            System.out.printf("%02d. %s\n", number, s);
            number++;
        }
    }

    /**
     * Method for adding an employee record into the employeeList
     * in a specific index (for Undo operation)
     * @param index int
     * @param currentEntry String
     */
    public void setEntry(int index, String currentEntry) {
        employeeList.add(index, currentEntry);
    }

    /**
     * Method for saving the employee record from {employeeList}
     * into dataStore.txt file
     */
    public void storeToFile() {
        try {
            if(!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }
            Files.write(FILE_PATH, employeeList, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
