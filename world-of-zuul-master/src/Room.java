
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is
 * connected to other rooms via exits. The exits are labelled north,
 * east, south, west. For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * 
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define the exits of this room. Every direction either leads
     * to another room or is null (no exit there).
     * 
     * @param direction The exit number.
     * @param neighbor  The room exit.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @param direction The exit number.
     * @return The room of the direction exit.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return a description of the room’s exits,
     * for example, "Exits: north west".
     * 
     * @return A description of the available exits.
     */
    public String getExitString() {
        String string_exit = "Sorties: \n";

        if (this.exits.get("1") != null) {
            string_exit += ("Devant, " + this.exits.get("1").getDescription() + " (1) \n");
        }
        if (this.exits.get("2") != null) {
            string_exit += ("A droite, " + this.exits.get("2").getDescription() + " (2) \n");
        }
        if (this.exits.get("3") != null) {
            string_exit += ("Derrière, " + this.exits.get("3").getDescription() + " (3) \n");
        }
        if (this.exits.get("4") != null) {
            string_exit += ("A gauche, " + this.exits.get("4").getDescription() + " (4) \n");
        }
        if (this.exits.get("5") != null) {
            string_exit += ("En haut, " + this.exits.get("5").getDescription() + " (5) \n");
        }
        if (this.exits.get("6") != null) {
            string_exit += ("En bas, " + this.exits.get("6").getDescription() + " (6) \n");
        }
        return string_exit;
    }

    /**
     * Return a long description of this room, of the form:
     * You are in the kitchen.
     * Exits: north west
     * 
     * @return A description of the room, including exits.
     */
    public String getLongDescription() {
        return "Tu es " + description + ".\n" + getExitString();
    }

}
