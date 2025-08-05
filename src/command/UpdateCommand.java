package command;

import Exceptions.CommandException;
import core.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Concrete Commands implements the various kinds of commands. A concrete command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete command.
 */

public class UpdateCommand implements Command  {
    private final String first_name;
    private final String last_name;
    private final String emailAddress;
    private final int indexNumber;
    private Receiver data;


    boolean checkData(String emailToCheck){
        Pattern pattern = Pattern.compile("^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$");
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
    //Might not need to have so many. Because I can take in the params and then
    public UpdateCommand(Receiver data, String params) throws CommandException
    {

        String data1="-",data2="-",data3="-";
        String[] result = params.replaceAll("\\s{2,}", " ").split(" ");

        if(result.length < 2) throw new CommandException("Invalid number of input for update command.");
        data1 =  result[1];
        if(result.length >= 3) data2 =  result[2];
        if(result.length >= 4)
        {
            if(!checkData(result[3])) throw new CommandException("Invalid email address.");
            data3 =  result[3];
        }

        try {
            this.indexNumber = Integer.parseInt(result[0])-1;
            this.first_name = setTitleCase(data1);
            this.last_name = setTitleCase(data2);
            this.emailAddress = data3;
            this.data = data;
        }catch(NumberFormatException e) {
            throw new CommandException("Index number must be an integer.");
        }catch (IndexOutOfBoundsException e) {
            throw new CommandException("Invalid index number for the update.");
        }


    }


    @Override
    public void execute() {
        //Can just set it into the correct format to be set.
        data.updateEntry(this.indexNumber,this.first_name, this.last_name, this.emailAddress);
    }
}
