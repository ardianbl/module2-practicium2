package command;

import java.util.ArrayList;

/**
 * The Command interface usually declares just a single method for executing the command.
 */

public interface Command {
    default void execute() {}
}
