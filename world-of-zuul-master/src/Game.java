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
        outside.setExit("1", bedRoom);
        outside.setExit("2", kitchen);
        outside.setExit("3", hall);
        outside.setExit("4", gameRoom);

        hall.setExit("1", outside);
        hall.setExit("2", salon);
        hall.setExit("4", diningRoom);
        hall.setExit("5", grenier);

        salon.setExit("3", gameRoom);
        salon.setExit("4", hall);

        gameRoom.setExit("1", salon);
        gameRoom.setExit("2", outside);
        gameRoom.setExit("3", office);

        office.setExit("1", gameRoom);
        office.setExit("4", bedRoom);

        bedRoom.setExit("2", office);
        bedRoom.setExit("3", outside);
        bedRoom.setExit("4", bathRoom);

        bathRoom.setExit("2", bedRoom);

        kitchen.setExit("1", diningRoom);
        kitchen.setExit("4", outside);

        diningRoom.setExit("2", hall);
        diningRoom.setExit("3", diningRoom);

        grenier.setExit("6", hall);

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
        printLocationInfo();
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
        // Room nextRoom = null;
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Il n'y a pas de porte!");
        } else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /**
     * prints the information about the current location
     */
    private void printLocationInfo() {
        System.out.println("Tu es " + currentRoom.getDescription());
        System.out.print("Sorties: \n");
        if (currentRoom.getExit("1") != null) {
            System.out.print("Devant, " + currentRoom.getExit("1").getDescription() + " (1) \n");
        }
        if (currentRoom.getExit("2") != null) {
            System.out.print("A droite, " + currentRoom.getExit("2").getDescription() + " (2) \n");
        }
        if (currentRoom.getExit("3") != null) {
            System.out.print("Derrière, " + currentRoom.getExit("3").getDescription() + " (3) \n");
        }
        if (currentRoom.getExit("4") != null) {
            System.out.print("A gauche, " + currentRoom.getExit("4").getDescription() + " (4) \n");
        }
        if (currentRoom.getExit("5") != null) {
            System.out.print("En haut, " + currentRoom.getExit("5").getDescription() + " (5) \n");
        }
        if (currentRoom.getExit("6") != null) {
            System.out.print("En bas, " + currentRoom.getExit("6").getDescription() + " (6) \n");
        }
        System.out.println();
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
