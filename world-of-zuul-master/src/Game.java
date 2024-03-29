import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    private Parser parser;
    private Player player;
    private Timer timer;
    private String star_line = "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n";
    private String continuous_line = "_________________________________________________________________________________________________________________\n";
    private String dotted_line = "-----------------------------------------------------------------------------------------------------------------\n";
    private String middle_separation = "                           ------------------------------------------------\n";

    /**
     * Create the game and initialise its internal map.
     */
    public Game(Player player) {
        this.player = player;
        player.clear();
        createRooms();
        this.timer = new Timer(120000);
        this.parser = new Parser();
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
        ArrayList<Item> expensiveItemsRoom = new ArrayList();
        ArrayList<Item> itemsRoom = new ArrayList();
        Item tableau = new Item("un tableau", 5.0f, 100);
        itemsRoom.add(tableau);
        Item portefeuille = new Item("un porte-feuille", 0.2f, 50);
        itemsRoom.add(portefeuille);
        Item parapluie = new Item("un parapluie", 0.3f, 3);
        itemsRoom.add(parapluie);
        Item veste = new Item("une veste", 0.8f, 60);
        itemsRoom.add(veste);
        Item chaussures = new Item("des chaussures", 0.7f, 70);
        itemsRoom.add(chaussures);
        Item sac = new Item("un sac à main", 1.2f, 50);
        itemsRoom.add(sac);
        Item trotinette = new Item("une trotinette", 4.0f, 240);
        expensiveItemsRoom.add(trotinette);
        Item lampe = new Item("une lampe", 0.4f, 20);
        itemsRoom.add(lampe);
        Item nike = new Item("une paire de Nike", 0.5f, 120);
        expensiveItemsRoom.add(nike);
        Item diffuseur = new Item("des bâtonnets diffuseurs Rituals", 0.5f, 30);
        itemsRoom.add(diffuseur);
        Item billet = new Item("un billet", 0.1f, 5);
        itemsRoom.add(billet);

        int random = (int) (Math.random() * (expensiveItemsRoom.size()));
        expensiveItemsRoom.get(random).setAlarm(true);

        for (int i = 0; i < 3; i++) {
            random = (int) (Math.random() * (itemsRoom.size()));
            hall.addItem(itemsRoom.get(random));
            itemsRoom.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            random = (int) (Math.random() * (expensiveItemsRoom.size()));
            hall.addItem(expensiveItemsRoom.get(random));
            expensiveItemsRoom.remove(random);
        }
        hall.Shuffle();

        // salon 8
        ArrayList<Item> expensiveItemsRoom2 = new ArrayList();
        ArrayList<Item> itemsRoom2 = new ArrayList();
        Item console = new Item("une console de jeux vidéos", 0.5f, 300);
        expensiveItemsRoom2.add(console);
        Item tele = new Item("une télé", 8.0f, 700);
        expensiveItemsRoom2.add(tele);
        Item montre = new Item("une montre", 0.02f, 150);
        expensiveItemsRoom2.add(montre);
        Item lecteurdvd = new Item("un lecteur DVD", 0.5f, 80);
        itemsRoom2.add(lecteurdvd);
        Item airpods = new Item("des airpods", 0.01f, 160);
        expensiveItemsRoom2.add(airpods);
        Item dolby = new Item("des enceintes Dolby", 5.0f, 320);
        expensiveItemsRoom2.add(dolby);
        Item smartphone = new Item("un smartphone", 0.2f, 800);
        expensiveItemsRoom2.add(smartphone);
        Item bouteille = new Item("une bouteille d'alcool", 1.0f, 25);
        itemsRoom2.add(bouteille);
        Item chandelier = new Item("un chandelier", 3.0f, 25);
        itemsRoom2.add(chandelier);
        Item vase2 = new Item("un vase", 4.0f, 50);
        itemsRoom2.add(vase2);

        random = (int) (Math.random() * (expensiveItemsRoom2.size()));
        expensiveItemsRoom2.get(random).setAlarm(true);

        for (int i = 0; i < 3; i++) {
            random = (int) (Math.random() * (itemsRoom2.size()));
            salon.addItem(itemsRoom2.get(random));
            itemsRoom2.remove(random);

        }

        for (int i = 0; i < 1; i++) {
            random = (int) (Math.random() * (expensiveItemsRoom2.size()));
            salon.addItem(expensiveItemsRoom2.get(random));
            expensiveItemsRoom2.remove(random);
        }

        salon.Shuffle();

        // bedRoom 11
        ArrayList<Item> expensiveItemsRoom3 = new ArrayList();
        ArrayList<Item> itemsRoom3 = new ArrayList();
        Item reveil = new Item("un radio-réveil", 0.03f, 13);
        itemsRoom3.add(reveil);
        Item coffre_fort = new Item("un coffre-fort fermé", 20.0f, 110);
        expensiveItemsRoom3.add(coffre_fort);
        Item lunettes = new Item("des lunettes", 0.01f, 300);
        expensiveItemsRoom3.add(lunettes);
        Item gourde = new Item("une gourde", 0.50f, 15);
        itemsRoom3.add(gourde);
        Item casquette = new Item("une casquette", 0.2f, 2);
        itemsRoom3.add(casquette);
        Item flute = new Item("une flûte traversière", 0.6f, 700);
        expensiveItemsRoom3.add(flute);
        Item string = new Item("un string", 0.02f, 35);
        itemsRoom3.add(string);
        Item polo = new Item("un polo Ralph Lauren", 0.4f, 70);
        itemsRoom3.add(polo);
        Item pupitre = new Item("un pupitre", 1.0f, 20);
        itemsRoom3.add(pupitre);
        Item gant_boxe = new Item("un gant de boxe", 0.5f, 15);
        itemsRoom3.add(gant_boxe);
        Item paire_chaussette = new Item("une paire de chaussettes", 0.1f, 5);
        itemsRoom3.add(paire_chaussette);

        for (int i = 0; i < 3; i++) {
            random = (int) (Math.random() * (itemsRoom3.size()));
            bedRoom.addItem(itemsRoom3.get(random));
            itemsRoom3.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            random = (int) (Math.random() * (expensiveItemsRoom3.size()));
            bedRoom.addItem(expensiveItemsRoom3.get(random));
            expensiveItemsRoom3.remove(random);
        }

        bedRoom.Shuffle();

        // bathRoom 11
        ArrayList<Item> expensiveItemsRoom4 = new ArrayList();
        ArrayList<Item> itemsRoom4 = new ArrayList();

        Item collier = new Item("un collier en or", 0.2f, 4000);
        expensiveItemsRoom4.add(collier);
        Item bague = new Item("une bague en argent", 0.015f, 90);
        itemsRoom4.add(bague);
        Item enceinte = new Item("une enceinte JBL", 0.7f, 250);
        expensiveItemsRoom4.add(enceinte);
        Item lisseur = new Item("un lisseur", 0.6f, 600);
        expensiveItemsRoom4.add(lisseur);
        Item maquillage = new Item("une trousse de maquillage", 1.2f, 150);
        expensiveItemsRoom4.add(maquillage);
        Item parfum = new Item("un parfum", 0.8f, 110);
        expensiveItemsRoom4.add(parfum);
        Item plante = new Item("une plante", 1.0f, 50);
        itemsRoom4.add(plante);
        Item miroirsdb = new Item("un miroir", 5.0f, 20);
        itemsRoom4.add(miroirsdb);
        Item serpent = new Item("un serpent", 15.0f, 400);
        expensiveItemsRoom4.add(serpent);
        Item shampoing = new Item("un shampoing ", 0.3f, 12);
        itemsRoom4.add(shampoing);
        Item brosse_dent = new Item("une brosse à dents électrique ", 0.25f, 20);
        itemsRoom4.add(brosse_dent);

        random = (int) (Math.random() * (expensiveItemsRoom4.size()));
        expensiveItemsRoom4.get(random).setAlarm(true);

        for (int i = 0; i < 3; i++) {
            random = (int) (Math.random() * (itemsRoom4.size()));
            bathRoom.addItem(itemsRoom4.get(random));
            itemsRoom4.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            random = (int) (Math.random() * (expensiveItemsRoom4.size()));
            bathRoom.addItem(expensiveItemsRoom4.get(random));
            expensiveItemsRoom4.remove(random);
        }

        bathRoom.Shuffle();

        // office 10
        ArrayList<Item> expensiveItemsRoom5 = new ArrayList();
        ArrayList<Item> itemsRoom5 = new ArrayList();

        Item appareil_photo = new Item("un appareil photo", 0.8f, 350);
        expensiveItemsRoom5.add(appareil_photo);
        Item ordinateur = new Item("un ordinateur portable", 1.4f, 700);
        expensiveItemsRoom5.add(ordinateur);
        Item radio = new Item("une radio", 0.7f, 40);
        itemsRoom5.add(radio);
        Item casque = new Item("un casque de musique", 0.3f, 150);
        expensiveItemsRoom5.add(casque);
        Item pc = new Item("un pc", 4.0f, 1012);
        expensiveItemsRoom5.add(pc);
        Item clavier = new Item("un clavier", 0.4f, 70);
        itemsRoom5.add(clavier);
        Item livre = new Item("un livre", 0.45f, 22);
        itemsRoom5.add(livre);
        Item tablette = new Item("une tablette ", 0.4f, 550);
        expensiveItemsRoom5.add(tablette);
        Item imprimante = new Item("une imprimante ", 2.5f, 50);
        itemsRoom5.add(imprimante);
        Item chargeur = new Item("un chargeur sans fil ", 0.25f, 60);
        itemsRoom5.add(chargeur);

        random = (int) (Math.random() * (expensiveItemsRoom5.size()));
        expensiveItemsRoom5.get(random).setAlarm(true);

        for (int i = 0; i < 3; i++) {

            random = (int) (Math.random() * (itemsRoom5.size()));
            office.addItem(itemsRoom5.get(random));
            itemsRoom5.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            random = (int) (Math.random() * (expensiveItemsRoom5.size()));
            office.addItem(expensiveItemsRoom5.get(random));
            expensiveItemsRoom5.remove(random);
        }

        office.Shuffle();

        // kitchen 12
        ArrayList<Item> expensiveItemsRoom6 = new ArrayList();
        ArrayList<Item> itemsRoom6 = new ArrayList();

        Item machine_cafe = new Item("une machine à café", 2.8f, 499);
        expensiveItemsRoom6.add(machine_cafe);
        Item grille_pain = new Item("un grille-pain", 0.4f, 22);
        itemsRoom6.add(grille_pain);
        Item thermomix = new Item("un Thermomix", 5.0f, 950);
        expensiveItemsRoom6.add(thermomix);
        Item vaisselle = new Item("un set de vaisselle", 8.0f, 130);
        expensiveItemsRoom6.add(vaisselle);
        Item bouilloire = new Item("une bouilloire", 0.3f, 30);
        itemsRoom6.add(bouilloire);
        Item chewings_gums = new Item("des chewing-gums", 0.1f, 2);
        itemsRoom6.add(chewings_gums);
        Item tele_c = new Item("une télé", 6.0f, 250);
        expensiveItemsRoom6.add(tele_c);
        Item sodastream = new Item("une Sodastream", 0.7f, 60);
        itemsRoom6.add(sodastream);
        Item sirop = new Item("sirop La Maison Guiot", 0.7f, 4);
        itemsRoom6.add(sirop);
        Item saladier = new Item("un saladier", 1.7f, 25);
        itemsRoom6.add(saladier);
        Item kinder = new Item("un Kinder Pingui", 0.07f, 15);
        itemsRoom6.add(kinder);
        Item panini = new Item("une machine à panini", 4.0f, 50);
        itemsRoom6.add(panini);

        random = (int) (Math.random() * (expensiveItemsRoom6.size()));
        expensiveItemsRoom6.get(random).setAlarm(true);

        for (int i = 0; i < 3; i++) {
            random = (int) (Math.random() * (itemsRoom6.size()));
            kitchen.addItem(itemsRoom6.get(random));
            itemsRoom6.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            random = (int) (Math.random() * (expensiveItemsRoom6.size()));
            kitchen.addItem(expensiveItemsRoom6.get(random));
            expensiveItemsRoom6.remove(random);
        }

        kitchen.Shuffle();

        // diningRoom 10
        ArrayList<Item> expensiveItemsRoom7 = new ArrayList();
        ArrayList<Item> itemsRoom7 = new ArrayList();

        Item argenterie = new Item("l’argenterie", 4.0f, 350);
        expensiveItemsRoom7.add(argenterie);
        Item statue = new Item("une statue", 150.0f, 2400);
        expensiveItemsRoom7.add(statue);
        Item miroir = new Item("un miroir", 6.0f, 285);
        expensiveItemsRoom7.add(miroir);
        Item base_alexa = new Item("une base Alexa", 0.45f, 60);
        itemsRoom7.add(base_alexa);
        Item lampe2 = new Item("une lampe", 5.0f, 60);
        itemsRoom7.add(lampe2);
        Item horloge = new Item("une horloge", 3.0f, 100);
        itemsRoom7.add(horloge);
        Item champagne = new Item("une bouteille de champagne", 0.75f, 110);
        expensiveItemsRoom7.add(champagne);
        Item bougie = new Item("une bougie Jewel Candle", 1.0f, 45);
        itemsRoom7.add(bougie);
        Item ventilateur = new Item("un ventilateur", 3.0f, 33);
        itemsRoom7.add(ventilateur);
        Item bibelots = new Item("des bibelots", 10.0f, 60);
        itemsRoom7.add(bibelots);

        random = (int) (Math.random() * (expensiveItemsRoom7.size()));
        expensiveItemsRoom7.get(random).setAlarm(true);

        for (int i = 0; i < 3; i++) {
            random = (int) (Math.random() * (itemsRoom7.size()));
            diningRoom.addItem(itemsRoom7.get(random));
            itemsRoom7.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            random = (int) (Math.random() * (expensiveItemsRoom7.size()));
            diningRoom.addItem(expensiveItemsRoom7.get(random));
            expensiveItemsRoom7.remove(random);
        }

        diningRoom.Shuffle();

        // gameRoom 11
        ArrayList<Item> expensiveItemsRoom8 = new ArrayList();
        ArrayList<Item> itemsRoom8 = new ArrayList();

        Item cartouches = new Item("des cartouches de jeux vidéos", 0.2f, 50);
        itemsRoom8.add(cartouches);
        Item billard = new Item("un billard", 100.0f, 600);
        expensiveItemsRoom8.add(billard);
        Item arcade = new Item("une arcade de jeu", 80.0f, 70);
        itemsRoom8.add(arcade);
        Item flipper = new Item("un flipper", 120.0f, 5000);
        expensiveItemsRoom8.add(flipper);
        Item jukebox = new Item("un jukebox", 35.0f, 1400);
        expensiveItemsRoom8.add(jukebox);
        Item manette = new Item("une manette de jeux vidéos", 0.2f, 40);
        itemsRoom8.add(manette);
        Item pouffe = new Item("un pouffe", 3.0f, 150);
        expensiveItemsRoom8.add(pouffe);
        Item uno = new Item("un jeu de uno", 0.75f, 12);
        itemsRoom8.add(uno);
        Item drone = new Item("un drône", 0.5f, 220);
        expensiveItemsRoom8.add(drone);
        Item playmobile = new Item("des Playmobils", 1.0f, 12);
        itemsRoom8.add(playmobile);
        Item rubiks = new Item("un Rubik's cube", 0.1f, 5);
        itemsRoom8.add(rubiks);

        random = (int) (Math.random() * (expensiveItemsRoom8.size()));
        expensiveItemsRoom8.get(random).setAlarm(true);

        for (int i = 0; i < 3; i++) {
            random = (int) (Math.random() * (itemsRoom8.size()));
            gameRoom.addItem(itemsRoom8.get(random));
            itemsRoom8.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            random = (int) (Math.random() * (expensiveItemsRoom8.size()));
            gameRoom.addItem(expensiveItemsRoom8.get(random));
            expensiveItemsRoom8.remove(random);
        }

        gameRoom.Shuffle();

        // grenier 7
        ArrayList<Item> expensiveItemsRoom9 = new ArrayList();
        ArrayList<Item> itemsRoom9 = new ArrayList();

        Item vase = new Item("un vase", 2.5f, 15);
        itemsRoom9.add(vase);
        Item peintureLV = new Item("une peinture", 1.2f, 1200);
        expensiveItemsRoom9.add(peintureLV);
        Item peintureVG = new Item("une peinture", 1.2f, 12);
        itemsRoom9.add(peintureVG);
        Item tourne_disque = new Item("un tourne disque", 4.0f, 117);
        expensiveItemsRoom9.add(tourne_disque);
        Item velo = new Item("un vélo", 7.0f, 800);
        expensiveItemsRoom9.add(velo);
        Item balle = new Item("une balle de tennis", 0.3f, 450);
        expensiveItemsRoom9.add(balle);
        Item vetements = new Item("des vêtements", 1.2f, 80);
        itemsRoom9.add(vetements);
        Item livres = new Item("des livres", 1.2f, 27);
        itemsRoom9.add(livres);

        random = (int) (Math.random() * (expensiveItemsRoom9.size()));
        expensiveItemsRoom9.get(random).setAlarm(true);

        for (int i = 0; i < 3; i++) {
            random = (int) (Math.random() * (itemsRoom9.size()));
            grenier.addItem(itemsRoom9.get(random));
            itemsRoom9.remove(random);
        }

        for (int i = 0; i < 1; i++) {
            random = (int) (Math.random() * (expensiveItemsRoom9.size()));
            grenier.addItem(expensiveItemsRoom9.get(random));
            expensiveItemsRoom9.remove(random);
        }

        grenier.Shuffle();
        player.setCurrentRoom(outside); // start game outside
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        timer.start();
        timer.pause();
        printWelcome();
        boolean finished = false;
        while (!finished && timer.isRunning()) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        if (timer.getLost()) {
            System.out.println(continuous_line);
            System.out.println("                                Le temps est écoulé, tu as perdu !");
            System.out.println(continuous_line);
        }
        replay();
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
                System.out.println("\nJe ne comprends pas ce que tu veux dire... Tape 'help' ou 'manual' si tu as besoin d'aide.\n");
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
            case TIMER:
                timer();
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
        if (!timer.getPause()) {
            timer.pause();
        }
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
            System.out.println("\nAller où ?\n");
            System.out.println(player.getCurrentRoom().getExitString());
            return;
        }

        if (timer.getPause()) {
            timer.restart();
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        // Room nextRoom = null;
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("Il n'y a pas de porte !\n");
            System.out.println(player.getCurrentRoom().getExitString());
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
        timer.pause();
        if (command.hasSecondWord()) {
            System.out.println("Quitter quoi ?\n");
            timer.restart();
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }

    /**
     * "look" was entered.Print out the description of the room and the exits
     */
    private void look() {
        if (timer.getPause()) {
            timer.restart();
        }
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * "back" takes the player into the previous room he/she was in was entered
     */
    private void back() {
        if (timer.getPause()) {
            timer.restart();
        }
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
        if (timer.getPause()) {
            timer.restart();
        }
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know xhat to take
            System.out.println("\nPrendre quoi ? \n");
            System.out.println(player.getCurrentRoom().getItemsString());

            return;
        }
        String item_index_str = command.getSecondWord();
        int item_index = 0;
        try {
            item_index = Integer.parseInt(item_index_str);
        } catch (Exception e) {
            System.out.println("La commande n'est pas valide.\n");
            return;
        }
        Item item = player.getCurrentRoom().getItem(item_index - 1);
        if (item == null) {
            System.out.println("\nCet objet n'est pas dans la pièce.\n");
        } else {
            if (player.canTakeIt(item)) {
                player.getCurrentRoom().removeItem(item);
                player.addItem(item);
                if (item.getAlarm()) {
                    timer.alarm();
                    return;
                } else {
                    System.out.println("\nTu as pris " + item.getDescription() + ".\n");
                }
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
        if (timer.getPause()) {
            timer.restart();
        }
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop
            System.out.println("\nPoser quoi ?\n");
            String string_items = player.getItemsString();
            System.out.println(string_items + "\n");
            return;
        }
        String item_index_str = command.getSecondWord();
        int item_index = 0;
        try {
            item_index = Integer.parseInt(item_index_str);
        } catch (Exception e) {
            System.out.println("La commande n'est pas valide.\n");
            return;
        }
        Item item = player.getItem(item_index - 1);
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
        if (timer.getPause()) {
            timer.restart();
        }
        String string_items = "\nLes objets que tu as volé :\n\n";
        if (player.getItems().size() == 0) {
            System.out.println("\nTu n'as pas d'objets dans ton inventaire.\n");
            return;
        } else {
            System.out.println("\nTu as " + player.getItems().size() + " objets dans ton inventaire.\n");
            string_items += player.getItemsString();
        }
        System.out.println(string_items);
        System.out.println("Poids total : " + player.getTotalweight() + " kg.\n");
    }

    /**
     * "timer" prints out the remaining time
     */
    private void timer() {
        if (timer.getPause()) {
            timer.restart();
        }
        System.out.println("\nIl te reste " + timer.getRemainingTime() / 1000 + " secondes pour finir le jeu.\n");
    }

    /**
     * "finish" when the player is outside the house, he can finish the game
     */
    private void finish() {
        timer.pause();
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
                timer.stop();
                timer.setLost(false);
                System.out.println("\n" + star_line);
                System.out.println("\n             Tu as réussi à sortir de la maison avec ton butin sans te faire attraper, félicitations !\n\n                                     Tu as volé pour "
                                + player.getTotalValue()
                                + " € d'objets.\n\n                                       Il te restait "
                                + timer.getRemainingTime() / 1000 + " secondes.\n");
                System.out.println("\n" + star_line + "\n");
            } else if (answer.equals("non")) {
                System.out.println("\nTu as décidé de continuer le jeu.\n");
                timer.restart();
            }
        } else {
            System.out.println("\nTu ne peux pas finir le jeu ici.\n");
            timer.restart();
        }
    }

    /**
     * "replay" when the player finish or loose the game he can play again
     */
    private void replay() {
        timer.stop();
        System.out.println("\nVeux-tu rejouer ? (oui/non)\n");
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        while (!answer.equals("oui") && !answer.equals("non")) {
            System.out.println("\nJe ne comprends pas ce que tu veux dire... (oui/non)\n");
            System.out.print("> ");
            answer = sc.nextLine();
        }
        if (answer.equals("oui")) {
            Game game = new Game(player);
            game.play();
        } else if (answer.equals("non")) {
            System.out.println("\nMerci d'avoir joué ! À la prochaine !\n");
            System.exit(0);
        }
    }

    /**
     * "manual" prints out the rules of the game and the commands
     */
    private void manual() {
        if (!timer.getPause()) {
            timer.pause();
        }
        System.out.println(continuous_line);
        System.out.println("                                            Règles du jeu :\n");
        System.out.println(
                "        Ton but est de cambrioler des maisons. Récupère le plus d'argent possible en volant les\n           objets qui te paraissent les plus chers dans les différentes pièces de la maison.\n\n                                            ! Attention !\n                   N'oublie pas ! Tu ne peux transporter qu'un certain poids d'objets.\n                                       !! Have fun and good luck !!\n\n");
        System.out.println(middle_separation);
        System.out.println("Voici les différentes commandes à ta disposition et leur utilité :\n");
        System.out.println("help         --->  Liste les différentes commandes que tu peux utiliser dans le jeu.\n");
        System.out
                .println("manual       --->  Présente les règles du jeu et l'utilisation des différentes commandes.\n");
        System.out.println(
                "look         --->  Présente l'endroit où tu te trouves, les différentes pièces où tu peux\n                   aller et les objets présents autour de toi.\n");
        System.out.println("items        --->  Liste les objets qui sont en ta possession.\n");
        System.out.println("timer        --->  Affiche le temps qu'il te reste avant la fin de la partie.\n");
        System.out.println(
                "go 'choix'   --->  Permet de te déplacer dans la maison. Indique ton choix avec le chiffre\n                   qui correspond à la sortie que tu souhaites.\n");
        System.out.println("back         --->  Permet de retourner dans l'endroit précédent.\n");
        System.out.println(
                "take 'choix' --->  Permet de prendre un objet. Indique ton choix avec le chiffre qui\n                   correspond à l'objet que tu souhaites voler.\n");
        System.out.println(
                "drop 'choix' --->  Permet de déposer un objet de ton inventaire à l'endroit où tu te\n                   trouves. Indique ton choix avec le chiffre qui correspond à l'objet\n                   que tu veux supprimer de ton inventaire.\n");
        System.out.println(
                "finish       --->  Permet, losque tu es en dehors de la maison, de finir ta partie afin\n                   de remporter tous les objets que tu as volé, et de connaître\n                   la valeur de ton butin.\n");
        System.out.println(
                "quit         --->  Permet de quitter le jeu définitivement. Attention si tu quittes le jeu,\n                   tu perdras tous les scores des parties que tu as gagnés !");
        System.out.println(continuous_line);
    }
}
