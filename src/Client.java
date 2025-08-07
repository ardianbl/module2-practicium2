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
//                new DeleteCommand(receiver, " 0"),      // ❌ Index out of bounds

                /* TEST FOR ADD COMMAND */
//                new AddCommand(receiver, "^na123me1 name2 email@email.com"),    // valid
                new AddCommand(receiver, "entry1 name4 email09_"),           // valid
//                new AddCommand(receiver, "entry2 name4 email09_"),
//                new AddCommand(receiver, "entry3 name4 email09_"),
                new DeleteCommand(receiver, "1"),
//                new DeleteCommand(receiver, "2"),
//                new DeleteCommand(receiver, "3"),
//                new DeleteCommand(receiver, "1"),
//                new AddCommand(receiver, "n22444ame1 na44me2222 email@email.com ."),  // invalid
//                new AddCommand(receiver, "n222ame1 nr34r3ame2 .email @email.com"),   // invalid
//                new AddCommand(receiver, "n43tame1 22n4r43ame2 -email@email.com"),   // invalid
//                new AddCommand(receiver, "nam22e1 name3222re2222  _email@email.com"),   // valid
//                new AddCommand(receiver, "name1 nr34r11134ame2222 222email.@email.com"),   // invalid
//                new AddCommand(receiver, "name1 n111ame2 eggg2 e347899mail-@email.com"),   // invalid
//                new AddCommand(receiver, "na34r34me1 nar34rme2 email --@email.com"),  // invalid
//                new AddCommand(receiver, "n222ame1 name2 email_@emai l.com"),   // valid
//                new AddCommand(receiver, "name1 name2 email__f fs@email.com"),  // valid
//                new AddCommand(receiver, "name1 name2 e..mail@email.com"),  // invalid
//                new AddCommand(receiver, "name1 name2 e--mail@ema il.com"),  // invalid
//                new AddCommand(receiver, "sfdname1 dfname2 e__mail@email.com"),  // valid
//                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL.com"),    // valid
//                new AddCommand(receiver, "namfdsfe1 name2 EMAIL@EMAIL.COM"),    // invalid
//                new AddCommand(receiver, "name1 name2 EMAIL@.EMAIL.com"),   // invalid
//                new AddCommand(receiver, "name1 namfdse2 EMAIL@-EMAIL.com"),   // invalid
//                new AddCommand(receiver, "name1 name2 EMAIL@EMAILsfd..com"),   // invalid
//                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL-.com"),   // invalid
//                new AddCommand(receiver, "name1 name2 EMAIL@E.MAIL.com"),   // valid
//                new AddCommand(receiver, "name1 name2 EMAIL@E-MAIL.com"),   // valid
//                new AddCommand(receiver, "name1 name2 EMAIL@E..MAIL.com"),  // invalid
//                new AddCommand(receiver, "name1 name2 EMAIL@E--MAIL.com"),  // invalid
//                new AddCommand(receiver, "name1 name2 EMAIL@E--MAIL.co."),  // invalid
                new ListCommand(receiver),
//                new UpdateCommand(receiver, "1 name lastname .abc@domain.com"),          // starts with dot
//                new UpdateCommand(receiver, "1 name lastname abc.@domain.com"),          // ends with dot
//                new UpdateCommand(receiver, "1 name lastname -abc@domain.com"),          // starts with dash
//                new UpdateCommand(receiver, "1 name lastname abc-@domain.com"),          // ends with dash
//                new UpdateCommand(receiver, "1 name lastname abc..def@domain.com"),      // consecutive dots
//                new UpdateCommand(receiver, "1 name lastname abc--def@domain.com"),      // consecutive dashes
//                new UpdateCommand(receiver, "1 name lastname abc.-def@domain.com"),      // mixed consecutive dot/dash
//                new UpdateCommand(receiver, "1 name lastname abc@domain.com"),           // control test
//                new UpdateCommand(receiver, "1 name lastname user@-abc.com"),            // starts with dash
//                new UpdateCommand(receiver, "1 name lastname user@abc-.com"),            // ends with dash
//                new UpdateCommand(receiver, "1 name lastname user@.abc.com"),            // starts with dot
//                new UpdateCommand(receiver, "1 name lastname user@abc..com"),            // consecutive dots
//                new UpdateCommand(receiver, "1 name lastname user@abc--def.com"),        // consecutive dashes
//                new UpdateCommand(receiver, "1 name lastname user@abc.c"),               // domain suffix too short
//                new UpdateCommand(receiver, "1 name lastname user@abc.comm"),            // domain suffix too long
//                new UpdateCommand(receiver, "1 name lastname user@abc.CoM"),             // uppercase letters in domain suffix
//                new UpdateCommand(receiver, "1 name lastname user@abc.123"),             // digits not allowed in suffix
//                new UpdateCommand(receiver, "1 name lastname user@abc.c_m"),             // underscore not allowed in suffix
//                new UpdateCommand(receiver, "1 name lastname user@abc.def-"),            // ends with dash before suffix
//
////
////                /* TEST FOR UPDATE COMMAND */
////// ✅ VALID TEST CASES
////                new UpdateCommand(receiver, "1 name"),
////                new UpdateCommand(receiver, "1 John Doe john.doe-email_2022@company-site.com"),
////                new UpdateCommand(receiver, "2 Jane Dae valid-email.23@abc-site.org"),
////                new UpdateCommand(receiver, "3 Alice Bob a_b.c-d@domain-name.co"),
////                new UpdateCommand(receiver, "4 Foo Bar user_name@doma1n.io"),
////                new ListCommand(receiver),
////
////// ❌ INVALID TEST CASES
////                new UpdateCommand(receiver, "John Doe john.doe@example.com"),           // Missing index
////                new UpdateCommand(receiver, "1"),                                         // Missing data
////                new UpdateCommand(receiver, "1 John Doe john..doe@example.com"),         // Consecutive dots in local part
////                new UpdateCommand(receiver, "1 John Doe john.doe-@example.com"),         // Dash at end of local part
////                new UpdateCommand(receiver, "1 John Doe .john.doe@example.com"),         // Dot at start of local part
////                new UpdateCommand(receiver, "1 John Doe john.doe@company--site.com"),    // Consecutive dashes in domain
////                new UpdateCommand(receiver, "1 John Doe john.doe@company-site.CoM"),    // Uppercase in domain suffix
////                new UpdateCommand(receiver, "1 John Doe john.doe@company-site.x"),      // Domain suffix too short
////                new UpdateCommand(receiver, "1 John Doe john.doe@company-site.abcd"),   // Domain suffix too long
////                new UpdateCommand(receiver, "1 John Doe john#doe@example.com"),         // Invalid character in local part
////                new UpdateCommand(receiver, "1 John Doe john.doe@"),                     // Missing domain
////                new UpdateCommand(receiver, "1 John Doe user_@domain.com_"),            // Invalid underscore at end of domain
//                new ListCommand(receiver),
//
//                /* TEST FOR DELETE COMMAND */
//// ✅ VALID TEST CASES
//                new DeleteCommand(receiver, " 1 "),      // ✅ Valid: typical index
//                new DeleteCommand(receiver, " 9"),       // ✅ Valid: larger index
//
//// ❌ INVALID TEST CASES
//                new DeleteCommand(receiver, "999"),      // ❌ Index out of bounds
//                new DeleteCommand(receiver, ""),         // ❌ Empty payload
//                new DeleteCommand(receiver, "1, 2"),     // ❌ Wrong payload
//                new DeleteCommand(receiver, "0"),      // ❌ Non-numeric input
//                new DeleteCommand(receiver, "1abc"),     // ❌ Alphanumeric string
//                new DeleteCommand(receiver, "-1"),       // ❌ Negative index
//                new DeleteCommand(receiver, "0"),        // ❌ Zero index (if 1-based indexing is assumed)
//                new DeleteCommand(receiver, " "),        // ❌ Space as input
//                new DeleteCommand(receiver, "1 2"),      // ❌ Extra unexpected arguments
//                new DeleteCommand(receiver, "3.14"),     // ❌ Decimal number
//                new DeleteCommand(receiver, "#1"),       // ❌ Symbol-prefixed input
//                new ListCommand(receiver),
//
//                /* TEST FOR UNDO COMMAND */
//                new UndoCommand(receiver, history),
//                new UndoCommand(receiver, history),
//                new UndoCommand(receiver, history),
//                new UndoCommand(receiver, history),
//                new UndoCommand(receiver, history),
//                new UndoCommand(receiver, history),
                new ListCommand(receiver),
        };

        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(allCommands);
        invoker.executeCommand(history);

        receiver.storeToFile();
    }
}
