package command;

import Exceptions.CommandException;
import core.*;


/**gt
 * Concrete Commands implements the various kinds of commands. A concrete command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete command.
 */

public class AddCommand implements Command {
    //Change input to params.
    private String params;
    private Receiver data;
    private String first_name;
    private String last_name;
    private String email;
    private int indexToDelete;



    //Create payload object.
    //Check validate here.
    public AddCommand(Receiver data,String params)
    {

      this.data = data;
      this.params = params;
    }

    @Override
    public void execute() {

        String[] result = params.replaceAll("\\s{2,}", " ").split(" ");
        if(result.length != 3) throw new CommandException("Invalid number of input for add command.");

        first_name = Validator.setTitleCase(result[0]);
        last_name = Validator.setTitleCase(result[1]);
        if(result[2].contains("@"))
        {
            if(!Validator.isEmailValid(result[2])) throw new CommandException("Invalid input.");
            email = result[2];
        }
        else{
            if(!Validator.isDataWithUnderScore(result[2])) throw new CommandException("Invalid input.");
            email = result[2];
        }

        data.addEntry(first_name, last_name, email);
        indexToDelete = data.getEmployeeCount()-1;
        System.out.println("add");
    }

    @Override
    public void undo(){
        data.delete(indexToDelete);
    }

    @Override
    public boolean isStackItem() {
        return true;
    }


}
