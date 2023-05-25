
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
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        player = new Player();
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
        diningRoom.setExit("3", kitchen);

        grenier.setExit("6", hall);

        // initialise items
        Item tableau = new Item("un tableau", 7.75, 890);
        hall.addItem(tableau);
        Item portefeuille = new Item("un porte-feuille", 0.05, 67);
        hall.addItem(portefeuille);

        Item console = new Item("une console de jeux vidéos", 0.5, 300);
        salon.addItem(console);
        Item tele = new Item("une télé", 8.0, 700);
        salon.addItem(tele);
        Item montre = new Item("une montre", 0.02, 150);
        salon.addItem(montre);

        Item reveil = new Item("un radio-réveil", 0.5, 50);
        bedRoom.addItem(reveil);
        Item coffre_fort = new Item("un coffre-fort fermé", 32.0, 700);
        bedRoom.addItem(coffre_fort);
        Item lunettes = new Item("des lunettes", 0.01, 117);
        bedRoom.addItem(lunettes);

        Item collier = new Item("un collier en or", 0.16, 1200);
        bathRoom.addItem(collier);
        Item bague = new Item("une bague en argent", 0.015, 77);
        bathRoom.addItem(bague);

        Item ordinateur = new Item("un ordinateur portable", 3.0, 800);
        office.addItem(ordinateur);
        Item radio = new Item("une radio", 0.5, 30);
        office.addItem(radio);

        Item machine_cafe = new Item("une machine à café", 4.0, 99);
        kitchen.addItem(machine_cafe);
        Item grille_pain = new Item("un grille pain", 3.5, 75);
        kitchen.addItem(grille_pain);

        Item argenterie = new Item("l’argenterie", 22.0, 54);
        diningRoom.addItem(argenterie);

        Item cartouches = new Item("des cartouches de jeux vidéos", 0.15, 70);
        gameRoom.addItem(cartouches);

        Item vase = new Item("un vase", 7.2, 27);
        grenier.addItem(vase);

        player.setCurrentRoom(outside); // start game outside
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
        } else if (commandWord.equals("look")) {
            look();
        } else if (commandWord.equals("back")) {
            back();
        } else if (commandWord.equals("take")) {
            take(command);
        } else if (commandWord.equals("drop")) {
            drop(command);
        } else if (commandWord.equals("items")) {
            items();
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
        System.out.println(parser.showCommands());
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Aller où?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        // Room nextRoom = null;
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("Il n'y a pas de porte!");
        } else {
            player.setPreviousRoom(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);

            printLocationInfo();
        }
    }

    /**
     * prints the information about the current location
     */
    private void printLocationInfo() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quitter quoi?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }

    /**
     * "look" was entered.Print out the description of the room and the exits
     */
    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * "back" takes the player into the previous room he/she was in was entered
     */
    private void back() {
        player.setCurrentRoom(player.getPreviousRoom());
        printLocationInfo();
    }

    /**
     * "take" pick up the item that is in second word
     */
    private void take(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know xhat to take
            System.out.println("Prendre quoi?\nQUOICOUBEH");
            return;
        }
        String item_index = command.getSecondWord();
        Item item = player.getCurrentRoom().getItem(Integer.parseInt(item_index) - 1);
        if (item == null) {
            System.out.println("Cet objet n'est pas dans la pièce");
        } else {
            if (player.CanTakeIt(item) == true) {
                player.getCurrentRoom().removeItem(item);
                player.addItem(item);
                printLocationInfo();
            } else {
                System.out.println("Cet objet est trop lourd, vous ne pouvez pas le prendre");
            }

        }
    }

    /**
     * "drop" drop the item that is in second word
     */
    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop
            System.out.println("Poser quoi?");
            return;
        }
        String item_index = command.getSecondWord();
        Item item = player.getItem(Integer.parseInt(item_index) - 1);
        if (item == null) {
            System.out.println("Cet objet n'est pas en votre possession");
        } else {
            player.getCurrentRoom().addItem(item);
            player.removeItem(item);

            printLocationInfo();
        }

    }

    /**
     * "items" prints out all items currently carried and their total weight
     */
    private void items() {
        String string_items = "Vos objets: \n";
        for (int i = 0; i < player.getItems().size(); i++) {
            string_items += player.getItem(i).getDescription() + " (" + (i + 1) + ") " + "\n";
        }
        System.out.println(string_items);
        System.out.println("Poids total : " + player.getTotalweight());
    }
}
