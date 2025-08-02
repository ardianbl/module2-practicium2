package Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The Receiver class contains some business logic. Almost any object may act as a receiver.
 * Most commands only handle the details of how a request is passed to the receiver, while the
 * receiver itself does the actual work.
 */

public class DataStore {
//Check if dataStore exist in the folder. Get data and put into an array.
// if add and dataStore did not exist create A NEW file, add data and save.
//Execute the command.

    private ArrayList<String> data = new ArrayList<>();
    private boolean isExist = false;


    public DataStore() {
        Path filepath = Paths.get("./src/dataStore.txt");
        isExist = Files.exists(filepath);

        if(isExist) {
            try (BufferedReader reader = Files.newBufferedReader(filepath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    data.add(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done");

        }else{
            this.data = new ArrayList<>();
            System.out.println("Cannot locate file.");
        }
        
        System.out.println(this.data);


    };

    public void addEntry() {
        System.out.println("Yup u want to add entry");
    }

}
