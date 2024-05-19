package andygibson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PersonSerializerTest {

    private Logger logger = LoggerFactory.getLogger(PersonSerializerTest.class);

    @Test
    public void test_smoke() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("MySerializers");
        module.addSerializer(new PersonSerializer());
        module.addSerializer(new PetSerializer());
        mapper.registerModule(module);

        Person p1 = new Person();
        p1.setFirstName("Albert");
        p1.setLastName("Camus");
        p1.addPet(new Dog("Olin", Dog.Size.MEDIUM));

        Person p2 = new Person();
        p2.setFirstName("Evariste");
        p2.setLastName("Galois");
        p2.addPet(new Snake("Harry", 15));

        final Collection<Person> people = new ArrayList<>();
        people.add(p1);
        people.add(p2);

        System.out.println("people unformated : " + mapper.writeValueAsString(people));

        final ObjectWriter prettyWriter = mapper.writerWithDefaultPrettyPrinter();
        System.out.println("people:\n" + prettyWriter.writeValueAsString(people));
    }
}
