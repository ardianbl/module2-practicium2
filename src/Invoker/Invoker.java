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
    private Command[] cmdToExecute;

    public void setCommandsForExecution(Command[] commandToAdd) {
        this.cmdToExecute = commandToAdd;
    }

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
