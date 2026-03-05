//********************************************************************
// Shape.java
//
// Abstract class representing a generic shape.
//********************************************************************
public abstract class Shape {
    private String shapeName;

    public Shape(String name) {
        shapeName = name;
    }

    public abstract double area();

    public String toString() {
        return shapeName;
    }
}