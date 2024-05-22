import java.util.HashMap;
import java.util.Map;

public class Room {
    private String description;
    private Map<String, Room> exits;

    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Room> getExits() {
        return exits;
    }
}
