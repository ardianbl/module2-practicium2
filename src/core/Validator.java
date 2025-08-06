package core;

import Exceptions.CommandException;

import java.util.ArrayList;

public class Validator {
//    private static Receiver receiver;

    public static String[] checkAddPayloadValidity(String payload) {

        if (payload == null || payload.trim().isEmpty()) {
            throw new CommandException("No input is detected");
        }

        String[] payloadArray = payload.trim().split("\\s+");
        if (payloadArray.length != 3) {
            throw new CommandException("Add command requires exactly 3 inputs");
        }
        String firstName = payloadArray[0];
        String lastName = payloadArray[1];
        String email = payloadArray[2];

        if (!isEmailValid(email)) {
            throw new CommandException("Email format is invalid.");
        }

        return new String[]{firstName, lastName, email};
    }

    public static String[] checkUpdatePayloadValidity(String payload) {
        if (payload == null || payload.trim().isEmpty()) {
            throw new CommandException("No input is detected");
        }

        String[] payloadArray = payload.trim().split("\\s+");
        if (payloadArray.length != 4) {
            throw new CommandException("Add command requires exactly 3 inputs");
        }
        String index = payloadArray[0];
        String firstName = payloadArray[1];
        String lastName = payloadArray[2];
        String email = payloadArray[3];

//        if (!isIndexValid(Integer.parseInt(index))) {
//            throw new CommandException("Index is not in the list");
//        }

//        if (!isEmailValid(email)) {
//            throw new CommandException("Email format is invalid.");
//        }

        return new String[]{index, firstName, lastName, email};
    }

    public static boolean isEmailValid(String email) {

//      String regex = "(?i)^[a-z0-9_]+(?:[.-][a-z0-9_]+)*@" +  // local
//                "[a-z0-9]+(?:[.-][a-z0-9]+)*\\.[a-z]{2,3}$";    // domain

        String regex ="^(?!.*(\\.\\.|__|--))[a-zA-Z0-9]+" +
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

    public static String setTitleCase (String wordToSet)
    {
        for (int i = 0; i < wordToSet.length(); i++) {
            String firstLetter = wordToSet.substring(0, 1).toUpperCase();
            String otherLetter = wordToSet.substring(1).toLowerCase();
            wordToSet = firstLetter + otherLetter;
        }

        return wordToSet;

    }

//    public static boolean isIndexValid(int index) {
//        return index > 0 && index <= receiver.getEmployeeCount();
//    }
}
