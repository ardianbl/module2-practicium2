package command;

import core.Command;
import core.Receiver;
import exceptions.CommandException;
import model.Employee;

/**
 * Concrete Commands implements the various kinds of commands. A concrete command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete command.
 */

public class DeleteCommand implements Command {
    private Receiver receiver;
    private String payload;
    private int index;
    private Employee deletedEmployee;

    public DeleteCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        this.payload = payload;
    }

    @Override
    public void execute() {
        try {
            index = Integer.parseInt(payload.trim()) - 1;
            deletedEmployee = receiver.getEmployee(index);
            receiver.delete(deletedEmployee);
            System.out.println("Delete");
        } catch (Exception e) {
            throw new CommandException("Failed to delete employee: Invalid index.");
        }
    }

    @Override
    public void undo() {
        receiver.add(deletedEmployee);
    }
}
