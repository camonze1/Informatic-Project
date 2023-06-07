
import java.util.ArrayList;

/**
 * 
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

        // hall 11
        System.out.println("méthode create");
        ArrayList<Item> itemsRoomExpensive = new ArrayList();
        ArrayList<Item> itemsRoom = new ArrayList();
        Item tableau = new Item("un tableau", 5.0, 100);
        itemsRoom.add(tableau);
        Item portefeuille = new Item("un porte-feuille", 0.2, 50);
        itemsRoom.add(portefeuille);
        Item parapluie = new Item("un parapluie", 0.30, 3);
        itemsRoom.add(parapluie);
        Item veste = new Item("une veste", 0.800, 60);
        itemsRoom.add(veste);
        Item chaussures = new Item("des chaussures", 0.70, 70);
        itemsRoom.add(chaussures);
        Item sac = new Item("un sac à main", 1.2, 50);
        itemsRoom.add(sac);
        Item trotinette = new Item("une  trotinette", 4.0, 240);
        itemsRoomExpensive.add(trotinette);
        Item lampe = new Item("une  lampe", 0.4, 20);
        itemsRoom.add(lampe);
        Item nike = new Item("une  paire de nike", 0.5, 120);
        itemsRoomExpensive.add(nike);
        Item diffuseur = new Item("des bâtonnets diffuseur Rituals", 0.5, 30);
        itemsRoom.add(diffuseur);
        Item billet = new Item("un billet", 0.1, 5);
        itemsRoom.add(billet);

        for (int i = 0; i < 3; i++) {
            System.out.println(itemsRoom.size());
            int random = (int) (Math.random() * (itemsRoom.size()));
            System.out.println(random);
            System.out.println(itemsRoom.get(random).getDescription());
            hall.addItem(itemsRoom.get(random));
            itemsRoom.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            System.out.println(itemsRoomExpensive.size());
            int random = (int) (Math.random() * (itemsRoomExpensive.size()));
            System.out.println(random);
            System.out.println(itemsRoomExpensive.get(random).getDescription());
            hall.addItem(itemsRoomExpensive.get(random));
            itemsRoomExpensive.remove(random);
        }

        // salon 8
        ArrayList<Item> itemsRoomExpensive = new ArrayList();
        ArrayList<Item> itemsRoom = new ArrayList();
        Item console = new Item("une console de jeux vidéos", 0.5, 300);
        salon.addItem(console);
        Item tele = new Item("une télé", 8.0, 700);
        salon.addItem(tele);
        Item montre = new Item("une montre", 0.02, 150);
        salon.addItem(montre);
        Item lecteurdvd = new Item("un lecteur DVD", 0.5, 80);
        salon.addItem(lecteurdvd);
        Item airpods = new Item("des airpods", 0.01, 160);
        salon.addItem(airpods);
        Item dolby = new Item("des enceintes dolby", 5.0, 320);
        salon.addItem(dolby);
        Item smartphone = new Item("un smartphone", 0.2, 800);
        salon.addItem(smartphone);
        Item bouteille = new Item("une bouteille d'alcool", 1.0, 25);
        salon.addItem(bouteille);

        // bedRoom 11
        ArrayList<Item> itemsRoomExpensive = new ArrayList();
        ArrayList<Item> itemsRoom = new ArrayList();
        Item reveil = new Item("un radio-réveil", 0.03, 13);
        bedRoom.addItem(reveil);
        Item coffre_fort = new Item("un coffre-fort fermé", 20.0, 110);
        bedRoom.addItem(coffre_fort);
        Item lunettes = new Item("des lunettes", 0.01, 300);
        bedRoom.addItem(lunettes);
        Item gourde = new Item("une gourde", 0.50, 15);
        bedRoom.addItem(gourde);
        Item casquette = new Item("une casquette", 0.2, 2);
        bedRoom.addItem(casquette);
        Item flute = new Item("une flute traversière", 0.6, 700);
        bedRoom.addItem(flute);
        Item string = new Item("un string", 0.02, 35);
        bedRoom.addItem(string);
        Item polo = new Item("un polo Ralph Lauren", 0.4, 70);
        bedRoom.addItem(polo);
        Item pupitre = new Item("un pupitre", 1.0, 20);
        bedRoom.addItem(pupitre);
        Item gant_boxe = new Item("un gant de boxe", 0.5, 15);
        bedRoom.addItem(gant_boxe);
        Item paire_chaussette = new Item("une paire de chaussette", 0.1, 5);
        bedRoom.addItem(paire_chaussette);

        // bathRoom 11
        Item collier = new Item("un collier en or", 0.2, 4000);
        bathRoom.addItem(collier);
        Item bague = new Item("une bague en argent", 0.015, 90);
        bathRoom.addItem(bague);
        Item enceinte = new Item("une enceinte JBL", 0.7, 250);
        bathRoom.addItem(enceinte);
        Item lisseur = new Item("un lisseur", 0.6, 600);
        bathRoom.addItem(lisseur);
        Item maquillage = new Item("une trousse de maquillage", 1.2, 150);
        bathRoom.addItem(maquillage);
        Item parfum = new Item("un parfum", 0.8, 110);
        bathRoom.addItem(parfum);
        Item plante = new Item("une plante", 1.0, 50);
        bathRoom.addItem(plante);
        Item miroirsdb = new Item("un miroir", 5.0, 20);
        bathRoom.addItem(miroirsdb);
        Item serpent = new Item("un serpent", 15.0, 400);
        bathRoom.addItem(serpent);
        Item shampoing = new Item("un shampoing ", 0.3, 12);
        bathRoom.addItem(shampoing);
        Item brosse_dent = new Item("une brosse à dent électrique ", 0.25, 20);
        bathRoom.addItem(brosse_dent);

        // office 10
        Item appareil_photo = new Item("un appareil photo", 800.0, 350);
        office.addItem(appareil_photo);
        Item ordinateur = new Item("un ordinateur portable", 1.4, 700);
        office.addItem(ordinateur);
        Item radio = new Item("une radio", 0.7, 40);
        office.addItem(radio);
        Item casque = new Item("un casque de musique", 0.3, 150);
        office.addItem(casque);
        Item pc = new Item("un pc", 4.0, 1012);
        office.addItem(pc);
        Item clavier = new Item("un clavier", 0.4, 70);
        office.addItem(clavier);
        Item livre = new Item("un livre", 0.450, 22);
        office.addItem(livre);
        Item tablette = new Item("une tablette ", 0.4, 550);
        office.addItem(tablette);
        Item imprimante = new Item("une imprimante ", 2.5, 50);
        office.addItem(imprimante);
        Item chargeur = new Item("un chargeur sans fil ", 0.25, 60);
        office.addItem(chargeur);

        // kitchen 12
        Item machine_cafe = new Item("une machine à café", 2.8, 499);
        kitchen.addItem(machine_cafe);
        Item grille_pain = new Item("un grille pain", 0.4, 22);
        kitchen.addItem(grille_pain);
        Item thermomix = new Item("un thermomix", 5.0, 950);
        kitchen.addItem(thermomix);
        Item vaisselle = new Item("un set de vaisselle", 8.0, 130);
        kitchen.addItem(vaisselle);
        Item bouilloire = new Item("une bouilloire", 0.3, 30);
        kitchen.addItem(bouilloire);
        Item chewings_gums = new Item("des chewings gums", 0.1, 2);
        kitchen.addItem(chewings_gums);
        Item tele_c = new Item("une télé", 6.0, 250);
        kitchen.addItem(tele_c);
        Item sodastream = new Item("une sodastream", 0.7, 60);
        kitchen.addItem(sodastream);
        Item sirop = new Item("sirop la maison de guiot", 0.7, 4);
        kitchen.addItem(sirop);
        Item saladier = new Item("un saladier", 1.7, 25);
        kitchen.addItem(saladier);
        Item kinder = new Item("un kinder pingui", 0.07, 15);
        kitchen.addItem(kinder);
        Item panini = new Item("une machine a panini", 4.0, 50);
        kitchen.addItem(panini);

        // diningRoom 10
        Item argenterie = new Item("l’argenterie", 4.0, 350);
        diningRoom.addItem(argenterie);
        Item statue = new Item("une statue", 150.0, 2400);
        diningRoom.addItem(statue);
        Item miroir = new Item("un miroir", 6.0, 285);
        diningRoom.addItem(miroir);
        Item base_alexa = new Item("une base alexa", 0.45, 60);
        diningRoom.addItem(base_alexa);
        Item lampe2 = new Item("une lampe", 5.0, 60);
        diningRoom.addItem(lampe2);
        Item horloge = new Item("une horloge", 3.0, 100);
        diningRoom.addItem(horloge);
        Item champagne = new Item("une bouteille de champagne", 0.75, 110);
        diningRoom.addItem(champagne);
        Item bougie = new Item("une bougie Jewel Candle", 1.0, 45);
        diningRoom.addItem(champagne);
        Item ventilateur = new Item("un ventilateur", 3.0, 33);
        diningRoom.addItem(ventilateur);
        Item bibelots = new Item("des bibelots", 10.0, 60);
        diningRoom.addItem(bibelots);

        // gameRoom 11
        Item cartouches = new Item("des cartouches de jeux vidéos", 0.2, 50);
        gameRoom.addItem(cartouches);
        Item billard = new Item("un billard", 100.0, 600);
        gameRoom.addItem(billard);
        Item arcade = new Item("une arcade de jeu", 80.0, 70);
        gameRoom.addItem(arcade);
        Item flipper = new Item("un flipper", 120.0, 5000);
        gameRoom.addItem(flipper);
        Item jukebox = new Item("un jukebox", 35.0, 1400);
        gameRoom.addItem(jukebox);
        Item manette = new Item("une manette de jeux vidéos", 0.2, 40);
        gameRoom.addItem(manette);
        Item pouffe = new Item("un pouffe", 3.0, 150);
        gameRoom.addItem(pouffe);
        Item uno = new Item("un jeu de uno", 0.75, 12);
        gameRoom.addItem(uno);
        Item drone = new Item("un drone", 0.5, 220);
        gameRoom.addItem(drone);
        Item playmobile = new Item("des playmobiles", 1.0, 12);
        gameRoom.addItem(playmobile);
        Item rubiks = new Item("un rubiks cube", 0.1, 5);
        gameRoom.addItem(rubiks);

        // grenier 7
        Item vase = new Item("un vase", 2.5, 15);
        grenier.addItem(vase);
        Item peintureLV = new Item("une peinture", 1.2, 1200);
        grenier.addItem(peintureLV);
        Item peintureVG = new Item("une peinture", 1.2, 12);
        grenier.addItem(peintureVG);
        Item tourne_disque = new Item("un tourne disque", 4.0, 117);
        grenier.addItem(tourne_disque);
        Item velo = new Item("un vélo", 7.0, 800);
        grenier.addItem(velo);
        Item balle = new Item("une balle de tennis", 0.3, 450);
        grenier.addItem(balle);
        Item vetements = new Item("des vetements", 1.2, 80);
        grenier.addItem(vetements);

        player.setCurrentRoom(outside); // start game outside
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        System.out.println("separé");
        Game thread = new Game();

        System.out.print("fin");
        thread.start();

        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished && thread.isAlive()) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Merci d'avoir joué. Au revoir.");
        System.exit(0);
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
                "Dorobo est un jeu incroyable qui se joue en lignes de commande !\nTu joues le rôle d'un cambrioleur, qui voles des maisons pour gagner de l'argent.\nLe but du jeu est de t'infiltrer dans des maisons et de collecter autant d'argent que possible\navant de t'échapper sans te faire attraper par les propriétaires ou la police...\n");
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
                System.out.println("Je ne comprends pas ce que tu veux dire... Tape 'help' si tu as besoin d'aide.");
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
            System.out.println("Vous ne pouvez pas retourner en arrière, vous êtes au début.");
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
            System.out.println("Cet objet n'est pas dans la pièce.");
        } else {
            if (player.CanTakeIt(item) == true) {
                player.getCurrentRoom().removeItem(item);
                player.addItem(item);
                printLocationInfo();
            } else {
                System.out.println("Cet objet est trop lourd, vous ne pouvez pas le prendre.");
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
            System.out.println("Cet objet n'est pas en votre possession.");
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
                System.out.println("\nIl te reste 30 secondes, dépêche toi de sortir !");
            }
        }
    }
}
