import java.util.HashMap;
import java.util.Map;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords {
    // a constant array that holds all valid command words

    private HashMap<String, CommandWord> validCommands;

    // private static final String[] validCommands = {
    // "go", "quit", "help", "look", "back", "take", "drop", "items"
    // };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            } else {
                validCommands.put(null, CommandWord.UNKNOWN);
            }
        }

    }

    /**
     * Check whether a given String is a valid command word.
     * 
     * @return true if a given string is a valid command,
     *         false if it isn't.
     */
    public boolean isCommand(String aString) {
        for (Map.Entry entry : validCommands.entrySet()) {
            if (validCommands.containsKey(aString)) {
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public String getCommandList() {
        String command_list = "";
        for (Map.Entry<String, CommandWord> entry : validCommands.entrySet()) {
            if (entry.getValue() != CommandWord.UNKNOWN) {
                command_list += entry.getValue() + " ";
            }
        }
        return command_list;
    }

    public CommandWord getTranslate(String command) {
        if (validCommands.containsKey(command)) {
            return validCommands.get(command);
        }
        return CommandWord.UNKNOWN;
    }

}