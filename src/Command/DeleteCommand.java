package Command;

import Exceptions.CommandException;
import core.*;

/**
 * Concrete Command for deleting Employee details from the ArrayList <p>
 * Takes in a Receiver object and a String payload {@code index}
 */

public class DeleteCommand implements Command {
    /**
     * Receiver instance
     */
    private Receiver receiver;
    /**
     * Input payload, i.e. (index)
     */
    private String payload;
    /**
     * Variable for storing the index number for ArrayList manipulation
     */
    private int indexNumber;
    /**
     * Variable for storing the employee's details for undo operation
     */
    private String employeeToBeAdded;

    /**
     * DeleteCommand constructor
     * @param receiver Receiver instance
     * @param payload String object in the form of {@code index}
     */
    public DeleteCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        this.payload = payload;
    }

    /**
     * Overridden execute method <p>
     * To sanitise the payload and pass it to Receiver class
     */
    @Override
    public void execute() {
        String[] data = payload.trim().split("\\s+");

        // check if payload contain exactly 1 data
        if (data.length != 1) {
            throw new CommandException("(Delete) Incorrect input length.");
        }

        // check if index is an integer and between 0 and count-1
        try {
            indexNumber = Integer.parseInt(data[0]) - 1;
            if (indexNumber < 0 || indexNumber > receiver.getEmployeeCount()) {
                throw new CommandException("(Delete) Index out of bounds.");
            }
        } catch (NumberFormatException e) {
            throw new CommandException("(Delete) Index number must be an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("(Delete) Index out of bounds.");
        }

        // store employee details for undo operation
        employeeToBeAdded = receiver.getEmployeeList().get(indexNumber);

        receiver.deleteEntry(indexNumber);
        System.out.println("Deleted: "
                + (indexNumber + 1) + ". "
                + employeeToBeAdded
        );
    }

    /**
     * Overridden undo method <p>
     * Undo deletion, i.e. add back the entry
     */
    @Override
    public void undo()
    {
        receiver.setEntry(indexNumber, employeeToBeAdded);
    }

    /**
     * Overridden isStackItem method <p>
     * To indicate that this command is to be added into the history stack
     *
     * @return true
     */
    @Override
    public boolean isStackItem() {
        return true;
    }
}
