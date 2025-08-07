package Command;

import Exceptions.CommandException;
import core.Receiver;

import java.util.Stack;

/**
 * Concrete Command for undo operation <p>
 * Takes in a Receiver object and a Stack of command history
 */

public class UndoCommand implements Command {
    /**
     * Receiver instance
     */
    private Receiver receiver;
    /**
     * Variable for storing a Stack of Command history
     */
    private Stack<Command> history;

    /**
     * UndoCommand constructor
     *
     * @param receiver Receiver instance
     * @param history  Stack of Command history
     */
    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        this.history = history;
    }

    /**
     * Overridden execute method <p>
     * Call the undo operation from respective concrete command class
     * and remove the most recent command history
     */
    @Override
    public void execute() {
        if (history.isEmpty()) {
            throw new CommandException("(Undo) There are no commands to undo.");
        }
        Command lastCommand = history.pop();
        lastCommand.undo();
        System.out.println("Undo");
    }
}
