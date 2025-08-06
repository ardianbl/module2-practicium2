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

public class UpdateCommand implements Command {
    private Receiver receiver;
    private String payload;
    private int index;
    private Employee newEmployeeObject;
    private Employee oldEmployeeObject;

    public UpdateCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        this.payload = payload;
    }

    @Override
    public void execute() {
        try {
            String[] parts = payload.trim().split("\\s+", 4);
            if (parts.length < 2) {
                throw new CommandException("Insufficient Update arguments.");
            }

            index = Integer.parseInt(parts[0]) - 1; // Convert from 1-based to 0-based
            String firstName = parts.length > 1 ? parts[1] : null;
            String lastName = parts.length > 2 ? parts[2] : null;
            String email = parts.length > 3 ? parts[3] : null;

            if (email != null && !Validator.isValidData3(email)) {
                throw new CommandException("Data 3 is invalid.");
            }

            oldEmployeeObject = receiver.getEmployee(index);
            newEmployeeObject = new Employee(
                    firstName != null ? firstName : oldEmployeeObject.getFirstName(),
                    lastName != null ? lastName : oldEmployeeObject.getLastName(),
                    email != null ? email : oldEmployeeObject.getEmail()
            );

            receiver.update(index, newEmployeeObject);
            System.out.println("update");
        } catch (Exception e) {
            throw new CommandException("Failed to update employee:  " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        receiver.update(index, oldEmployeeObject);
    }
}
