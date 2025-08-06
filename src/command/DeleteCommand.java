package command;

import Exceptions.CommandException;
import core.*;

/**
 * Concrete Commands implements the various kinds of commands. A concrete command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete command.
 */

public class DeleteCommand implements Command {
    private Receiver receiver;
    private int index;
    private String toBeDelete;
    private String params;


    public DeleteCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {
        String[] result = params.replaceAll("\\s{2,}", " ").split(" ");
        try{
            index  = Integer.parseInt(result[0]) == 1 ? 0 : Integer.parseInt(result[0])-1 ;
            toBeDelete = receiver.getEmployeeList().get(index);
        }
        catch(NumberFormatException e) {
            throw new CommandException("Error: Invalid input.");
        }
        receiver.delete(index);
    }

    @Override
    public void undo()
    {
        receiver.setEntry(index,toBeDelete);
        //must get the index and the item to put back.
    }

}
