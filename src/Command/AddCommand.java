package Command;
import Data.Receiver;


/**
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


    public String getFirst_name() {
        return first_name;
    }

    //Create payload object.
    public AddCommand(Receiver data, String first_name, String last_name, String emailAddress)
    {
        this.data = data;
        this.first_name = first_name;
        this.last_name = last_name;
        this.emailAddress = emailAddress;
    }

    @Override
    public void execute() {
        data.addEntry(this.first_name, this.last_name, this.emailAddress);
    }

}
