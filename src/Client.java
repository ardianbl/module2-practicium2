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
        Receiver receiver = new Receiver();

        Command [] allCommands = {
//                new UpdateCommand(receiver, "1"), //
//                new UpdateCommand(receiver, "1 firstName lastName"),
//                new UpdateCommand(receiver, "1 firstName lastName .ardian@email.com"), //
//                new UpdateCommand(receiver, "1 firstName lastName -ardian@email.com"), //
//                new UpdateCommand(receiver, "1 firstName lastName _ardian@email.com"),
//                new UpdateCommand(receiver, "1 firstName lastName _a..rdian@email.com"), //
//                new UpdateCommand(receiver, "1 firstName lastName _a--rdian@email.com"), //
//                new UpdateCommand(receiver, "1 firstName lastName _a-r-dian@email.com"),
//                new UpdateCommand(receiver, "1 firstName lastName _ardian@.email.com"), //
//                new UpdateCommand(receiver, "1 firstName lastName _ardian@email..com"), //
//                new UpdateCommand(receiver, "1 firstName lastName _ardian@-email.com"), //
//                new UpdateCommand(receiver, "1 firstName lastName _ardian@email-.com"), //
//                new UpdateCommand(receiver, "1 firstName lastName _ardian@_email.com"), //
//                new UpdateCommand(receiver, "1 firstName lastName _ardian@_email.comm"), //

                new AddCommand(receiver,"name1 name2 email@gmail.com"),
                new AddCommand(receiver,"name2 name3 email@gmail.com"),
                new AddCommand(receiver,"name3 name4 email@gmail.com"),
                new ListCommand(receiver),

                new DeleteCommand(receiver,"1"),
                new DeleteCommand(receiver,"1"),
                new ListCommand(receiver),

                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new ListCommand(receiver),

                new UpdateCommand(receiver,"1 name"),
                new ListCommand(receiver),

                new UndoCommand(receiver, history),
                new ListCommand(receiver),

//                new AddCommand(receiver, "firstName no email@gmail.com"),
//                new AddCommand(receiver, "firstName no email_"),
////                    new UndoCommand(history),
//                new ListCommand(receiver),
//                new DeleteCommand(receiver, "1, 2"),
//                new UndoCommand(receiver, history),
//                new ListCommand(receiver),
        };

        Invoker control = new Invoker();
        control.setCommandsForExecution(allCommands);
        control.executeCommand(history);
        receiver.storeToFile();

        //This is from the ArrayList created from the receiver.
        System.out.println(receiver.getEmployeeList().toString());

    }

}
