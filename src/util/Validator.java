package util;

import core.Receiver;
import exceptions.CommandException;

import java.util.regex.Pattern;

public class Validator {
    private static Receiver receiver;

    // Regex for ID-like string (letters, numbers, underscore only)
    private static final Pattern ID_PATTERN = Pattern.compile("^[A-Za-z0-9_]+$");

    // Regex for email (custom rules from assignment)
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-z0-9_]+([.-](?![.-])[a-z0-9_]+)*@" +     // local part
                    "[a-z0-9]+([.-](?![.-])[a-z0-9]+)*\\." +     // domain
                    "[a-z]{2,3}$",                               // .com / .sg / etc.
            Pattern.CASE_INSENSITIVE);

    // Validate <data3>
    public static boolean isValidData3(String input) {
        return isValidEmail(input) || isValidId(input);
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidId(String id) {
        return ID_PATTERN.matcher(id).matches();
    }


//    public static String[] checkAddPayloadValidity(String payload) throws CustomException {
//
//        if (payload == null || payload.trim().isEmpty()) {
//            throw new CustomException("No input is detected");
//        }
//
//        String[] payloadArray = payload.trim().split("\\s+");
//        if (payloadArray.length != 3) {
//            throw new CustomException("Add command requires exactly 3 inputs");
//        }
//        String firstName = payloadArray[0];
//        String lastName = payloadArray[1];
//        String email = payloadArray[2];
//
//        if (!isValidData3(email)) {
//            throw new CustomException("Email format is invalid.");
//        }
//
//        return new String[]{firstName, lastName, email};
//    }
//
//    public static String[] checkUpdatePayloadValidity(String payload) throws CustomException {
//        String index, firstName, lastName, email;
//        if (payload == null || payload.trim().isEmpty()) {
//            throw new CustomException("No input is detected");
//        }
//
//        String[] payloadArray = payload.trim().split("\\s+");
//        if (payloadArray.length < 2) {
//            throw new CustomException("Update payload requires at least 2 input.");
//        }
//        if (payloadArray.length > 4) {
//            throw new CustomException("Too many input is detected.");
//        }
//        if (payloadArray.length == 2) {
//            index = payloadArray[0];
//            firstName = payloadArray[1];
//        }
//
//        lastName = payloadArray[2];
//        email = payloadArray[3];
//
//        if (!isIndexValid(Integer.parseInt(index))) {
//            throw new CustomException("Index is not in the list");
//        }
//
//        if (!isValidData3(email)) {
//            throw new CustomException("Email format is invalid.");
//        }
//
//        return new String[]{index, firstName, lastName, email};
//    }


    public static boolean isIndexValid(int index) {
        return index > 0 && index <= receiver.getEmployeeCount();
    }

    public static int checkDeletePayloadValidity(String payload) {
        if (payload == null || payload.trim().isEmpty()) {
            throw new CommandException("No input is detected");
        }

        String[] index = payload.trim().split("\\s+");
        if (index.length > 1) {
            throw new CommandException("Delete command requires only 1 input.");
        }

        return Integer.parseInt(index[0]);
    }
}
