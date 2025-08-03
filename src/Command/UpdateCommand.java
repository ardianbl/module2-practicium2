package Command;

import Data.Receiver;

/**
 * Concrete Commands implements the various kinds of commands. A concrete command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete command.
 */

public class UpdateCommand implements Command {
    private final String first_name;
    private final String last_name;
    private final String emailAddress;
    private final String indexNumber;
    private Receiver data;


    //Create payload object.
    //Might not need to have so many. Because i can take in the parama and then
    public UpdateCommand(Receiver data, String indexNumber, String first_name)
    {
        this.data = data;
        this.indexNumber = indexNumber;
        this.first_name = first_name;
        this.last_name = "-";
        this.emailAddress = "-";
    }

    public UpdateCommand(Receiver data, String indexNumber, String first_name, String last_name)
    {
        this.data = data;
        this.indexNumber = indexNumber;
        this.first_name = first_name;
        this.last_name = last_name;
        this.emailAddress = "-";
    }

    public UpdateCommand(Receiver data, String indexNumber, String first_name, String last_name, String emailAddress)
    {
        this.data = data;
        this.indexNumber = indexNumber;
        this.first_name = first_name;
        this.last_name = last_name;
        this.emailAddress = emailAddress;
    }

    @Override
    public void execute() {
        data.updateEntry(this.indexNumber,this.first_name, this.last_name, this.emailAddress);
    }
}
