package Command;

import Exceptions.CommandException;
import core.Receiver;
import core.Validator;

/**
 * Concrete Command for updating Employee details in the ArrayList <p>
 * Takes in a Receiver object and a String payload {@code index data1 data2 data3}
 */

public class UpdateCommand implements Command {
    /**
     * Receiver instance
     */
    private Receiver receiver;
    /**
     * Input payload, i.e. (index data1 data2 data3)
     */
    private String payload;
    /**
     * Variable for storing employee's updated first name
     */
    private String new_first_name;
    /**
     * Variable for storing employee's original first name
     */
    private String old_first_name;
    /**
     * Variable for storing employee's updated last name
     */
    private String new_last_name;
    /**
     * Variable for storing employee's original last name
     */
    private String old_last_name;
    /**
     * Variable for storing employee's updated email address / ID
     */
    private String new_email;
    /**
     * Variable for storing employee's original email address / ID
     */
    private String old_email;
    /**
     * Variable for storing the index number for ArrayList manipulation
     */
    private int indexNumber;
    /**
     * Variable for storing the original employee details
     */
    private String oldEmployeeDetails;

    /**
     * UpdateCommand constructor
     *
     * @param receiver Receiver instance
     * @param payload  String object in the form of {@code index data1 data2 data3}
     */
    public UpdateCommand(Receiver receiver, String payload) {
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

        // check if payload contain between 2 and 4 data
        if (data.length < 2 || data.length > 4) {
            throw new CommandException("(Update) Incorrect input length.");
        }

        // check for empty list
        if (receiver.getEmployeeCount() == 0) {
            throw new CommandException("(Update) No entry found.");
        }

        // check if index is an integer and between 0 and count-1
        try {
            indexNumber = Integer.parseInt(data[0]) - 1;
            if (indexNumber < 0 || indexNumber > receiver.getEmployeeCount()) {
                throw new CommandException("(Update) Index out of bounds.");
            }
        } catch (NumberFormatException e) {
            throw new CommandException("(Update) Index number must be an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("(Update) Index out of bounds.");
        }

        new_first_name = Validator.capitalize(data[1]);
        new_last_name = data.length > 2 ? Validator.capitalize(data[2]) : null;

        // check if data3 is an email or a string
        if (data.length > 3) {
            if (data[3].contains("@") && Validator.isValidEmail(data[3])) {
                new_email = data[3];
            } else if (Validator.isValidId(data[3])) {
                new_email = Validator.capitalize(data[3]);
            } else {
                throw new CommandException("(Update) Data3 format is invalid.");
            }
        } else {
            new_email = null;
        }

        // store old employee details for undo operation

        oldEmployeeDetails = receiver.getEmployeeDetails(indexNumber);
        String[] oldEmployeeDetailsParts = oldEmployeeDetails.split(" ");
        old_first_name = oldEmployeeDetailsParts[0];
        old_last_name = oldEmployeeDetailsParts[1];
        old_email = oldEmployeeDetailsParts[2];

        // set last name and email based on original/updated value
        new_last_name = new_last_name != null ? new_last_name : old_last_name;
        new_email = new_email != null ? new_email : old_email;

        receiver.updateEntry(indexNumber, new_first_name, new_last_name, new_email);
        System.out.println("Updated: "
                + (indexNumber + 1) + ". "
                + new_first_name + " "
                + new_last_name + " "
                + new_email
        );
    }

    /**
     * Overridden undo method <p>
     * Undo updating, i.e. revert the update
     */
    @Override
    public void undo() {
        receiver.updateEntry(indexNumber, old_first_name, old_last_name, old_email);
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
