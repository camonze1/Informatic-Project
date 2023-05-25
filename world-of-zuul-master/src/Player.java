import java.util.*;
import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private Stack<Room> previousRoom = new Stack<Room>();
    private ArrayList<Item> items = new ArrayList();
    private Double max_weight = 10.0;

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
        Room lastRoom = previousRoom.peek();
        previousRoom.pop();
        return lastRoom;
    }

    /**
     * adds a room to the list of visited rooms
     * 
     * @param The room to add
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
     * Return the player's item list
     * 
     * @return items list
     */
    public ArrayList getItems() {
        return items;
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
    public Double getTotalweight() {
        Double weight = 0.0;
        for (int item = 0; item < items.size(); item++) {
            weight += items.get(item).getWeight();
        }
        return weight;

    }

    /**
     * returns true if the player can take the object else returns false
     */
    public Boolean CanTakeIt(Item item) {
        Double total_weight = getTotalweight() + item.getWeight();
        if (total_weight <= max_weight) {
            return true;
        } else
            return false;
    }
}
