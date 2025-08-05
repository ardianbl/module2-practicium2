package command;

import core.*;

/**
 * Concrete Commands implements the various kinds of commands. A concrete command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete command.
 */

public class UpdateCommand implements Command {
    private Receiver receiver;
    private int index;
    private String firstName, lastName, email;

    public UpdateCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
        String[] payloadArray = Validator.checkUpdatePayloadValidity(payload);
        index = Integer.parseInt(payloadArray[0]);
        firstName = payloadArray[1];
        lastName = payloadArray[2];
        email = payloadArray[3];
    }

    @Override
    public void execute() {
        receiver.update(index, firstName, lastName, email);
    }
}
