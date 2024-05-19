package andygibson;

public class Dog extends Pet {
    public enum Size { SMALL, MEDIUM, LARGE };
    private Size size = Size.SMALL;
    public Dog(String name, Size size) {
        this.name = name;
        this.size = size;
    }

    public Size getSize() {
        return size;
    }
}
