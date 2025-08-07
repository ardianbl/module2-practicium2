package Command;

import core.*;

/**
 * Concrete Commands implements the various kinds of commands. A concrete Command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete Command.
 */

public class ListCommand implements Command {
    private Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.list();
    }
}
