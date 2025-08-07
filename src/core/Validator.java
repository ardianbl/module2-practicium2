package core;

import java.util.regex.Pattern;

/**
 * Performs all necessary validation checks related to the {@code Receiver} class's operations.
 *
 */
public class Validator {

    /**
     * Reciever instance f
     */
    private static Receiver receiver;

    /**
     * Pattern
     */

    // Regex for ID-like string (letters, numbers, underscore only)
    private static final Pattern ID_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{1,}+$");

    // Regex for email (custom rules from assignment)
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_]+(?:[.-][a-zA-Z0-9_]+)*@[a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*\\.[a-z]{2,3}$");

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


    public static String capitalize (String wordToSet)
    {
        for (int i = 0; i < wordToSet.length(); i++) {
            String firstLetter = wordToSet.substring(0, 1).toUpperCase();
            String otherLetter = wordToSet.substring(1).toLowerCase();
            wordToSet = firstLetter + otherLetter;
        }

        return wordToSet;

    }

    public static boolean isIndexValid(Receiver receiver, String indexString) {
        try {
            int indexNumber = Integer.parseInt(indexString) - 1;
            if (indexNumber < 0 || indexNumber >= receiver.getEmployeeCount()) {
                throw new Exceptions.CommandException("Index out of bounds.");
            }
        } catch (NumberFormatException e) {
            throw new Exceptions.CommandException("Index number must be an integer.");
        }

        return true;
    }
}
