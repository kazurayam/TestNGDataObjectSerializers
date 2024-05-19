package andygibson;

import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@Data
public class Pet {
    protected String name;
    public String getName() {
        return name;
    }
}
