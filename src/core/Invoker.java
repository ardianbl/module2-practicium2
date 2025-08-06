package core;

import exceptions.CommandException;

import java.util.Stack;

/**
 * The core.Invoker class is responsible for initiating the commands. It must have a field to store the
 * reference to a command object. The core.Invoker triggers the commands instead of sending the
 * request directly to the receiver. The invoker is not responsible for creating the command
 * object, it usually gets a pre-created command from the client.
 */

public class Invoker {
    private Command[] cmdToExecute;

    public void setCommandsForExecution(Command[] commandToAdd) {
        this.cmdToExecute = commandToAdd;
    }

    public void executeCommand(Stack<Command> history) {
        //Execute is link to the execute() in each of the subclass;
        // Update the stackHistory;
        for(Command cmd : cmdToExecute)
        {
            try {
                cmd.execute();
                // !!!!!!use instance of to update history only for add,delete and update.
                if ()
                history.push(cmd);
            } catch (CommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

//        if(!history.isEmpty())
//        {
//            Command lastCommand = history.peek();
//            lastCommand.execute();
//        }
    }
}
