package sjcc;

/**
 * Venus H. 
 * For CIS 54 - Data Structure
 *
 */

import java.util.Scanner;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

public class App 
{

    private Stack<String> commandStack;
    private Stack<String> undoStack;
    private List<String> commandList;
    
    public App() {
        commandStack = new Stack<>();
        undoStack = new Stack<>();
        commandList = new ArrayList<>();
        loadCommands("/Users/queen/2023MavenProject/commander/src/main/java/resources/commands.json");
        System.out.println("Commands...: " + commandList.size());

    }

    private void loadCommands(String jsonFilePath) {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("commands.json");
        if (inputStream == null) {
            System.err.println("Could not load the commands, the file was not found!");
            return;
        }

        JSONParser jsonParser = new JSONParser();

        try {
            JSONArray commandJsonArray = (JSONArray) jsonParser.parse(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            System.out.println("JSON Array Read: ... " + commandJsonArray.size() + " commands");
            for (Object commandObject : commandJsonArray) {
                String command = (String) commandObject;
                commandList.add(command);
            }
            System.out.println("Commands loaded: .... " + commandList.size() + " commands");

        }

        catch (IOException | ParseException e) {
            System.err.println("Error loading commands: .... " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void start() {
        Scanner userInput = new Scanner(System.in);
        String input = "";
        System.out.println("Hello, welcome to Venus' Company");
        while (!input.equals("q")) {
            System.out.println("\n Menu");
            System.out.println("[I]ssue a random command");
            System.out.println("[L]ist all of the commands");
            System.out.println("[U]undo the last command");
            System.out.println("[R]edo the last command");
            System.out.println("[Q]uit");
            System.out.println("[Enter a command: ");
            input = userInput.nextLine().toLowerCase();

            switch (input) {
                case "i":
                    issueCommand();
                    break;
                case "l":
                    listCommands();
                    break;
                case "u":
                    undoCommand();
                    break;
                case "r":
                    redoCommand();
                    break;
                case "q":
                    quit();
                    break;
                default:
                    System.out.println("Please enter a valid command");
                    break;
            }
        }
        userInput.close();
        System.out.println("Goodbye!");
    }

    private void issueCommand() {
        //issuing random commands

        if (commandList.isEmpty()) {
            System.out.println("There are no commands available to issue");
            return;
        }
        Random rand = new Random();
        String command = commandList.get(new Random().nextInt(commandList.size()));
        commandStack.push(command);
        System.out.println("Issued command: " + command);
        undoStack.clear();
    }

    private void listCommands() {
        //listing all the commands

        if (commandList.isEmpty()) {
            System.out.println("The list is empty");
            return;
        }

        System.out.println("Here is the list of comamnds: ");
        for (String command : commandList) {
            System.out.println(command);
        }

    }

    private void undoCommand() {
        //undoing the last command

        if (commandStack.isEmpty()) {
            System.out.println("There are no commands to undo");
            return;
        }

        String command = commandStack.pop();
        undoStack.push(command);
        System.out.println("Undoing >>>> " + command);
    }

    private void redoCommand() {
        //redoing the last command

        if (undoStack.isEmpty()) {
            System.out.println("There are no commands to redo");
            return;
        }

        String command = undoStack.pop();
        commandStack.push(command);
        System.out.println("Redoing >>>> " + command);
    }

    private void quit() {

    }

    public static void main( String[] args )
    {
        App commanderApp = new App();
        commanderApp.start();
    }
}
