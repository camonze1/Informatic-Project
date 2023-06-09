import java.util.Scanner;

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
    private static int timer = 3000;
    private String star_line = "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n";
    private String continuous_line = "_________________________________________________________________________________________________________________\n";
    private String dotted_line = "-----------------------------------------------------------------------------------------------------------------\n";
    private String middle_separation = "                           ------------------------------------------------\n";


    /**
     * Create the game and initialise its internal map.
     */
    public Game(Player player) {
        this.player = player;
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
            int random = (int) (Math.random() * (itemsRoom.size()));
            hall.addItem(itemsRoom.get(random));
            itemsRoom.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            int random = (int) (Math.random() * (itemsRoomExpensive.size()));
            hall.addItem(itemsRoomExpensive.get(random));
            itemsRoomExpensive.remove(random);
        }
        hall.Shuffle();

        // salon 8
        ArrayList<Item> itemsRoomExpensive2 = new ArrayList();
        ArrayList<Item> itemsRoom2 = new ArrayList();
        Item console = new Item("une console de jeux vidéos", 0.5, 300);
        itemsRoomExpensive2.add(console);
        Item tele = new Item("une télé", 8.0, 700);
        itemsRoomExpensive2.add(tele);
        Item montre = new Item("une montre", 0.02, 150);
        itemsRoomExpensive2.add(montre);
        Item lecteurdvd = new Item("un lecteur DVD", 0.5, 80);
        itemsRoom2.add(lecteurdvd);
        Item airpods = new Item("des airpods", 0.01, 160);
        itemsRoomExpensive2.add(airpods);
        Item dolby = new Item("des enceintes dolby", 5.0, 320);
        itemsRoomExpensive2.add(dolby);
        Item smartphone = new Item("un smartphone", 0.2, 800);
        itemsRoomExpensive2.add(smartphone);
        Item bouteille = new Item("une bouteille d'alcool", 1.0, 25);
        itemsRoom2.add(bouteille);
        Item chandelier = new Item("un chandelier", 3.0, 25);
        itemsRoom2.add(chandelier);

        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * (itemsRoom2.size()));
            salon.addItem(itemsRoom2.get(random));
            itemsRoom2.remove(random);

        }

        for (int i = 0; i < 1; i++) {
            int random = (int) (Math.random() * (itemsRoomExpensive2.size()));
            salon.addItem(itemsRoomExpensive2.get(random));
            itemsRoomExpensive2.remove(random);
        }

        salon.Shuffle();

        // bedRoom 11
        ArrayList<Item> itemsRoomExpensive3 = new ArrayList();
        ArrayList<Item> itemsRoom3 = new ArrayList();
        Item reveil = new Item("un radio-réveil", 0.03, 13);
        itemsRoom3.add(reveil);
        Item coffre_fort = new Item("un coffre-fort fermé", 20.0, 110);
        itemsRoomExpensive3.add(coffre_fort);
        Item lunettes = new Item("des lunettes", 0.01, 300);
        itemsRoomExpensive3.add(lunettes);
        Item gourde = new Item("une gourde", 0.50, 15);
        itemsRoom3.add(gourde);
        Item casquette = new Item("une casquette", 0.2, 2);
        itemsRoom3.add(casquette);
        Item flute = new Item("une flute traversière", 0.6, 700);
        itemsRoomExpensive3.add(flute);
        Item string = new Item("un string", 0.02, 35);
        itemsRoom3.add(string);
        Item polo = new Item("un polo Ralph Lauren", 0.4, 70);
        itemsRoom3.add(polo);
        Item pupitre = new Item("un pupitre", 1.0, 20);
        itemsRoom3.add(pupitre);
        Item gant_boxe = new Item("un gant de boxe", 0.5, 15);
        itemsRoom3.add(gant_boxe);
        Item paire_chaussette = new Item("une paire de chaussette", 0.1, 5);
        itemsRoom3.add(paire_chaussette);

        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * (itemsRoom3.size()));
            bedRoom.addItem(itemsRoom3.get(random));
            itemsRoom3.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            int random = (int) (Math.random() * (itemsRoomExpensive3.size()));
            bedRoom.addItem(itemsRoomExpensive3.get(random));
            itemsRoomExpensive3.remove(random);
        }

        bedRoom.Shuffle();

        // bathRoom 11
        ArrayList<Item> itemsRoomExpensive4 = new ArrayList();
        ArrayList<Item> itemsRoom4 = new ArrayList();

        Item collier = new Item("un collier en or", 0.2, 4000);
        itemsRoomExpensive4.add(collier);
        Item bague = new Item("une bague en argent", 0.015, 90);
        itemsRoom4.add(bague);
        Item enceinte = new Item("une enceinte JBL", 0.7, 250);
        itemsRoomExpensive4.add(enceinte);
        Item lisseur = new Item("un lisseur", 0.6, 600);
        itemsRoomExpensive4.add(lisseur);
        Item maquillage = new Item("une trousse de maquillage", 1.2, 150);
        itemsRoomExpensive4.add(maquillage);
        Item parfum = new Item("un parfum", 0.8, 110);
        itemsRoomExpensive4.add(parfum);
        Item plante = new Item("une plante", 1.0, 50);
        itemsRoom4.add(plante);
        Item miroirsdb = new Item("un miroir", 5.0, 20);
        itemsRoom4.add(miroirsdb);
        Item serpent = new Item("un serpent", 15.0, 400);
        itemsRoomExpensive4.add(serpent);
        Item shampoing = new Item("un shampoing ", 0.3, 12);
        itemsRoom4.add(shampoing);
        Item brosse_dent = new Item("une brosse à dent électrique ", 0.25, 20);
        itemsRoom4.add(brosse_dent);

        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * (itemsRoom4.size()));
            bathRoom.addItem(itemsRoom4.get(random));
            itemsRoom4.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            int random = (int) (Math.random() * (itemsRoomExpensive4.size()));
            bathRoom.addItem(itemsRoomExpensive4.get(random));
            itemsRoomExpensive4.remove(random);
        }

        bathRoom.Shuffle();

        // office 10
        ArrayList<Item> itemsRoomExpensive5 = new ArrayList();
        ArrayList<Item> itemsRoom5 = new ArrayList();

        Item appareil_photo = new Item("un appareil photo", 800.0, 350);
        itemsRoomExpensive5.add(appareil_photo);
        Item ordinateur = new Item("un ordinateur portable", 1.4, 700);
        itemsRoomExpensive5.add(ordinateur);
        Item radio = new Item("une radio", 0.7, 40);
        itemsRoom5.add(radio);
        Item casque = new Item("un casque de musique", 0.3, 150);
        itemsRoomExpensive5.add(casque);
        Item pc = new Item("un pc", 4.0, 1012);
        itemsRoomExpensive5.add(pc);
        Item clavier = new Item("un clavier", 0.4, 70);
        itemsRoom5.add(clavier);
        Item livre = new Item("un livre", 0.450, 22);
        itemsRoom5.add(livre);
        Item tablette = new Item("une tablette ", 0.4, 550);
        itemsRoomExpensive5.add(tablette);
        Item imprimante = new Item("une imprimante ", 2.5, 50);
        itemsRoom5.add(imprimante);
        Item chargeur = new Item("un chargeur sans fil ", 0.25, 60);
        itemsRoom5.add(chargeur);

        for (int i = 0; i < 3; i++) {

            int random = (int) (Math.random() * (itemsRoom5.size()));
            office.addItem(itemsRoom5.get(random));
            itemsRoom5.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            int random = (int) (Math.random() * (itemsRoomExpensive5.size()));
            office.addItem(itemsRoomExpensive5.get(random));
            itemsRoomExpensive5.remove(random);
        }

        office.Shuffle();

        // kitchen 12
        ArrayList<Item> itemsRoomExpensive6 = new ArrayList();
        ArrayList<Item> itemsRoom6 = new ArrayList();

        Item machine_cafe = new Item("une machine à café", 2.8, 499);
        itemsRoomExpensive6.add(machine_cafe);
        Item grille_pain = new Item("un grille pain", 0.4, 22);
        itemsRoom6.add(grille_pain);
        Item thermomix = new Item("un thermomix", 5.0, 950);
        itemsRoomExpensive6.add(thermomix);
        Item vaisselle = new Item("un set de vaisselle", 8.0, 130);
        itemsRoomExpensive6.add(vaisselle);
        Item bouilloire = new Item("une bouilloire", 0.3, 30);
        itemsRoom6.add(bouilloire);
        Item chewings_gums = new Item("des chewings gums", 0.1, 2);
        itemsRoom6.add(chewings_gums);
        Item tele_c = new Item("une télé", 6.0, 250);
        itemsRoomExpensive6.add(tele_c);
        Item sodastream = new Item("une sodastream", 0.7, 60);
        itemsRoom6.add(sodastream);
        Item sirop = new Item("sirop la maison de guiot", 0.7, 4);
        itemsRoom6.add(sirop);
        Item saladier = new Item("un saladier", 1.7, 25);
        itemsRoom6.add(saladier);
        Item kinder = new Item("un kinder pingui", 0.07, 15);
        itemsRoom6.add(kinder);
        Item panini = new Item("une machine a panini", 4.0, 50);
        itemsRoom6.add(panini);

        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * (itemsRoom6.size()));
            kitchen.addItem(itemsRoom6.get(random));
            itemsRoom6.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            int random = (int) (Math.random() * (itemsRoomExpensive6.size()));
            kitchen.addItem(itemsRoomExpensive6.get(random));
            itemsRoomExpensive6.remove(random);
        }

        kitchen.Shuffle();

        // diningRoom 10
        ArrayList<Item> itemsRoomExpensive7 = new ArrayList();
        ArrayList<Item> itemsRoom7 = new ArrayList();

        Item argenterie = new Item("l’argenterie", 4.0, 350);
        itemsRoomExpensive7.add(argenterie);
        Item statue = new Item("une statue", 150.0, 2400);
        itemsRoomExpensive7.add(statue);
        Item miroir = new Item("un miroir", 6.0, 285);
        itemsRoomExpensive7.add(miroir);
        Item base_alexa = new Item("une base alexa", 0.45, 60);
        itemsRoom7.add(base_alexa);
        Item lampe2 = new Item("une lampe", 5.0, 60);
        itemsRoom7.add(lampe2);
        Item horloge = new Item("une horloge", 3.0, 100);
        itemsRoom7.add(horloge);
        Item champagne = new Item("une bouteille de champagne", 0.75, 110);
        itemsRoomExpensive7.add(champagne);
        Item bougie = new Item("une bougie Jewel Candle", 1.0, 45);
        itemsRoom7.add(bougie);
        Item ventilateur = new Item("un ventilateur", 3.0, 33);
        itemsRoom7.add(ventilateur);
        Item bibelots = new Item("des bibelots", 10.0, 60);
        itemsRoom7.add(bibelots);

        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * (itemsRoom7.size()));
            diningRoom.addItem(itemsRoom7.get(random));
            itemsRoom7.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            int random = (int) (Math.random() * (itemsRoomExpensive7.size()));
            diningRoom.addItem(itemsRoomExpensive7.get(random));
            itemsRoomExpensive7.remove(random);
        }

        diningRoom.Shuffle();

        // gameRoom 11
        ArrayList<Item> itemsRoomExpensive8 = new ArrayList();
        ArrayList<Item> itemsRoom8 = new ArrayList();

        Item cartouches = new Item("des cartouches de jeux vidéos", 0.2, 50);
        itemsRoom8.add(cartouches);
        Item billard = new Item("un billard", 100.0, 600);
        itemsRoomExpensive8.add(billard);
        Item arcade = new Item("une arcade de jeu", 80.0, 70);
        itemsRoom8.add(arcade);
        Item flipper = new Item("un flipper", 120.0, 5000);
        itemsRoomExpensive8.add(flipper);
        Item jukebox = new Item("un jukebox", 35.0, 1400);
        itemsRoomExpensive8.add(jukebox);
        Item manette = new Item("une manette de jeux vidéos", 0.2, 40);
        itemsRoom8.add(manette);
        Item pouffe = new Item("un pouffe", 3.0, 150);
        itemsRoomExpensive8.add(pouffe);
        Item uno = new Item("un jeu de uno", 0.75, 12);
        itemsRoom8.add(uno);
        Item drone = new Item("un drone", 0.5, 220);
        itemsRoomExpensive8.add(drone);
        Item playmobile = new Item("des playmobiles", 1.0, 12);
        itemsRoom8.add(playmobile);
        Item rubiks = new Item("un rubiks cube", 0.1, 5);
        itemsRoom8.add(rubiks);

        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * (itemsRoom8.size()));
            gameRoom.addItem(itemsRoom8.get(random));
            itemsRoom8.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            int random = (int) (Math.random() * (itemsRoomExpensive8.size()));
            gameRoom.addItem(itemsRoomExpensive8.get(random));
            itemsRoomExpensive8.remove(random);
        }

        gameRoom.Shuffle();

        // grenier 7
        ArrayList<Item> itemsRoomExpensive9 = new ArrayList();
        ArrayList<Item> itemsRoom9 = new ArrayList();

        Item vase = new Item("un vase", 2.5, 15);
        itemsRoom9.add(vase);
        Item peintureLV = new Item("une peinture", 1.2, 1200);
        itemsRoomExpensive9.add(peintureLV);
        Item peintureVG = new Item("une peinture", 1.2, 12);
        itemsRoom9.add(peintureVG);
        Item tourne_disque = new Item("un tourne disque", 4.0, 117);
        itemsRoomExpensive9.add(tourne_disque);
        Item velo = new Item("un vélo", 7.0, 800);
        itemsRoomExpensive9.add(velo);
        Item balle = new Item("une balle de tennis", 0.3, 450);
        itemsRoomExpensive9.add(balle);
        Item vetements = new Item("des vetements", 1.2, 80);
        itemsRoom9.add(vetements);

        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * (itemsRoom9.size()));
            grenier.addItem(itemsRoom9.get(random));
            itemsRoom9.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            int random = (int) (Math.random() * (itemsRoomExpensive9.size()));
            grenier.addItem(itemsRoomExpensive9.get(random));
            itemsRoomExpensive9.remove(random);
        }

        grenier.Shuffle();
        player.setCurrentRoom(outside); // start game outside
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        Game thread = new Game(player);
        thread.start();
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished && thread.isAlive()) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.exit(0);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println(star_line);
        System.out.println("                                            Bienvenue dans Dorobo !\n");
        System.out.println(star_line);
        System.out.println("                        Dorobo est un jeu incroyable qui se joue en lignes de commande !\n              Tu joues le rôle d'un cambrioleur, qui voles des maisons pour gagner de l'argent.\n         Le but du jeu est de t'infiltrer dans des maisons et de collecter autant d'argent que possible\n                 avant de t'échapper sans te faire attraper par les propriétaires ou la police...\n");
        System.out.println("                                      Ton sac a une capacité de " + player.getMaxWeight() + " kg.\n");
        System.out.println(continuous_line);
        System.out.println("                                        Tape 'help' si tu as besoin d'aide.\n");
        System.out.println(continuous_line);
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
                System.out.println(
                        "\nJe ne comprends pas ce que tu veux dire... Tape 'help' ou 'manual' si tu as besoin d'aide.\n");
                break;
            case HELP:
                help();
                break;
            case GO:
                go(command);
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
            case FINISH:
                finish();
                break;
            case MANUAL:
                manual();
                break;
            default:
                help();
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
    private void help() {
        System.out.println("\nTu es perdu ? Tu ne sais plus quoi faire ?\n");
        System.out.println("Tes commandes sont :\n");
        System.out.println(dotted_line);
        System.out.println(parser.showCommands() + "\n");
        System.out.println(dotted_line);
        System.out.println("Si tu ne sais pas comment les utiliser, tu peux utiliser la fonction 'manual'.\n");
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void go(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Aller où ?\n");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        // Room nextRoom = null;
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("Il n'y a pas de porte !\n");
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
            System.out.println("Quitter quoi ?\n");
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
            System.out.println("\nTu ne peux pas retourner en arrière, tu es au début.\n");
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
            System.out.println("\nPrendre quoi ? \n");
            return;
        }
        String item_index = command.getSecondWord();
        Item item = player.getCurrentRoom().getItem(Integer.parseInt(item_index) - 1);
        if (item == null) {
            System.out.println("\nCet objet n'est pas dans la pièce.\n");
        } else {
            if (player.canTakeIt(item) == true) {
                player.getCurrentRoom().removeItem(item);
                player.addItem(item);
                System.out.println("\nTu as pris " + item.getDescription() + ".\n");
                printLocationInfo();
            } else {
                System.out.println("\nCet objet est trop lourd, tu ne peux pas le prendre.\n");
            }
        }
    }

    /**
     * "drop" drop the item that is in second word
     */
    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop
            System.out.println("\nPoser quoi ?\n");
            return;
        }
        String item_index = command.getSecondWord();
        Item item = player.getItem(Integer.parseInt(item_index) - 1);
        if (item == null) {
            System.out.println("\nTu n'as pas cet objet sur toi.\n");
        } else {
            player.getCurrentRoom().addItem(item);
            player.removeItem(item);
            System.out.println("\nTu as posé " + item.getDescription() + ".\n");
            printLocationInfo();
        }
    }

    /**
     * "items" prints out all items currently carried and their total weight
     */
    private void items() {
        String string_items = "\nLes objets que tu as volé :\n\n";
        if(player.getItems().size() == 0){
            System.out.println("\nTu n'as pas d'objets dans ton inventaire.\n");
            return;
        } else {
            System.out.println("\nTu as " + player.getItems().size() + " objets dans ton inventaire.\n");
            for (int i = 0; i < player.getItems().size(); i++) {
            string_items += "- " + player.getItem(i).getDescription() + " (" + (i + 1) + ") " + "\n";
            }
        }
        System.out.println(string_items);
        System.out.println("Poids total : " + player.getTotalweight() + " kg.\n");
    }

    /**
     * "finish" when the player is outside the house, he can finish the game
     */
    private void finish() {
        if (player.getCurrentRoom().getDescription().equals("dehors devant la maison")) {
            System.out.println("\nEs-tu sûr de vouloir quitter le jeu ? (oui/non)\n");
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            String answer = sc.nextLine();
            while (!answer.equals("oui") && !answer.equals("non")) {
                System.out.println("\nJe ne comprends pas ce que tu veux dire... (oui/non)\n");
                System.out.print("> ");
                answer = sc.nextLine();
            }
            if (answer.equals("oui")) { 
                System.out.println("\n" + star_line);
                System.out.println("\n             Tu as réussi à sortir de la maison avec ton butin sans te faire attraper, félicitation !\n\n                                     Tu as volé pour " + player.getTotalValue() + " € d'objets.\n");
                System.out.println("\n" + star_line + "\n");                
                System.exit(0);
            } else if (answer.equals("non")) {
                System.out.println("\nTu as décidé de continuer le jeu.\n");
            }
        } else {
            System.out.println("\nTu ne peux pas finir le jeu ici.\n");
        }
    }

    /**
     * "manual" prints out the rules of the game and the commands
     */
    private void manual(){
        System.out.println(continuous_line);
        System.out.println("                                            Règles du jeu :\n");
        System.out.println("        Ton but est de cambrioler des maisons. Récupère le plus d'argent possible en volant les\n           objets qui te paraissent les plus cher dans les différentes pièces de la maison.\n\n                                            ! Attention !\n                   N'oublie pas ! Tu ne peux transporter qu'un certain poids d'objets.\n                                       !! Have fun and good luck !!\n\n");
        System.out.println(middle_separation);
        System.out.println("Voici les différentes commandes à ta disposition et leur utilité :\n");
        System.out.println("help         --->  Liste les différentes commandes que tu peux utiliser dans le jeu.\n");
        System.out.println("manual       --->  Présente les règles du jeu et l'utilisation des différentes commandes.\n");
        System.out.println("look         --->  Présente l'endroit où tu te trouves, les différentes pièces où tu peux\n                   aller et les objets présents autour de toi.\n");
        System.out.println("items        --->  Liste les objets qui sont en ta possession.\n");
        System.out.println("go 'choix'   --->  Permet de te déplacer dans la maison. Indique ton choix avec le chiffre\n                   qui correspond à la sortie que tu souhaites.\n");
        System.out.println("back         --->  Permet de retourner dans l'endroit précédent.\n");
        System.out.println("take 'choix' --->  Permet de prendre un objet. Indique ton choix avec le chiffre qui\n                   correspond à l'objet que tu souhaites voler.\n");
        System.out.println("drop 'choix' --->  Permet de déposer un objet de ton inventaire à l'endroit où tu te\n                   trouves. Indique ton choix avec le chiffre qui correspond à l'objet\n                   que tu veux supprimer de ton inventaire.\n");
        System.out.println("finish       --->  Permet, losque tu es en dehors de la maison, de finir ta partie afin\n                   de remporter tous les objets que tu as volé, et de connaître\n                   la valeur de ton butin.\n");
        System.out.println("quit         --->  Permet de quitter le jeu définitivement. Attention si tu quittes le jeu,\n                   tu perdras tous les scores des parties que tu as gagnées !");
        System.out.println("\n");
        System.out.println(continuous_line);
    }

    /*
     * public void run() start timer thread
     */
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Problème de timer");
            }
            timer--;
            if (timer == 0) {
                System.out.println("\nLe temps est écoulé, tu as perdu");
                System.exit(0);
            } else if (timer == 30) {
                System.out.println(
                        "\n------------------------------------------------\nIl te reste 30 secondes, dépêche toi de sortir !\n------------------------------------------------\n");
            }
        }
    }
}
