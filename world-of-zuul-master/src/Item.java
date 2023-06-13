public class Item {
    private String description;
    private Double weight;
    private Integer value;
    private Boolean alarm;

    /**
     * Create a item
     * description is a description of the object as a book
     * weight is the weight of the object
     * 
     * @param description The item's description.
     * @param weight      The item's weight.
     * @param value       The item's value.
     */
    public Item(String description, Double weight, Integer value) {
        this.description = description;
        this.weight = weight;
        this.value = value;
        this.alarm = false;
    }

    /**
     * @return The weight of the item.
     */
    public Double getWeight() {
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

    /**
     * @return The alarm of the item.
     */
    public Boolean getAlarm() {
        return this.alarm;
    }

    /**
     * @param The value of the alarm.
     */
    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }
}