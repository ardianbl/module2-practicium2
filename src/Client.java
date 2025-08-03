import Command.AddCommand;
import Command.UpdateCommand;
import Command.Command;
import Data.Receiver;
import exceptions.validationException;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Client creates and configures the concrete command objects. The client must pass all
 * the command parameters, including a receiver instance, into the command’s constructor.
 * After that, the resulting command may be associated with one or multiple senders.
 */

public class Client {

    static void setTitleCase(String[] inputInArray,String command)
    {
        //For update as email is not always in the input.
        int length = inputInArray.length ;
        int startIndex = 1;

        switch (command)
        {
            case "add":
                //Set to -1 to skip emailAddress.
                length = inputInArray.length - 1;
                break;
            case "update":
                //Set to start from 2 as the second input is the index.
                startIndex = 2;
                if (inputInArray.length == 5) length = inputInArray.length - 1;
                break;
            default:
                break;
        }


        for(int i = startIndex; i < length; i++)
        {
            String firstLetter = inputInArray[i].substring(0,1).toUpperCase();
            String otherLetter = inputInArray[i].substring(1).toLowerCase();
            inputInArray[i] = firstLetter+otherLetter;
        }

        //To test print to see what is updates.
        //System.out.println("Result for setTitleCase: " + Arrays.toString(inputInArray));

    }

    static String[] cleanInput(String userInput)
    {
        //QUESTION: Do we need to check if the # is before the 1?
        String[] result = userInput.replaceAll("#"," ").replaceAll("\\s{2,}", " ").split(" ");
        result[0] = result[0].toLowerCase();

        //To print out the cleaned data.
        //System.out.println("Result of cleanInput: " + Arrays.toString(result));

        return result;

    };

    //Check if data exist for update and delete.
    //Can consider to simplify this.
//    static void checkEntry(Receiver data, int index)
//    {
//        try {
//            String checkData = data.getData().get(index-1);
//        }
//        catch (IndexOutOfBoundsException e)
//        {
//            throw new validationException("Invalid index number, entry does not exist.");
//        }
//
//    }

    static boolean checkEmail(String emailToCheck){
        Pattern pattern = Pattern.compile("^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$");
        Matcher matcher = pattern.matcher(emailToCheck);

        return matcher.find();

    }

    public static void main(String[] args) throws validationException {


        Stack<Command> history = new Stack<>();
        Receiver data = new Receiver();

        boolean isExist = !data.getData().isEmpty();
        boolean isEmail = false;



        //This will send to the invoker
        //Receiver will then be triggered to execute.
        do {
            Scanner inputScanner = new Scanner(System.in);
            String input = inputScanner.nextLine();

            //Clean the user input,remove additional spacing, make sure command is always lowercase.
            String[] inputArray = cleanInput(input);

            String command = inputArray[0].toLowerCase();

            //Check number of inputs.
            if (inputArray.length < 2 || inputArray.length > 5 ) {
                throw new validationException("You have enter the wrong amount of input");
            }


            Invoker control = new Invoker();

            switch (command)
            {
                case "add":
                    if(inputArray.length != 4) throw new validationException("Invalid number of input for add command.");
                    //Check the emails.
                    if(!checkEmail(inputArray[3])) throw new validationException("This is an invalid Email address");
                    setTitleCase(inputArray,command);
                    Command addCommand = new AddCommand(data, inputArray[1], inputArray[2], inputArray[3]);
                    control.setCommandsForExecution(new Command[]{addCommand});
                    control.executeCommand(history);
                    isExist = true; // Update the boolean.
                    break;

                case "update":
                    Command updateCommand = null;
                    if(!isExist) throw new validationException("Invalid command: There is no data to update.");
                    if(inputArray.length < 3) throw new validationException("Invalid number of input for update command.");

                    // Check if the index exist in the list.
                    // checkEntry(data,Integer.parseInt(inputArray[1]));
                    if(data.getData().size() < Integer.parseInt(inputArray[1]))
                        throw new validationException("Invalid number of input for update command.");

                    //Set all data into titleCase.
                    setTitleCase(inputArray,command);

                    if(inputArray.length == 3) updateCommand = new UpdateCommand(data, inputArray[1], inputArray[2]);
                    if(inputArray.length == 4) updateCommand = new UpdateCommand(data, inputArray[1], inputArray[2], inputArray[3]);

                    //Check email here:
                    if(!checkEmail(inputArray[4])) throw new validationException("Invalid email address.");

                    if(inputArray.length == 5) updateCommand = new UpdateCommand(data, inputArray[1], inputArray[2], inputArray[3], inputArray[4]);
                    
                    control.setCommandsForExecution(new Command[]{updateCommand});
                    control.executeCommand(history);
                    break;

                default:
                    throw new validationException ("Invalid command.");

            }

            //Print out to test the function if it works. This show the full dataStore.
            System.out.println(data.getData().toString());


        } while (true);


    }

}