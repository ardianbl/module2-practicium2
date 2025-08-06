package command;

import Exceptions.CommandException;
import core.*;

/**
 * Concrete Commands implements the various kinds of commands. A concrete command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete command.
 */

public class UpdateCommand implements Command  {
    private String first_name;
    private String last_name;
    private String emailAddress;
    private String params;
    private int indexNumber;
    private String toBeUpdated;
    private Receiver receiver;


    public UpdateCommand(Receiver receiver, String params)
    {
        this.receiver = receiver;
        this.params = params;

    }


    @Override
    public void execute() {

        String data1="-",data2="-",data3="-";
        String[] result = params.replaceAll("\\s{2,}", " ").split(" ");

        if(result.length < 2) throw new CommandException("Invalid number of input for update command.");
        data1 =  result[1];
        if(result.length >= 3) data2 =  result[2];
        if(result.length >= 4)
        {
//            if(!Validator.isEmailValid(result[3])) throw new CommandException("Invalid email address.");
//            data3 =  result[3];
            if(result[3].contains("@"))
            {
                if(!Validator.isEmailValid(result[3])) throw new CommandException("Invalid input.");
                data3 = result[3];
            }
            else{
                if(!Validator.isDataWithUnderScore(result[3])) throw new CommandException("Invalid input.");
                data3 = result[3];
            }

        }

        try {
            indexNumber = Integer.parseInt(result[0]) == 1 ? 0 : Integer.parseInt(result[0])-1 ;
            first_name = Validator.setTitleCase(data1);
            last_name = Validator.setTitleCase(data2);
            emailAddress = data3;
            toBeUpdated = this.receiver.getEmployee(this.indexNumber);
        }catch(NumberFormatException e) {
            throw new CommandException("Index number must be an integer.");
        }catch (IndexOutOfBoundsException e) {
            throw new CommandException("Invalid index number for the update.");
        }

        //Can just set it into the correct format to be set.
        receiver.updateEntry(indexNumber,first_name, last_name, emailAddress);
    }

    @Override
    public void undo() {
        String [] updateEntry = toBeUpdated.split(" ");
        receiver.updateEntry(indexNumber,updateEntry[0],updateEntry[1],updateEntry[2]);
    }
}
