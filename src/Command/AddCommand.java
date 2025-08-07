package Command;

import Exceptions.CommandException;
import core.Receiver;
import core.Validator;


/**
 * Command for adding Employee details into the ArrayList.<br>
 * Takes in a Receiver object and a String payload (data1 data2 data3)
 */

public class AddCommand implements Command {
    private Receiver receiver;
    private String payload;
    private String first_name;
    private String last_name;
    private String emailAddress;
    private int indexToDelete;

    /**
     * Add Command constructor
     *
     * @param receiver a Receiver object
     * @param payload  a String object in the form of "data1 data2 data3"
     */
    public AddCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        this.payload = payload;
    }

    @Override
    public void execute() {
        String[] result = payload.trim().split("\\s+");
        if (result.length != 3) {
            throw new CommandException("Incorrect input length.");
        }

        first_name = Validator.capitalize(result[0]);
        last_name = Validator.capitalize(result[1]);
        emailAddress = result[2];

        if (!Validator.isValidData3(emailAddress)) {
            throw new CommandException("Data3 format is invalid.");
        }

        receiver.addEntry(first_name, last_name, emailAddress);
        indexToDelete = receiver.getEmployeeCount() - 1;
        System.out.println("Added " + first_name + " " + last_name + " to " + emailAddress + ".");
    }

    @Override
    public void undo() {
        receiver.deleteEntry(indexToDelete);
    }

    @Override
    public boolean isStackItem() {
        return true;
    }
}
