package core;

import java.util.regex.Pattern;

public class Validator {
    private static Receiver receiver;

    // Regex for ID-like string (letters, numbers, underscore only)
    private static final Pattern ID_PATTERN = Pattern.compile("^[A-Za-z0-9_]+$");

    // Regex for email (custom rules from assignment)
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^(?!.*(\\.\\.|__|--))[a-zA-Z0-9]+" +
                    "[\\w.-]+[a-zA-Z0-9]+" +
                    "@[a-zA-Z0-9]+[\\w.-]+" +
                    "[a-zA-Z0-9]+" +
                    "\\.[a-z]{2,3}$",
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

    public static boolean isEmailValid(String email) {

//      String regex = "(?i)^[a-z0-9_]+(?:[.-][a-z0-9_]+)*@" +  // local
//                "[a-z0-9]+(?:[.-][a-z0-9]+)*\\.[a-z]{2,3}$";    // domain

        String regex = "^(?!.*(\\.\\.|__|--))[a-zA-Z0-9]+" +
                "[\\w.-]+[a-zA-Z0-9]+" +
                "@[a-zA-Z0-9]+[\\w.-]+" +
                "[a-zA-Z0-9]+" +
                "\\.[a-z]{2,3}$";

        return email.matches(regex);
    }

    public static boolean isDataWithUnderScore(String email) {

        String regex = "^[a-zA-Z0-9_]{1,}+$";
        return email.matches(regex);
    }

    public static String capitalize(String wordToCapitalize) {
        return wordToCapitalize.replaceFirst(
                wordToCapitalize.substring(0, 1),
                wordToCapitalize.substring(0, 1).toUpperCase()
        );

//        for (int i = 0; i < wordToCapitalize.length(); i++) {
//            String firstLetter = wordToCapitalize.substring(0, 1).toUpperCase();
//            String otherLetter = wordToCapitalize.substring(1).toLowerCase();
//            wordToCapitalize = firstLetter + otherLetter;
//        }
//
//        return wordToCapitalize;
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
