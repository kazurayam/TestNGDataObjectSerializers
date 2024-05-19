package andygibson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
public class PersonSerializer extends StdSerializer<Person> {

    protected PersonSerializer() {
        super(Person.class);
    }

    @Override
    public void serialize(final Person value,
                          final JsonGenerator gen,
                          final SerializerProvider provider)
            throws IOException {
        gen.writeStartObject();
        gen.writeStringField("firstName", value.getFirstName());
        gen.writeStringField("lastName", value.getLastName());
        gen.writeFieldName("pets");
        gen.writeObject(value.getPets());
        gen.writeEndObject();
    }
}
