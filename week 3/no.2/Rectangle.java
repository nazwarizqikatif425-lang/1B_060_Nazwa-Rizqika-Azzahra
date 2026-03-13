public class Rectangle extends Shape {
    private double width;
    private double length;

    // No-arg constructor
    public Rectangle() {
        super();
        width = 1.0;
        length = 1.0;
    }

    // Constructor dengan width dan length
    public Rectangle(double width, double length) {
        super();
        this.width = width;
        this.length = length;
    }

    // Constructor dengan width, length, color, filled
    public Rectangle(double width, double length, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    // Getter dan Setter
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }

    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }

    // Hitung luas persegi panjang
    public double getArea() {
        return width * length;
    }

    // Hitung keliling persegi panjang
    public double getPerimeter() {
        return 2 * (width + length);
    }

    @Override
    public String toString() {
        return "A Rectangle with width=" + width + " and length=" + length
               + ", which is a subclass of " + super.toString();
    }
}