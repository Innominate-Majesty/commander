package sjcc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFile {

  // read a json file and return an array
  public static JSONArray readArray(String fileName) {
    JSONParser jsonParser = new JSONParser();

    try (FileReader reader = new FileReader(filePath)) {
      JSONArray commandJsonArray = (JSONArray) jsonParser.parse(reader);
      for (Object commandObject : commandJsonArray) {
        String command = (String) commandObject;
        commandList.add(command);
      }

      catch (FileNotFoundException e) {
          System.err.println("The file wa nsot found: " + filePath);
          e.printStackTrace();
      }
      catch (IOException e) {
          System.err.println("An I/O error occurred: " + e.getMessage());
          e.printStackTrace();
      }
      catch (ParseException e) {
          System.err.println("Parsing error: " + e.getMessage());
          e.printStackTrace();
      }

    }
}
}
