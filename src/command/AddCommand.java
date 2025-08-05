package command;

import core.*;

/**
 * Concrete Commands implements the various kinds of commands. A concrete command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete command.
 */

public class AddCommand implements Command {
    private Receiver receiver;
    private String firstName, lastName, email;

    public AddCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        String[] payloadArray = Validator.checkAddPayloadValidity(payload);
        firstName = payloadArray[0];
        lastName = payloadArray[1];
        email = payloadArray[2];
    }
    @Override
    public void execute() {
        receiver.add(firstName, lastName, email);
    }
}
