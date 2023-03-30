/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game. Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game. It also evaluates and
 * executes the commands that the parser returns.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game {
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, hall, salon, gameRoom, office, bedRoom, bathRoom, kitchen, diningRoom, grenier;

        // create the rooms
        outside = new Room("dehors devant la maison");
        hall = new Room("dans le hall");
        salon = new Room("dans le salon");
        gameRoom = new Room("dans la salle de jeu");
        office = new Room("dans le bureau");
        bedRoom = new Room("dans la chambre");
        bathRoom = new Room("dans la salle de bain");
        kitchen = new Room("dans la cuisine");
        diningRoom = new Room("dans la salle à manger");
        grenier = new Room("dans le grenier");

        // initialise room exits
        outside.setExits(bedRoom, kitchen, hall, gameRoom, null, null);
        hall.setExits(outside, salon, null, diningRoom, grenier, null);
        salon.setExits(null, null, gameRoom, hall, null, null);
        gameRoom.setExits(salon, outside, office, null, null, null);
        office.setExits(gameRoom, null, null, bedRoom, null, null);
        bedRoom.setExits(null, office, outside, bathRoom, null, null);
        bathRoom.setExits(null, bedRoom, null, null, null, null);
        kitchen.setExits(diningRoom, null, null, outside, null, null);
        diningRoom.setExits(null, hall, diningRoom, null, null, null);
        grenier.setExits(null, null, null, null, null, hall);

        currentRoom = outside; // start game outside
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("Tu es " + currentRoom.getDescription());
        System.out.print("Sorties: \n");
        if (currentRoom.northExit != null) {
            System.out.print("Devant, " + currentRoom.northExit.getDescription() + " (1) \n");
        }
        if (currentRoom.eastExit != null) {
            System.out.print("A droite, " + currentRoom.eastExit.getDescription() + " (2) \n");
        }
        if (currentRoom.southExit != null) {
            System.out.print("Derrière, " + currentRoom.southExit.getDescription() + " (3) \n");
        }
        if (currentRoom.westExit != null) {
            System.out.print("A gauche, " + currentRoom.westExit.getDescription() + " (4) \n");
        }
        if (currentRoom.upExit != null) {
            System.out.print("En haut, " + currentRoom.upExit.getDescription() + " (5) \n");
        }
        if (currentRoom.downExit != null) {
            System.out.print("En bas, " + currentRoom.downExit.getDescription() + " (6) \n");
        }
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if (direction.equals("1")) {
            nextRoom = currentRoom.northExit;
        }
        if (direction.equals("2")) {
            nextRoom = currentRoom.eastExit;
        }
        if (direction.equals("3")) {
            nextRoom = currentRoom.southExit;
        }
        if (direction.equals("4")) {
            nextRoom = currentRoom.westExit;
        }
        if (direction.equals("5")) {
            nextRoom = currentRoom.upExit;
        }
        if (direction.equals("6")) {
            nextRoom = currentRoom.downExit;
        }

        if (nextRoom == null) {
            System.out.println("Il n'y a pas de porte!");
        } else {
            currentRoom = nextRoom;
            System.out.println("Tu es " + currentRoom.getDescription());
            System.out.print("Sorties: \n");
            if (currentRoom.northExit != null) {
                System.out.print("Devant, " + currentRoom.northExit.getDescription() + " (1) \n");
            }
            if (currentRoom.eastExit != null) {
                System.out.print("A droite, " + currentRoom.eastExit.getDescription() + " (2) \n");
            }
            if (currentRoom.southExit != null) {
                System.out.print("Derrière, " + currentRoom.southExit.getDescription() + " (3) \n");
            }
            if (currentRoom.westExit != null) {
                System.out.print("A gauche, " + currentRoom.westExit.getDescription() + " (4) \n");
            }
            if (currentRoom.upExit != null) {
                System.out.print("En haut, " + currentRoom.upExit.getDescription() + " (5) \n");
            }
            if (currentRoom.downExit != null) {
                System.out.print("En bas, " + currentRoom.downExit.getDescription() + " (6) \n");
            }
            System.out.println();
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }
}
