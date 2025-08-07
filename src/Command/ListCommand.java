package Command;

import core.Receiver;

/**
 * Concrete Command for printing the Employees in the ArrayList <p>
 * Takes in only a Receiver object
 */

public class ListCommand implements Command {
    /**
     * Receiver instance
     */
    private Receiver receiver;

    /**
     * ListCommand constructor
     *
     * @param receiver Receiver instance
     */
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Overridden execute method <p>
     * To make a call to the Receiver class
     */
    @Override
    public void execute() {
        receiver.list();
    }
}
