package command;

import Exceptions.CommandException;
import core.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**gt
 * Concrete Commands implements the various kinds of commands. A concrete command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete command.
 */

public class AddCommand implements Command {
    //Change input to params.
    private final String first_name;
    private final String last_name;
    private final String emailAddress;
    private Receiver data;

    boolean checkData(String emailToCheck){
        Pattern pattern = Pattern.compile("(^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,3}$|\\w)");
        Matcher matcher = pattern.matcher(emailToCheck);

        return matcher.find();

    }

    String setTitleCase (String wordToSet)
    {
        for (int i = 0; i < wordToSet.length(); i++) {
            String firstLetter = wordToSet.substring(0, 1).toUpperCase();
            String otherLetter = wordToSet.substring(1).toLowerCase();
            wordToSet = firstLetter + otherLetter;
        }

        return wordToSet;

    }


    //Create payload object.
    //Check validate here.
    public AddCommand(Receiver data,String params)
    {

        String[] result = params.replaceAll("\\s{2,}", " ").split(" ");
        if(result.length != 3) throw new CommandException("Invalid number of input for add command.");

        this.first_name = setTitleCase(result[0]);
        this.last_name = setTitleCase(result[1]);
        if(!checkData(result[2])) throw new CommandException("Invalid input.");
        this.emailAddress = result[2].contains("@") ? result[2]:setTitleCase(result[2]);
        this.data = data;
    }

    @Override
    public void execute() {
        data.addEntry(this.first_name, this.last_name, this.emailAddress);
    }

}
