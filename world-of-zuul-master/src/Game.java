
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

public class Game extends Thread {
    private Parser parser;
    private Player player;
    private static int timer = 120;

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
        Game thread = new Game();
        thread.start();

        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished || thread.isAlive()) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Merci d'avoir joué. Au revoir.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
        System.out.println("   Bienvenue dans Dorobo !\n");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
        System.out.println(
                "Dorobo est un jeu incroyable qui se joue en lignes de commande !\nTu joues le rôle d'un cambrioleur, qui vole des maisons pour gagner de l'argent.\nLe but du jeu est de t'infiltrer dans des maisons et de collecter autant d'argent que possible\navant de t'échapper sans te faire attraper par les propriétaires ou la police...\n");
        System.out.println("------------------------------------\n");
        System.out.println("Tape 'help' si tu as besoin d'aide.\n");
        System.out.println("------------------------------------");
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

        CommandWord commandWord = this.parser.getCommandWords().getTranslate(command.getCommandWord());

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("Je ne comprends pas ce que tu veux dire... Tapes 'help' si tu as besoin d'aide.");
                break;
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case LOOK:
                look();
                break;
            case BACK:
                back();
                break;
            case TAKE:
                take(command);
                break;
            case DROP:
                drop(command);
                break;
            case ITEMS:
                items();
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            default:
                printHelp();
                break;
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
        System.out.println("\nTu es perdu ? Tu ne sais plus quoi faire ?");
        System.out.println("Tes commandes sont :\n");
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
        Room lastRoom = player.getPreviousRoom();
        if (lastRoom == null) {
            System.out.println("Vous ne pouvez pas retourner en arrière, vous êtes au début");
        } else {
            player.setCurrentRoom(lastRoom);
            printLocationInfo();
        }

    }

    /**
     * "take" pick up the item that is in second word
     */
    private void take(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know xhat to take
            System.out.println("Prendre quoi?\n");
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

    /*
     * public void run() start timer thread
     */

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
            timer--;
            if (timer == 0) {
                System.out.println("\nLe temps est écoulé, tu as perdu");
                System.exit(0);
            } else if (timer == 30) {
                System.out.println("\n*Il te reste 30 secondes, dépêche toi de sortir !");
            }
        }
    }
}
