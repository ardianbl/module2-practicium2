import Command.*;
import Invoker.Invoker;
import core.Receiver;

import java.util.Stack;

/**
 * The Client creates and configures the concrete Command objects. The client must pass all
 * the Command parameters, including a receiver instance, into the Command’s constructor.
 * After that, the resulting Command may be associated with one or multiple senders.
 */

public class Client {


    public static void main(String[] args) throws Exceptions.CommandException {
        Stack<Command> history = new Stack<>();
        Receiver receiver = new Receiver();

        Command[] allCommands = {
                /* TEST FOR ADD COMMAND */
                new AddCommand(receiver, "name1 name2 email@email.com"),    // valid
                new AddCommand(receiver, "name3 name4 email09_"),           // valid
                new AddCommand(receiver, "name1 email@email.com"),          // invalid
                new AddCommand(receiver, "name1 name2 email@email.com ."),  // invalid
                new AddCommand(receiver, "name1 name2 .email@email.com"),   // invalid
                new AddCommand(receiver, "name1 name2 -email@email.com"),   // invalid
                new AddCommand(receiver, "name1 name2 _email@email.com"),   // valid
                new AddCommand(receiver, "name1 name2 email.@email.com"),   // invalid
                new AddCommand(receiver, "name1 name2 email..@email.com"),  // invalid
                new AddCommand(receiver, "name1 name2 email-@email.com"),   // invalid
                new AddCommand(receiver, "name1 name2 email--@email.com"),  // invalid
                new AddCommand(receiver, "name1 name2 email_@email.com"),   // valid
                new AddCommand(receiver, "name1 name2 email__@email.com"),  // valid
                new AddCommand(receiver, "name1 name2 e..mail@email.com"),  // invalid
                new AddCommand(receiver, "name1 name2 e--mail@email.com"),  // invalid
                new AddCommand(receiver, "name1 name2 e__mail@email.com"),  // valid
                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL.com"),    // valid
                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL.COM"),    // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@.EMAIL.com"),   // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@-EMAIL.com"),   // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL..com"),   // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL-.com"),   // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@E.MAIL.com"),   // valid
                new AddCommand(receiver, "name1 name2 EMAIL@E-MAIL.com"),   // valid
                new AddCommand(receiver, "name1 name2 EMAIL@E..MAIL.com"),  // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@E--MAIL.com"),  // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@E--MAIL.co."),  // invalid
                new ListCommand(receiver),

                /* TEST FOR UPDATE COMMAND */
// ✅ VALID TEST CASES
                new UpdateCommand(receiver, "1 name"),
                new UpdateCommand(receiver, "1 John Doe john.doe-email_2022@company-site.com"),
                new UpdateCommand(receiver, "2 Jane Dae valid-email.23@abc-site.org"),
                new UpdateCommand(receiver, "3 Alice Bob a_b.c-d@domain-name.co"),
                new UpdateCommand(receiver, "4 Foo Bar user_name@doma1n.io"),
                new ListCommand(receiver),

// ❌ INVALID TEST CASES
                new UpdateCommand(receiver, "John Doe john.doe@example.com"),           // Missing index
                new UpdateCommand(receiver, "1"),                                         // Missing data
                new UpdateCommand(receiver, "1 John Doe john..doe@example.com"),         // Consecutive dots in local part
                new UpdateCommand(receiver, "1 John Doe john.doe-@example.com"),         // Dash at end of local part
                new UpdateCommand(receiver, "1 John Doe .john.doe@example.com"),         // Dot at start of local part
                new UpdateCommand(receiver, "1 John Doe john.doe@company--site.com"),    // Consecutive dashes in domain
                new UpdateCommand(receiver, "1 John Doe john.doe@company-site.CoM"),    // Uppercase in domain suffix
                new UpdateCommand(receiver, "1 John Doe john.doe@company-site.x"),      // Domain suffix too short
                new UpdateCommand(receiver, "1 John Doe john.doe@company-site.abcd"),   // Domain suffix too long
                new UpdateCommand(receiver, "1 John Doe john#doe@example.com"),         // Invalid character in local part
                new UpdateCommand(receiver, "1 John Doe john.doe@"),                     // Missing domain
                new UpdateCommand(receiver, "1 John Doe user_@domain.com_"),            // Invalid underscore at end of domain
                new ListCommand(receiver),

                /* TEST FOR DELETE COMMAND */
// ✅ VALID TEST CASES
                new DeleteCommand(receiver, "1"),        // ✅ Valid: typical index
                new DeleteCommand(receiver, "5"),       // ✅ Valid: larger index

// ❌ INVALID TEST CASES
                new DeleteCommand(receiver, "999"),      // ❌ Index out of bounds
                new DeleteCommand(receiver, ""),         // ❌ Empty payload
                new DeleteCommand(receiver, "abc"),      // ❌ Non-numeric input
                new DeleteCommand(receiver, "1abc"),     // ❌ Alphanumeric string
                new DeleteCommand(receiver, "-1"),       // ❌ Negative index
                new DeleteCommand(receiver, "0"),        // ❌ Zero index (if 1-based indexing is assumed)
                new DeleteCommand(receiver, " "),        // ❌ Space as input
                new DeleteCommand(receiver, "1 2"),      // ❌ Extra unexpected arguments
                new DeleteCommand(receiver, "3.14"),     // ❌ Decimal number
                new DeleteCommand(receiver, "#1"),       // ❌ Symbol-prefixed input

                /* TEST FOR UNDO COMMAND */
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new ListCommand(receiver),
        };

        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(allCommands);
        invoker.executeCommand(history);
        receiver.storeToFile();
    }
}
