package andygibson;

import java.util.List;
import java.util.ArrayList;

/**
 * https://www.andygibson.net/blog/article/introduction-to-jackson-serializers/
 */
public class Person {
    private String firstName;
    private String lastName;
    private List<Pet> pets;

    public Person() {
        this.pets = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }
}
