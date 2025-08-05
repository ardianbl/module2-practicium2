package command;

/**
 * Command interface with skeleton execute method.
 */

public interface Command {
    /**
     * Execute method to be implemented by concrete Command class
     */
    default void execute() {}
}
