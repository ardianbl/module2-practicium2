package core;

import util.FileHandler;

import java.util.*;

/**
 * The Receiver class contains some business logic. Almost any object may act as a receiver.
 * Most commands only handle the details of how a request is passed to the receiver, while the
 * receiver itself does the actual work.
 */

public class Receiver {
    private static ArrayList<String> employeeList =  new ArrayList<>();

    public Receiver() {
        employeeList = FileHandler.populateList(employeeList);
    }

    public int getEmployeeCount() {
        return employeeList.size();
    }

    public static void add(String firstName, String lastName, String email) {
        employeeList.add(firstName + " " + lastName + " " + email);
    }

    public void update(int index, String firstName, String lastName, String email) {
        employeeList.set(index, firstName + " " + lastName + " " + email);
    }

    public static void delete(int index) {
        employeeList.remove(index);
    }

    public static void list() {
        int number = 1;
        for (String e : employeeList) {
            System.out.printf("%02d. %s\n", number , e);
            number++;
        }
    }
}
