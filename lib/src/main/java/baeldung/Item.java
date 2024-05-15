package baeldung;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ItemSerializer.class)
public class Item {
    public int id;
    public String itemName;
    public User owner;

    public Item(int id, String itemName, User owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }
}
