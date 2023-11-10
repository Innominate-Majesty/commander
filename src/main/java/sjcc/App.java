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
// import org.json.simple.JSONArray;
// import org.json.simple.parser.JSONParser;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.util.*;

public class App 
{

    private Stack<String> commandStack;
    private Stack<String> undoStack;
    private List<String> commandList;
    private Random rand;
    
    public App() {
        commandStack = new Stack<>();
        undoStack = new Stack<>();
        commandList = new ArrayList<>();
        rand = new Random();
        loadCommands("commands.json");

    }

    private void loadCommands(String jsonFilePath) {


        String absolutePath = "/Users/queen/2023MavenProject/commander/src/main/java/resources/commands.json";
        List<String> commands = JSONFile.readCommands(absolutePath);
        if (commands != null) {
            commandList.addAll(commands);
        }
    }

    public void start() {
        Scanner userInput = new Scanner(System.in);
        String input = "";
        while (!input.equals("q")) {

            System.out.println("  ");
            System.out.println("****************************************\n");
            System.out.println("         Commanding with Venus   \n");
            System.out.println("****************************************\n");
            System.out.println("i    :    [I]ssue random commands");
            System.out.println("l    :    [L]ist all available commands");
            System.out.println("u    :    [U]ndo previous command");
            System.out.println("r    :    [R]edo previous command");
            System.out.println("q    :    [Q]uit\n");
            System.out.println("****************************************\n");
            System.out.println("Enter your command: ");

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
        System.out.println("Goodbye, thanks for traing!");
    }

    private void issueCommand() {
        //issuing random commands

        if (commandList.isEmpty()) {
            System.out.println("There are no commands available to issue");
            return;
        }
        System.out.println("Follow these 5 commands: \n");
        for(int i = 0; i < 5; i++) {
            String command = commandList.get(rand.nextInt(commandList.size()));
            commandStack.push(command);
            System.out.println(command);
        }
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
