public class Square extends Rectangle {

    // No-arg constructor
    public Square() {
        super(1.0, 1.0);
    }

    // Constructor dengan side
    public Square(double side) {
        super(side, side); // width dan length sama
    }

    // Constructor dengan side, color, filled
    public Square(double side, String color, boolean filled) {
        super(side, side, color, filled); // width dan length sama
    }

    // Getter dan Setter side
    public double getSide() { return getWidth(); }

    // Override setWidth agar length ikut berubah
    @Override
    public void setWidth(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    // Override setLength agar width ikut berubah
    @Override
    public void setLength(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    public void setSide(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    @Override
    public String toString() {
        return "A Square with side=" + getWidth()
               + ", which is a subclass of " + super.toString();
    }
}
