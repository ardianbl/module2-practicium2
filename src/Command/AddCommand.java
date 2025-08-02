package Command;

import javax.sound.midi.Receiver;

/**
 * Concrete Commands implements the various kinds of commands. A concrete command
 * isn’t supposed to perform the work on its own, but rather to pass the call to one of the
 * business logic objects. Parameters required to execute a method on a receiving object can be
 * declared as fields in the concrete command.
 */

public class AddCommand implements Command {

    private final String first_name;
    private final String last_name;
    private final String emailAddress;
    protected Receiver receiver;


    //Create payload object.
    public AddCommand(Receiver receiver, String first_name,String last_name,String emailAddress)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.emailAddress = emailAddress;
    }

    @Override
    public void execute() {
//        receiver.addEntry(String first_name,String last_name,String emailAddress);
    }

}
