package command;

import Exceptions.CommandException;
import core.*;

import java.util.Stack;

public class UndoCommand implements Command  {
    private Stack<Command> history;

    public UndoCommand(Stack<Command> history ) {
        this.history = history;
    }

    @Override
    public void execute() {
        if(!history.isEmpty())
        {
            Command lastCommand = history.pop();
            lastCommand.undo();

            //Not a good way
//            Command lastCommand = history.pop();
//            int currentSize = receiver.getEmployeeList().size();
//            for(Command item:history)
//            {
//                item.execute();
//            }
//            for(int i = 0; i < currentSize; i++)
//            {
//                receiver.delete(i+1);
//            }

        }

    }


}