public class Rectangle  {
    private double height, width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getArea() { return height * width; }

    public double getCircumference () { return 2 * (height + width); }

}
