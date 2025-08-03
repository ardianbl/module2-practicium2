import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class TestMethod {

    private static ArrayList<String> data;

    public static void main(String[]args)
    {
        String test = "aa aa aaa   aaa";
        String newS = test.replaceAll("\\s{2,}", " ");
        System.out.println(newS);
//        Path filepath = Paths.get("./src/dataStore1.txt");
//
//        if(Files.exists(filepath)) {
//            try (BufferedReader reader = Files.newBufferedReader(filepath)) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    data.add(line);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Done");
//
//
//
//        }else{
//            System.out.println("Cannot locate file.");
//        }
//
//        System.out.println(data);

    }

}
