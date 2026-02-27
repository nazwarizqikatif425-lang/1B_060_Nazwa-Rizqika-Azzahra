public class TestShape {
    public static void main(String[] args) {

        // Test Shape 
        System.out.println("=== Test Shape ===");
        Shape s1 = new Shape();
        System.out.println(s1);
        System.out.println("Color: " + s1.getColor());
        System.out.println("Filled: " + s1.isFilled());

        Shape s2 = new Shape("blue", false);
        System.out.println(s2);

        // Test Circle 
        System.out.println("\n=== Test Circle ===");
        Circle c1 = new Circle();
        System.out.println(c1);
        System.out.println("Area: " + c1.getArea());
        System.out.println("Perimeter: " + c1.getPerimeter());

        Circle c2 = new Circle(5.0);
        System.out.println(c2);
        System.out.println("Area: " + c2.getArea());
        System.out.println("Perimeter: " + c2.getPerimeter());

        Circle c3 = new Circle(3.0, "red", false);
        System.out.println(c3);

        // Test Rectangle
        System.out.println("\n=== Test Rectangle ===");
        Rectangle r1 = new Rectangle();
        System.out.println(r1);
        System.out.println("Area: " + r1.getArea());
        System.out.println("Perimeter: " + r1.getPerimeter());

        Rectangle r2 = new Rectangle(4.0, 6.0);
        System.out.println(r2);
        System.out.println("Area: " + r2.getArea());
        System.out.println("Perimeter: " + r2.getPerimeter());

        Rectangle r3 = new Rectangle(3.0, 5.0, "yellow", true);
        System.out.println(r3);

        // Test Square
        System.out.println("\n=== Test Square ===");
        Square sq1 = new Square();
        System.out.println(sq1);
        System.out.println("Area: " + sq1.getArea());
        System.out.println("Perimeter: " + sq1.getPerimeter());

        Square sq2 = new Square(4.0);
        System.out.println(sq2);
        System.out.println("Area: " + sq2.getArea());
        System.out.println("Perimeter: " + sq2.getPerimeter());

        Square sq3 = new Square(5.0, "purple", true);
        System.out.println(sq3);

        // Test setWidth dan setLength pada Square
        System.out.println("\n=== Test setWidth/setLength pada Square ===");
        sq2.setWidth(7.0); 
        System.out.println("Setelah setWidth(7.0): width=" + sq2.getWidth() + " length=" + sq2.getLength());

        sq2.setLength(9.0); 
        System.out.println("Setelah setLength(9.0): width=" + sq2.getWidth() + " length=" + sq2.getLength());
    }
}