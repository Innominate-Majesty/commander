package sjcc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.*;
import org.json.simple.parser.*;

public class JSONFile {

  // read a json file and return an array
  public static List<String> readCommands(String fileName) {
    JSONParser jsonParser = new JSONParser();
    List<String> commands = new ArrayList<>();

    try (FileReader reader = new FileReader(fileName)) {
      Object obj = jsonParser.parse(reader);
      JSONArray commandArray = (JSONArray) obj;

      for (Object commandObject : commandArray) {
        String command = (String) commandObject;
        commands.add(command);
      }
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (ParseException e) {
      e.printStackTrace();
    }

    return commands;
  }
}

