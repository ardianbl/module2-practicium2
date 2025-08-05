package command;

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

    public DeleteCommand(Receiver receiver, int index) {
        this.receiver = receiver;
//        Validator.isIndexValid(index);
        this.index = index;
    }

    @Override
    public void execute() {
        receiver.delete(index);
    }
}
