import core.Receiver;
import util.FileHandler;

/**
 * The Client creates and configures the concrete command objects. The client must pass all
 * the command parameters, including a receiver instance, into the command’s constructor.
 * After that, the resulting command may be associated with one or multiple senders.
 */

public class Client {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
    }
}