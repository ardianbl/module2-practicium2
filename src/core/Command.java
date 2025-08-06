package core;

/**
 * Command interface with execute and undo method
 */

public interface Command {
    default void execute() {}
    default void undo() {}
}
