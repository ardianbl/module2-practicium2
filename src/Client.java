import Command.AddCommand;
import Command.UpdateCommand;
import Command.Command;
import Data.Receiver;
import Exceptions.customException;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Client creates and configures the concrete command objects. The client must pass all
 * the command parameters, including a receiver instance, into the command’s constructor.
 * After that, the resulting command may be associated with one or multiple senders.
 */

public class Client {


    public static void main(String[] args) throws customException {


        Stack<Command> history = new Stack<>();
        Receiver data = new Receiver();

        Command [] allCommands = {
                new UpdateCommand(data,"1 name"),
                new AddCommand(data,"firstName no email@gmail.com"),
                new AddCommand(data,"firstName no email_"),
        };


        Invoker control = new Invoker();
        control.setCommandsForExecution(allCommands);
        control.executeCommand(history);

        //This is from the ArrayList created from the receiver.
        System.out.println(data.getData().toString());



    }

}