package baeldung;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        Item myItem = new Item(1, "theItem", new User(2, "theUser"));

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Item.class, new ItemSerializer());
        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(myItem);
        System.out.println(serialized);
    }
}
