public class Main {
    public static void main(String[] args) {
        Rectangle[] tables = new Rectangle[] {
                new Rectangle(7, 5),
                new Rectangle(2, 4),
                new Rectangle(3, 5)
        };

        double totalArea = 0;
        double totalCircumference = 0;
        for (Rectangle table: tables) {
            totalArea += table.getArea();
            totalCircumference += table.getCircumference();
        }

        System.out.println("Total area = " + totalArea);
        System.out.println("Total circumference = " + totalCircumference);
    }
}
