public class Main {
    public static void main(String[] args) {
        Rectangle[] tables = new Rectangle[] {
                new Rectangle(7, 5),
                new Rectangle(2, 4),
                new Rectangle(3, 5)
        };

        double totalArea = 0;
        double totalPerimeter = 0;
        for (Rectangle table: tables) {
            totalArea += table.getArea();
            totalPerimeter += table.getPerimeter();
        }

        System.out.println("Total area = " + totalArea);
        System.out.println("Total perimeter = " + totalPerimeter);
    }
}
