package command;

import Exceptions.CommandException;
import core.*;

import java.util.Stack;

public class UndoCommand implements Command  {
    private Receiver receiver;
    private Stack<Command> history;

    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        this.history = history;
    }

    @Override
    public void execute() {
        if(!history.isEmpty())
        {
            Command lastCommand = history.pop();
            lastCommand.undo();
            System.out.println("Undo");
        }

    }


}
