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
//import javax.json.JSON;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.*;

import java.io.FileInputStream;
import java.io.InputStream;

public class App 
{

    private Stack<String> commandStack;
    private Stack<String> undoStack;
    private List<String> commandList;
    
    public App() {
        commandStack = new Stack<>();
        undoStack = new Stack<>();
        commandList = new ArrayList<>();
        loadCommands("/Users/queen/2023MavenProject/commander/src/main/java/commands.json");

    }

    private void loadCommands(String jsonFilePath) {
        try (InputStream fis = new FileInputStream(jsonFilePath);
            JsonReader reader = Json.createReader(fis)) {
            JsonObject obj = reader.readObject();
            JsonArray commandsJsonArray = obj.getJsonArray("commands");
            for (int i = 0; i < commandsJsonArray.size(); i++) {
                commandList.add(commandsJsonArray.getString(i));
            }
        }
        catch (Exception e) {
            System.err.println("Error loading ..... " + e.getMessage());
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
