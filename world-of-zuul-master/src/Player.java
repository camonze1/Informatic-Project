import java.util.*;
import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private Stack<Room> previousRoom = new Stack<Room>();
    private ArrayList<Item> items = new ArrayList();
    private ArrayList<Integer> scores = new ArrayList();
    private float max_weight = 20.0f;

    /**
     * return the current room
     * 
     * @return The current room.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * changes the current room
     * 
     * @param The current room
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * return the last room visited
     * 
     * @return The last room.
     */
    public Room getPreviousRoom() {
        if (previousRoom.size() == 0) {
            return null;
        } else {
            Room lastRoom = previousRoom.peek();
            previousRoom.pop();
            return lastRoom;
        }
    }

    /**
     * adds a room to the list of visited rooms
     * 
     */
    public void setPreviousRoom(Room currentRoom) {
        previousRoom.push(currentRoom);
    }

    /**
     * Add an item to the player's item list
     * 
     * @param item to add
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Delete an item to the player's item list
     * 
     * @param item to remove
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * return the max weight
     * 
     * @return the max weight
     */
    public float getMaxWeight() {
        return max_weight;
    }

    /**
     * Return the player's item list
     * 
     * @return items list
     */
    public ArrayList getItems() {
        return items;
    }

    /**
     * Return the player's total value of items
     * 
     * @return total value
     */
    public int getTotalValue() {
        int value = 0;
        for (int item = 0; item < items.size(); item++) {
            value += items.get(item).getValues();
        }
        return value;
    }

    /**
     * function that adds the total value of items of the current game to a list
     * that matches the total value of all games played.
     */
    public void setHighScore() {
        int value = getTotalValue();
        scores.add(value);
        int max_value = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) > max_value) {
                max_value = scores.get(i);
            }
        }
        System.out.println("Ton meilleur score est de " + max_value + " €.");
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
     * return the total weight
     * 
     * @return weight total
     */
    public float getTotalweight() {
        float weight = 0.0f;
        for (int item = 0; item < items.size(); item++) {
            weight += items.get(item).getWeight();
        }
        return weight;
    }

    /**
     * returns true if the player can take the object else returns false
     */
    public Boolean canTakeIt(Item item) {
        float total_weight = getTotalweight() + item.getWeight();
        if (total_weight <= max_weight) {
            return true;
        } else
            return false;
    }

    /**
     * return the list of items
     */
    public String getItemsString() {
        String string_items = "";
        for (int i = 0; i < this.items.size(); i++) {
            string_items += "- " + this.items.get(i).getDescription() + " (" + (i + 1) + ") " + "\n";
        }
        return string_items;
    }

    /**
     * deletes variables which are renitialised at each game
     */
    public void clear() {
        items.clear();
        previousRoom.clear();
    }
}
