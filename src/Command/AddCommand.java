package Command;

import Exceptions.CommandException;
import core.*;

/**
 * Concrete Command for adding Employee details into the ArrayList <p>
 * Takes in a Receiver object and a String payload {@code data1 data2 data3}
 */

public class AddCommand implements Command {
    /**
     * Receiver instance
     */
    private Receiver receiver;
    /**
     * Input payload, i.e. (data1 data2 data3)
     */
    private String payload;
    /**
     * Variable for storing employee's first name
     */
    private String first_name;
    /**
     * Variable for storing employee's last name
     */
    private String last_name;
    /**
     * Variable for storing employee's email address / ID
     */
    private String emailAddress;
    /**
     * Variable for storing the index for deletion
     */
    private int indexToDelete;

    /**
     * Add Command constructor
     *
     * @param receiver Receiver object
     * @param payload  String object in the form of {@code data1 data2 data3}
     */
    public AddCommand(Receiver receiver, String payload) {
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

        // check if payload contain exactly 3 data
        if (data.length != 3) {
            throw new CommandException("(Add) Incorrect input length.");
        }

        first_name = Validator.capitalize(data[0]);
        last_name = Validator.capitalize(data[1]);

        if(data[2].contains("@") && Validator.isValidEmail(data[2])) {
            emailAddress = data[2];
        } else if (Validator.isValidId(data[2])) {
            emailAddress = Validator.capitalize(data[2]);
        } else {
            throw new CommandException("(Add) Data3 format is invalid.");
        }

        // send add entry to Receiver
        receiver.addEntry(first_name, last_name, emailAddress);

        // set index for undo command
        // count-1 as it is 0-based index
        indexToDelete = receiver.getEmployeeCount() - 1;

        // print out message for successful operation
        System.out.println("Added: " + first_name + " " + last_name + " " + emailAddress + ".");
    }

    /**
     * Overridden undo method <p>
     * Undo addition, i.e. delete the entry
     */
    @Override
    public void undo() {
        receiver.deleteEntry(indexToDelete);
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
