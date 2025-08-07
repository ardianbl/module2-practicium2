package Command;

import Exceptions.CommandException;
import core.Receiver;
import core.Validator;

/**
 * Concrete Commands implements the various kinds of commands. A concrete Command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete Command.
 */

public class UpdateCommand implements Command {
    private Receiver receiver;
    private String payload;
    private String new_first_name;
    private String old_first_name;
    private String new_last_name;
    private String old_last_name;
    private String new_email;
    private String old_email;
    private int indexNumber;
    private String oldEmployeeDetails;

    public UpdateCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        this.payload = payload;
    }


    @Override
    public void execute() {
        String[] data = payload.trim().split("\\s+");

        if (data.length < 2 || data.length > 4) {
            throw new CommandException("Incorrect input length.");
        }

        if (Validator.isIndexValid(receiver, data[0])) {
            indexNumber = Integer.parseInt(data[0]) - 1;
        }

        new_first_name = Validator.capitalize(data[1]);
        new_last_name = data.length > 2 ? Validator.capitalize(data[2]) : null;
        new_email = data.length > 3 ? data[3] : null;

        if (new_email != null && !Validator.isValidData3(new_email)) {
            throw new CommandException("Data3 format is invalid.");
        }

        oldEmployeeDetails = receiver.getEmployeeDetails(indexNumber);
        String[] oldEmployeeDetailsParts = oldEmployeeDetails.split(" ");
        old_first_name = oldEmployeeDetailsParts[0];
        old_last_name = oldEmployeeDetailsParts[1];
        old_email = oldEmployeeDetailsParts[2];

        new_last_name = new_last_name != null ? new_last_name : old_last_name;
        new_email = new_email != null ? new_email : old_email;

        receiver.updateEntry(indexNumber, new_first_name, new_last_name, new_email);
        System.out.println("Updated " + new_first_name + " " + new_last_name + " " + new_email);
    }

    @Override
    public void undo() {
        receiver.updateEntry(indexNumber, old_first_name, old_last_name, old_email);
    }

    @Override
    public boolean isStackItem() {
        return true;
    }
}
