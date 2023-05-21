import java.util.*;

public class Player {

    private Room currentRoom;
    private Stack<Room> previousRoom = new Stack<Room>();

    /**
     * @return The current room.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * @param The current room
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * @return The last room.
     */
    public Room getPreviousRoom() {
        Room lastRoom = previousRoom.peek();
        previousRoom.pop();
        return lastRoom;
    }

    /**
     * @param The room to add
     */
    public void setPreviousRoom(Room currentRoom) {
        previousRoom.push(currentRoom);
    }

}
