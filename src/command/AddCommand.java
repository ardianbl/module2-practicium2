package command;

import core.Command;
import core.Receiver;
import exceptions.CommandException;
import model.Employee;
import util.Validator;

/**
 * Concrete Commands implements the various kinds of commands. A concrete command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete command.
 */

public class AddCommand implements Command {
    private Receiver receiver;
    private String payload;
    private Employee newEmployeeObject;

    public AddCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        this.payload = payload;
    }

    @Override
    public void execute() {
        String[] parts = payload.trim().split("\\s+", 3);
        if (parts.length != 3) {
            throw new CommandException("Command requires 3 arguments.");
        }

        String firstName = parts[0];
        String lastName = parts[1];
        String email = parts[2];

        if (!Validator.isValidData3(email)) {
            throw new CommandException("Data 3 is invalid.");
        }

        newEmployeeObject = new Employee(firstName, lastName, email);
        receiver.add(newEmployeeObject);
        System.out.println("add");
    }

    @Override
    public void undo() {
        receiver.delete(newEmployeeObject);
    }
}
