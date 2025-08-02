
import Command.AddCommand;
import Command.Command;
import Command.DataStore;

import java.util.Scanner;
import java.util.Stack;

/**
 * The Client creates and configures the concrete command objects. The client must pass all
 * the command parameters, including a receiver instance, into the command’s constructor.
 * After that, the resulting command may be associated with one or multiple senders.
 */

public class Client {


    public static void main(String[] args) throws NoSuchFieldException {

        Stack<Command> history = new Stack<>();
        //This will send to the invoker
        //Reciever will then be triggered to execute.

        int counter = 0;
        do{
            Scanner inputScanner = new Scanner(System.in);
            String input = inputScanner.nextLine();
            String[] inputArray = input.split(" ");


            String command = inputArray[0].toLowerCase();

            //Need to do checking on the variable?

            if(inputArray[0].equals("add"))
            {
                DataStore data = new DataStore();
                Invoker control = new Invoker();
                Command addCommand = new AddCommand(data,inputArray[1],inputArray[2],inputArray[3]);
                control.setCommandsForExecution(new Command[]{addCommand});
                history.push(addCommand);
                control.executeCommand(history);
            }

            counter++;

        }while(counter > 0);


    }

}