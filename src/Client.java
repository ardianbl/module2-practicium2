import command.*;
import core.Command;
import core.Receiver;
import core.Invoker;
import exceptions.CommandException;
import util.FileHandler;

import java.util.Stack;

/**
 * The Client creates and configures the concrete command objects. The client must pass all
 * the command parameters, including a receiver instance, into the command’s constructor.
 * After that, the resulting command may be associated with one or multiple senders.
 */

public class Client {


    public static void main(String[] args) throws CommandException {


        Stack<Command> history = new Stack<>();
        Receiver receiver = new Receiver();

            Command [] allCommands = {
//                    new ListCommand(receiver),
                    new UpdateCommand(receiver,"1"), //
                    new UpdateCommand(receiver,"1 firstName lastName"),
                    new UpdateCommand(receiver,"1 firstName lastName .ardian@email.com"), //
                    new UpdateCommand(receiver,"1 firstName lastName -ardian@email.com"), //
                    new UpdateCommand(receiver,"1 firstName lastName _ardian@email.com"),
                    new UpdateCommand(receiver,"1 firstName lastName _a..rdian@email.com"), //
                    new UpdateCommand(receiver,"1 firstName lastName _a--rdian@email.com"), //
                    new UpdateCommand(receiver,"1 firstName lastName _a-r-dian@email.com"),
                    new UpdateCommand(receiver,"1 firstName lastName _ardian@.email.com"), //
                    new UpdateCommand(receiver,"1 firstName lastName _ardian@email..com"), //
                    new UpdateCommand(receiver,"1 firstName lastName _ardian@-email.com"), //
                    new UpdateCommand(receiver,"1 firstName lastName _ardian@email-.com"), //
                    new UpdateCommand(receiver,"1 firstName lastName _ardian@_email.com"), //
                    new UpdateCommand(receiver,"1 firstName lastName _ardian@_email.comm"), //
                    new UndoCommand(history),
                    new AddCommand(receiver,"firstName no email@gmail.com"),
                    new AddCommand(receiver,"firstName no email_"),
//                    new UndoCommand(history),
                    new ListCommand(receiver),
                    new DeleteCommand(receiver,"1, 2"),
                    new UndoCommand(history),
                    new ListCommand(receiver),

//                    new DeleteCommand(receiver,"firstName"),

            };
            Invoker control = new Invoker();
            control.setCommandsForExecution(allCommands);
            control.executeCommand(history);
            FileHandler.storeToFile(receiver);




        //This is from the ArrayList created from the receiver.
//        System.out.println(receiver.getEmployeeList().toString());



    }

}