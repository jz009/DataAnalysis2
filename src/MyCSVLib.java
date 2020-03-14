import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MyCSVLib {
    public static ArrayList<HashMap<String, Object>> readcsv() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/NumericData.csv")); //Creates a buffered
        //reader to read in the file
        ArrayList list = new ArrayList();
        String line = reader.readLine(); //Reads first line of file
        String[] headers = line.split(","); //Splits headers into separate strings and stores in array
        String line1 = reader.readLine();
        while(line1 != null) {
            HashMap<String,Object> map = new HashMap<String,Object>(); //Create a map for each line of the file
            String[] nextLine = line1.split(","); //Split line into separate strings
            for (int i = 0; i < headers.length; i++) {
                    map.put(headers[i], nextLine[i]); //Add every other type as a String
            }
            line1 = reader.readLine(); //Read the next line of the file
            list.add(map);
        }
        reader.close();
        return list;
    }
}
