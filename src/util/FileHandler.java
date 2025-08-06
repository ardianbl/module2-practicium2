package util;

import core.Receiver;
import model.Employee;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * FileHandler class contains two methods: <br>
 * populateList - populate employee list from dataStore.txt file at start-up <br>
 * storeToFile - save the final employee list to dataStore.txt file
 */
public class FileHandler {
    /**
     * File name constant for reading/writing employee list, i.e. dataStore.txt
     */
    private static final String FILE_NAME = "dataStore.txt";
    /**
     * File path constant for instance methods
     */
    private static final Path FILE_PATH = Paths.get("src", FILE_NAME);

    public static ArrayList<Employee> populateList(ArrayList<Employee> employeeList) {
        try {
//            Path dir = FILE_PATH.getParent();
//            // check if the file directory exist
//            if (dir != null && !Files.exists(dir)) {
//                Files.createDirectories(dir);
//            }

            // check if dataStore.txt file exist and read its content
            if (!Files.exists(FILE_PATH)) {
                System.out.println("File does not exist. Creating a new file.");
                Files.createFile(FILE_PATH);
            }
            else {
                List<String> lines = Files.readAllLines(FILE_PATH);
                if (!lines.isEmpty()) {
                    employeeList.clear();
                    for (String line : lines) {
                        String[] split = line.split(" ");
                        String firstName = split[0];
                        String lastName = split[1];
                        String email = split[2];
                        employeeList.add(new Employee(firstName, lastName, email));
                    }
                    System.out.println("Successfully read from file. Employee list is updated.");
                } else {
                    System.out.println("File is empty. Employee list is not updated.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        return employeeList;
    }
}
