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
                new ListCommand(receiver),
                /* TEST FOR ADD COMMAND */
                // ✅ VALID TEST CASES
                new AddCommand(receiver, "NAME1 name2 email@email.com"),    // valid
                new AddCommand(receiver, "^!@#$ name4 email09_"),           // valid
                new AddCommand(receiver, "name1 name2 _email@email.com"),   // valid
                new AddCommand(receiver, "name1 name2 email_@email.com"),   // valid
                new AddCommand(receiver, "name1 name2 email__@email.com"),  // valid
                new AddCommand(receiver, "name1 name2 e__mail@email.com"),  // valid
                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL.com"),    // valid
                new AddCommand(receiver, "name1 name2 EMAIL@E.MAIL.com"),   // valid
                new AddCommand(receiver, "name1 name2 EMAIL@E-MAIL.com"),   // valid

                // ❌ INVALID TEST CASES
                new AddCommand(receiver, "name1 email@email.com"),          // invalid
                new AddCommand(receiver, "name1 name2 email@email.com ."),  // invalid
                new AddCommand(receiver, "name1 name2 .email@email.com"),   // invalid
                new AddCommand(receiver, "name1 name2 -email@email.com"),   // invalid
                new AddCommand(receiver, "name1 name2 email.@email.com"),   // invalid
                new AddCommand(receiver, "name1 name2 email..@email.com"),  // invalid
                new AddCommand(receiver, "name1 name2 email-@email.com"),   // invalid
                new AddCommand(receiver, "name1 name2 email--@email.com"),  // invalid
                new AddCommand(receiver, "name1 name2 e..mail@email.com"),  // invalid
                new AddCommand(receiver, "name1 name2 e--mail@email.com"),  // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL.COM"),    // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@.EMAIL.com"),   // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@-EMAIL.com"),   // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL..com"),   // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL-.com"),   // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@E..MAIL.com"),  // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@E--MAIL.com"),  // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@E--MAIL.co."),  // invalid
                new ListCommand(receiver),
                /* END OF TEST */


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
                new UpdateCommand(receiver, "1 name lastname .abc@domain.com"),          // starts with dot
                new UpdateCommand(receiver, "1 name lastname abc.@domain.com"),          // ends with dot
                new UpdateCommand(receiver, "1 name lastname -abc@domain.com"),          // starts with dash
                new UpdateCommand(receiver, "1 name lastname abc-@domain.com"),          // ends with dash
                new UpdateCommand(receiver, "1 name lastname abc..def@domain.com"),      // consecutive dots
                new UpdateCommand(receiver, "1 name lastname abc--def@domain.com"),      // consecutive dashes
                new UpdateCommand(receiver, "1 name lastname abc.-def@domain.com"),      // mixed consecutive dot/dash
                new UpdateCommand(receiver, "1 name lastname user@-abc.com"),            // starts with dash
                new UpdateCommand(receiver, "1 name lastname user@abc-.com"),            // ends with dash
                new UpdateCommand(receiver, "1 name lastname user@.abc.com"),            // starts with dot
                new UpdateCommand(receiver, "1 name lastname user@abc..com"),            // consecutive dots
                new UpdateCommand(receiver, "1 name lastname user@abc--def.com"),        // consecutive dashes
                new UpdateCommand(receiver, "1 name lastname user@abc.c"),               // domain suffix too short
                new UpdateCommand(receiver, "1 name lastname user@abc.comm"),            // domain suffix too long
                new UpdateCommand(receiver, "1 name lastname user@abc.CoM"),             // uppercase letters in domain suffix
                new UpdateCommand(receiver, "1 name lastname user@abc.123"),             // digits not allowed in suffix
                new UpdateCommand(receiver, "1 name lastname user@abc.c_m"),             // underscore not allowed in suffix
                new UpdateCommand(receiver, "1 name lastname user@abc.def-"),            // ends with dash before suffix
                new ListCommand(receiver),

                // ✅ VALID CASES
//                new UpdateCommand(receiver, "1 alice"),  // Expect data1 converted to "Alice"
//                new UpdateCommand(receiver, "2 john doe john.doe@example.com"),
//                new UpdateCommand(receiver, "3 alice smith alice.smith@domain.com"),
//                new UpdateCommand(receiver, "4 foo bar foo_bar123@abc-site.com"),
//                new UpdateCommand(receiver, "5 john doe _name@domain.com"),
//                new UpdateCommand(receiver, "6 jane doe name__part@domain.com"),
//                new UpdateCommand(receiver, "7 marie curie marie-curie@science-lab.net"),
//                new UpdateCommand(receiver, "8 max payne m.payne@game.io"),
//                new UpdateCommand(receiver, "9 luke skywalker luke@x1y2z3.co"),
//                new ListCommand(receiver),
//                new UpdateCommand(receiver, "1 jean luc jean.luc@domain.com"),
//                new UpdateCommand(receiver, "2 aLiCe McDoNaLd alice@domain.com"),
//                new ListCommand(receiver),

                // ❌ INVALID CASES
                new UpdateCommand(receiver, "john doe john.doe@example.com"),             // Missing index
                new UpdateCommand(receiver, "4"),                                            // Missing data entirely
                new UpdateCommand(receiver, "5 john doe john..doe@example.com"),             // Consecutive dots in local part
                new UpdateCommand(receiver, "6 john doe john.doe-@example.com"),             // Dash at end of local part
                new UpdateCommand(receiver, "7 john doe .john.doe@example.com"),             // Dot at start of local part
                new UpdateCommand(receiver, "8 john doe john.doe@company--site.com"),        // Consecutive dashes in domain
                new UpdateCommand(receiver, "9 john doe john.doe@company-site.CoM"),         // Uppercase in domain suffix
                new UpdateCommand(receiver, "1 john doe john.doe@company-site.x"),           // Domain suffix too short
                new UpdateCommand(receiver, "2 john doe john.doe@company-site.abcd"),        // Domain suffix too long
                new UpdateCommand(receiver, "3 john doe john#doe@example.com"),              // Invalid character in local part
                new UpdateCommand(receiver, "4 john doe john.doe@"),                         // Missing domain after @
                new UpdateCommand(receiver, "5 john doe user_@domain.com_"),                 // Invalid underscore at end of domain
                new UpdateCommand(receiver, "6 john doe john@-abc.com"),                     // Domain starts with dash
                new UpdateCommand(receiver, "7 john doe john@.abc.com"),                     // Domain starts with dot
                new UpdateCommand(receiver, "8 john doe john@abc..com"),                     // Consecutive dots in domain
                new UpdateCommand(receiver, "9 john doe john@abc.12"),                       // Digits in domain suffix
                new UpdateCommand(receiver, "1 john doe john@abc.c_m"),                      // Underscore in domain suffix
                new UpdateCommand(receiver, "2 john doe john@abc.def-"),                     // Ends with dash before suffix
                new ListCommand(receiver),
                /* END OF TEST */


                /* TEST FOR DELETE COMMAND */
                // ✅ VALID TEST CASES
                new DeleteCommand(receiver, "1"),        // ✅ Valid: typical index
                new DeleteCommand(receiver, "8"),       // ✅ Valid: larger index
                new ListCommand(receiver),

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
                new ListCommand(receiver),
                /* END OF TEST */


                /* TEST FOR UNDO COMMAND */
                new UndoCommand(receiver, history), // undo deletion of index 8
                new ListCommand(receiver),
                new UndoCommand(receiver, history), // undo deletion of index 1
                new ListCommand(receiver),
                new UndoCommand(receiver, history), // undo update of "3 Alice Bob a_b.c-d@domain-name.co"
                new ListCommand(receiver),
                new UndoCommand(receiver, history), // undo update of "4 Foo Bar user_name@doma1n.io"
                new ListCommand(receiver),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history), // error
                new ListCommand(receiver), // empty list
                /* END OF TEST */

        };

        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(allCommands);
        invoker.executeCommand(history);
        receiver.storeToFile();
    }
}
