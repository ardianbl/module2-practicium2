package core;

import Exceptions.CommandException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static core.Validator.*;

/**
 * The Receiver class contains some business logic. Almost any object may act as a receiver.
 * Most commands only handle the details of how a request is passed to the receiver, while the
 * receiver itself does the actual work.
 * <p>
 * Include a storeToFile() method in your class that handles the data store.
 */
public class Receiver {
//Check if dataStore exist in the folder. Get data and put into an array.
// if add and dataStore did not exist create A NEW file, add data and save.
//Execute the command.

    private ArrayList<String> employeeList = new ArrayList<>();
    private boolean isExist = false;
    Path filepath = Paths.get("./src/dataStore.txt");

    public ArrayList<String> getEmployeeList() {
        return employeeList;
    }

    public String getEmployee (int index){

        try {
            return employeeList.get(index);
        }catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Entry does not exist");
        }
    }
    public int getEmployeeCount() {
        return employeeList.size();
    }

    public void setEmployeeList(ArrayList<String> employeeList) {
        this.employeeList = employeeList;
    }

    //Passed the data in the file into the array called data for the command to use.
    public Receiver() {
        isExist = Files.exists(filepath);

        if (isExist) {
            try (BufferedReader reader = Files.newBufferedReader(filepath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    employeeList.add(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void addEntry(String first_name, String last_name, String emailAddress) {
        String currentEntry = String.format("%s %s %s", first_name, last_name, emailAddress);
        employeeList.add(currentEntry);
    }

    public void updateEntry(int index, String first_name, String last_name, String emailAddress) {
        try {
            String currentDataLine = employeeList.get(index);
            String[] splitCurrent = currentDataLine.split(" ");

            if (!first_name.equals("-")) splitCurrent[0] = first_name;
            if (!last_name.equals("-")) splitCurrent[1] = last_name;
            if (!emailAddress.equals("-")) splitCurrent[2] = emailAddress;

            String updatedValue = String.format("%s %s %s", splitCurrent[0], splitCurrent[1], splitCurrent[2]);

            employeeList.set(index, updatedValue);

        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Invalid index number, entry does not exist.");
        }

    }
    public void list() {
        int number = 1;
         System.out.println("List");
        for (String s : employeeList) {
            System.out.printf("%02d. %s\n", number, s);
            number++;
        }
    }


    public void delete(int index ){
        try {
            employeeList.remove(index); //Can just directly, it will just throw an error if it does not exist.
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Invalid index number, entry does not exist.");
        }
    }

    public void setEntry(int index, String currentEntry) {
        employeeList.add(index, currentEntry);
    }

     public void storeToFile() {
        try {
            Files.write(filepath, employeeList, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


//


}
