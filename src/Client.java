import command.*;
import core.Receiver;
import invoker.Invoker;

import java.util.Stack;

/**
 * The Client creates and configures the concrete command objects. The client must pass all
 * the command parameters, including a receiver instance, into the command’s constructor.
 * After that, the resulting command may be associated with one or multiple senders.
 */

public class Client {


    public static void main(String[] args) throws Exceptions.CommandException {


        Stack<Command> history = new Stack<>();
        Receiver data = new Receiver();

        Command [] allCommands = {
//                new UpdateCommand(data,"1 name"),
//                new AddCommand(data,"firstName no email@gmail.com"),
//                new AddCommand(data,"firstName no email_"),
                new DeleteCommand(data,"2"),
                new ListCommand(data)
        };


        Invoker control = new Invoker();
        control.setCommandsForExecution(allCommands);
        control.executeCommand(history);

        //This is from the ArrayList created from the receiver.
        System.out.println(data.getEmployeeList().toString());



    }

}