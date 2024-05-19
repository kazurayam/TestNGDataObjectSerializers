package andygibson;

public class Snake extends Pet {
    private int length;
    public Snake(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
