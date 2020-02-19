package problems;
import java.util.*;
public class TestCircle {
    public static void main(String[] args) {
        Circle c1 = new Circle(1.1, "blue");
        System.out.println(c1);  // toString()
        Circle c2 = new Circle(2.2);
        System.out.println(c2);  // toString()
        Circle c3 = new Circle();
        System.out.println(c3);  // toString()

        // Test Setters and Getters
        c1.setRadius(2.2);
        c1.setColor("green");
        System.out.println(c1);  // toString() to inspect the modified instance
        System.out.println("The radius is: " + c1.getRadius());
        System.out.println("The color is: " + c1.getColor());

        // Test getArea() and getCircumference()
        System.out.printf("The area is: %.2f%n", c1.getArea());
        System.out.printf("The circumference is: %.2f%n", c1.getCircumference());
    }
}
