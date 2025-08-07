package Exceptions;

/**
 * Handles Exceptions of type {@code CommandException} that may occur during command execution.
 */
public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super(message);
    }

}

