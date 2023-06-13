import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;

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
        items = new ArrayList();
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
        String string_exit = "\nSorties : \n\n";
        String[] exit_name = { "Devant", "À droite", "Derrière", "À gauche", "En haut", "En bas" };

        for (int i = 0; i < exit_name.length; i++) {
            if (this.exits.get(Integer.toString(i + 1)) != null) {
                string_exit += "- " + (exit_name[i] + ", " + this.exits.get(Integer.toString(i + 1)).getDescription() + " ("
                        + (i + 1) + ") \n");
            }
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
        return "\n-----------------------------------------------------------------------------------------------------------------\n\nTu es " + description + ".\n\n-----------------------------------------------------------------------------------------------------------------\n" + getExitString()
                + "\n-----------------------------------------------------------------------------------------------------------------\n" + getItemsString();
    }


    /**
     * Add an item in a room
     * 
     * @param item to add
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Delete an item in a room
     * 
     * @param item to remove
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * returns the object in relation to the index of the object list
     * 
     * @param item_index index of the item to be returned
     * @return the item or null if it does not exist
     */
    public Item getItem(int item_index) {
        if (item_index >= items.size()) {
            return null;
        }
        return items.get(item_index);
    }

    /**
     * Return a long description of the items in the room:
     * Objets :
     * Un tableau (1)
     * 
     * @return A description of the item in the room
     */
    public String getItemsString() {
        String string_items = "\nObjets :\n\n";
        if (items.size() == 0) {
            string_items += "Il n'y a aucun objet là où tu es.\n";
        } else {
            for (int i = 0; i < items.size(); i++) {
                string_items += "- " + items.get(i).getDescription() + " (" + (i + 1) + ") " + "\n";
            }
        }
        return string_items;
    }

    public void Shuffle() {
        Collections.shuffle(items);
    }

}
