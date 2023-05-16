/**
 * Class Item - a item in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * An "Item" represents an object in the game scenario.
 * There can be several objects in a room.
 * An object is characterised by a description and a weight
 * 
 */

public class Item {
    private String description;
    private Integer weight;
    private Integer value;

    /**
     * Create a item
     * description is a description of the object as a book
     * weight is the weight of the object
     * 
     * @param description The room's description.
     */
    public Item(String description, Integer weight, Integer value) {
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    /**
     * @return The weight of the item.
     */
    public Integer getWeight() {
        return this.weight;
    }

    /**
     * @return The description of the item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return The value of the item.
     */
    public Integer getValues() {
        return this.value;
    }

}