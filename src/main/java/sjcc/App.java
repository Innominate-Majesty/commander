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

public class App 
{

    private Stack<String> commandStack;
    private Stack<String> undoStack;
    private List<String> commandList;
    
    public App() {
        commandStack = new Stack<>();
        undoStack = new Stack<>();
        commandList = new ArrayList<>();
        

    }

    public void start() {
        Scanner userInput = new Scanner(System.in);
        String input = "";
        System.out.println("Hello, welcome to Venus' Company");
        while (!input.equals(q)) {
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
        
    }

    private void listCommands() {

    }

    private void undoCommand() {

    }

    private void redoCommand() {

    }

    private void quit() {

    }

    public static void main( String[] args )
    {
        
    }
}
