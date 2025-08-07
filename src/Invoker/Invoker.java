package Invoker;

import Command.Command;
import Exceptions.CommandException;

import java.util.Stack;

/**
 * The Invoker.Invoker class is responsible for initiating the commands. It must have a field to store the
 * reference to a Command object. The Invoker.Invoker triggers the commands instead of sending the
 * request directly to the receiver. The Invoker is not responsible for creating the Command
 * object, it usually gets a pre-created Command from the client.
 */

public class Invoker {

    /** *
     * Variable for the list of command object to execute.
     *
     **/
    private Command[] cmdToExecute;

    /**
     * Initializes {@code smdToExecute} using the list of commands provided by the client.
     *
     * @param commandToAdd the list of commands to be executed
     */
    public void setCommandsForExecution(Command[] commandToAdd) {
        this.cmdToExecute = commandToAdd;
    }

    /**
     * Iterates through the list of commands in {@code cmdToExecute}, executes each command,
     * and stores it in {@code history} for potential undo operations.
     *
     * @param history the stack used to store executed commands for undo functionality.
     */
    public void executeCommand(Stack<Command> history) {
        for (Command cmd : cmdToExecute) {
            try {
                cmd.execute();
                if (cmd.isStackItem()) {
                    history.push(cmd);
                }
            } catch (CommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
