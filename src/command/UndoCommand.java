package command;

import core.*;

import java.util.Stack;

public class UndoCommand implements Command {
    private Stack<Command> history;

    public UndoCommand(Stack<Command> history) {
        this.history = history;
    }

    @Override
    public void execute() {
        if (!history.empty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
            System.out.println("Undo");
        } else {
            System.out.println("Command history is empty. No command to undo.");
        }
    }
}