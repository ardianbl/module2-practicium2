package Command;

/**
 * Command interface to be implemented by concrete Command classes.
 */

public interface Command {
    /**
     * Skeleton Execute method
     */
    default void execute() {
    }

    /**
     * Skeleton Undo method
     */
    default void undo() {
    }

    /**
     * Skeleton isStackItem method
     *
     * @return false by default
     */
    default boolean isStackItem() {
        return false;
    }

    ;

}
