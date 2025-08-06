package core;

import model.Employee;
import util.FileHandler;

import java.util.ArrayList;

/**
 * The Receiver class contains some business logic. Almost any object may act as a receiver.
 * Most commands only handle the details of how a request is passed to the receiver, while the
 * receiver itself does the actual work.
 * <p>
 * Include a storeToFile() method in your class that handles the employeeList store.
 */
public class Receiver {

    private ArrayList<Employee> employeeList = new ArrayList<>();

    public Receiver() {
        employeeList = FileHandler.populateList(employeeList);
    }

    public void add(Employee employee) {
        employeeList.add(employee);
    }

    public void update(int index, Employee employeeDetail) {
        employeeList.set(index, employeeDetail);
    }

    public void delete(Employee employee) {
        employeeList.remove(employee);
    }

    public void list() {
        int number = 1;
        System.out.println("List");
        for (Employee e : employeeList) {
            System.out.printf("%02d. %s%n", number, e);
            number++;
        }
    }

    public int getEmployeeCount() {
        return employeeList.size();
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public Employee getEmployee(int index) {
        return employeeList.get(index);
    }
}
