package invoker;

import command.Command;

import java.util.Stack;

/**
 * The invoker.Invoker class is responsible for initiating the commands. It must have a field to store the
 * reference to a command object. The invoker.Invoker triggers the commands instead of sending the
 * request directly to the receiver. The invoker is not responsible for creating the command
 * object, it usually gets a pre-created command from the client.
 */

public class Invoker {
    private Command[] cmdToExecute;

    public void setCommandsForExecution(Command[] cmdToExecute) {

    }
    public void executeCommand(Stack<Command> history) {
    }
}
