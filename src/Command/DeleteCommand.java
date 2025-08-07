package Command;

import Exceptions.CommandException;
import core.*;

/**
 * Concrete Commands implements the various kinds of commands. A concrete Command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete Command.
 */

public class DeleteCommand implements Command {
    private Receiver receiver;
    private String payload;
    private int indexNumber;
    private String employeeToBeAdded;


    public DeleteCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        this.payload = payload;
    }

    @Override
    public void execute() {
        String[] data = payload.trim().split("\\s+");
        if (data.length != 1) {
            throw new CommandException("Incorrect input length.");
        }

        if (Validator.isIndexValid(receiver, data[0])) {
            indexNumber = Integer.parseInt(data[0]) - 1;
        }

        employeeToBeAdded = receiver.getEmployeeList().get(indexNumber);
        receiver.deleteEntry(indexNumber);
        System.out.println("Deleted index " +  indexNumber);
    }

    @Override
    public void undo()
    {
        receiver.setEntry(indexNumber, employeeToBeAdded);
        //must get the indexNumber and the item to put back.
    }

    @Override
    public boolean isStackItem() {
        return true;
    }
}
