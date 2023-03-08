# Interface

## Learning Goals

- Create an interface
- Implement an interface
- Use the debugger to demonstrate polymorphism and dynamic binding

## Introduction

An **Interface** in Java is defined as an abstract type
used to specify the behavior of a class.
Interfaces are used to achieve abstraction, specifying
what a class must do and not how.

## Code-Along

Fork and clone this lesson.  The code includes classes `Main`,
`Rectangle` and `Circle`.  You will create an interface named `Shape`
and adapt the classes to implement the interface.

## Motivation

Let's look at an example application that demonstrates why interfaces are useful.
Assume we own a restaurant with several rectangular tables, and we
need to repaint the table tops.  We need to figure out the total
area and perimeter to determine how much paint to buy.

The `Rectangle` class has methods to calculate area and perimeter:

```java
public class Rectangle {
    private double height, width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getArea() { return height * width; }
    public double getPerimeter () { return 2 * (height + width); }
    
}
```

The driver class `Main` has a  `main` method to
store an array of `Rectangle` objects representing the table tops.
It is easy to loop through the array and accumulate the total area
and perimeter of the rectangles.

```java
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

```

The program prints the totals:

```text
Total area = 58.0
Total perimeter = 52.0
```

But what if we decide to purchase two additional tables that are round rather than
rectangular?    The `Circle` class has methods to calculate
the area and perimeter:

```java
public class Circle {
    private double radius;

    public Circle(double radius) {this.radius = radius;}
    public double getArea() { return Math.PI * radius * radius; }
    public double getPerimeter() { return 2 * Math.PI * radius; }

}
```

Consider the loop in the `main` method, where we call `getArea()` and `getPerimeter()`
on each element in the array:

```java
for (Rectangle table: tables) {
    totalArea += table.getArea();
    totalPerimeter += table.getPerimeter();
}
```

`Circle` objects know how to calculate
area and perimeter.  It would be handy if we could store
`Rectangle` and `Circle` objects in the same array.  
However, our array expects `Rectangle` class instances, so we can't add `Circle`
objects into the array.  This code won't compile:

```java
//ERROR: Can't add Circle objects to the array
Rectangle[]tables=new Rectangle[]{
    new Rectangle(7,5),
    new Rectangle(2,4),
    new Rectangle(3,5),
    new Circle(4),
    new Circle(6)
};
```

The solution is to
create an **interface** named `Shape`, and change the array declaration to
hold objects that implement the `Shape` interface.

## Defining a `Shape` interface - Code Along

![shape uml](https://curriculum-content.s3.amazonaws.com/6677/pillars/shape_uml.png)

We will create an interface named `Shape`
that defines two abstract methods `getArea()` and `getPerimeter()`.
An **abstract**  method is a method with no
implementation (i.e. no curly braces enclosing a method body).

An interface is similar to a class. However, a class defines the attributes and
behaviors of its instances, while an interface describes behaviors
that will be implemented by one or more classes.

- An interface is never instantiated and does not have constructors.
- An interface may define one or more abstract methods.
  - A method declared without a method body within an interface is implicitly public and abstract.
- An interface may define constants.
- An interface may define default and static methods.
- A class that declares it **implements** an interface must provide
  an implementation (i.e. a method body) for every abstract method
  declared in the interface, or the class must also be declared as abstract.

Create a new interface named `Shape` with abstract methods `getArea()`
and `getPerimeter()` as shown below.  Both methods are abstract (no method body)
and public  by default.

```java
public interface Shape {
    double getArea();
    double getPerimeter();
}
```


Update `Rectangle` and `Circle` to declare they implement the `Shape` interface.
Add the `@Override` annotation to the `getArea()` and `getPerimeter()` methods.


```java
public class Rectangle implements Shape {
    private double height, width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double getArea() { return height * width; }

    @Override
    public double getPerimeter() { return 2 * (height + width); }

}

```

```java
public class Circle implements Shape {
  private double radius;

  public Circle(double radius) {this.radius = radius;}

  @Override
  public double getArea() { return Math.PI * radius * radius; }

  @Override
  public double getPerimeter() { return 2 * Math.PI * radius; }

}
```

While the `@Override` annotation is optional, it is best practice to use it to force
the compiler to check the method signatures match the abstract methods in the interface.


Now we can evolve the `main` method to use the `Shape` interface
as the declared type for the array.  We can add to the array
`Rectangle` and `Circle` objects since both classes implement
the `Shape` interface.

```java
Shape[] tables = new Shape[] {
        new Rectangle(7, 5),
        new Rectangle(2, 4),
        new Rectangle(3, 5),
        new Circle(4),
        new Circle(6)
};
```

We also need to change the declared type of the loop variable to `Shape`:

```java
for (Shape table: tables) {
    totalArea += table.getArea();
    totalPerimeter += table.getPerimeter();
}
```

The updated `Main` class is shown below:

```java
public class Main {
    public static void main(String[] args) {
        Shape[] tables = new Shape[] {
                new Rectangle(7, 5),
                new Rectangle(2, 4),
                new Rectangle(3, 5),
                new Circle(4),
                new Circle(6)
        };

        double totalArea = 0;
        double totalPerimeter = 0;
        for (Shape table: tables) {
            totalArea += table.getArea();
            totalPerimeter += table.getPerimeter();
        }

        System.out.println("Total area = " + totalArea);
        System.out.println("Total perimeter = " + totalPerimeter);
    }
}
```

The program now includes the area and perimeter of the
two `Circle` objects in calculating the totals:

```text
Total area = 221.36281798666926
Total perimeter = 114.83185307179586
```

## Debugging the application

Let's use the debugger to step through the code and understand how the `Shape` array is used.

Set a breakpoint at the loop, after the array has been filled with the 3 rectangles
and 2 circles.  Start the debug session and switch to the Java Visualizer view:

![interface set breakpoint](https://curriculum-content.s3.amazonaws.com/6677/pillars/interface_breakpoint.png)

Press "Step Into" to execute the first iteration of the loop, which will assign the
loop variable `table` to point at the first element in the array.

![loop step 1](https://curriculum-content.s3.amazonaws.com/6677/pillars/interface_step1.png)

Press "Step Into" to execute the method call `table.getArea()`, transferring control **into**
the method.  Since `table` references an instance of `Rectangle`, the `getArea()` method
defined in the `Rectangle` class is called.

![loop step 2](https://curriculum-content.s3.amazonaws.com/6677/pillars/interface_step2.png)

Press "Step Into" to calculate and return the area to the `main` method.

Keep pressing "Step Into" to loop through the rest of the elements in the array, calling `getArea()` and
`getPerimeter()` on each shape.  Confirm when the shape is a `Circle` object,
the `getArea()` in `Circle` is executed:

![loop step 3](https://curriculum-content.s3.amazonaws.com/6677/pillars/interface_step3.png)

## Polymorphism

Polymorphism means "many forms".

- A **polymorphic variable** is one that can hold values of different types.
  The array variable `tables` and the loop variable `table` are both polymorphic
  since they reference objects whose class implements `Shape`, i.e. `Rectangle` and `Circle` objects.
- An interface lets us achieve **dynamic polymorphism** by using method overriding in a class that implements the interface.
  When a method is called using a polymorphic variable, the actual method body that gets executed is
  determined at runtime, i.e. dynamically, based on the
  type of the object referenced by a polymorphic variable.
  This is also referred to as **dynamic binding** since it happens a runtime, rather than **static binding**
  that happens at compile-time.

Even though the array's declared type is `Shape`,  the actual `getArea()` or `getPerimeter()`
method that executes each time through the loop depends on the object referenced by the polymorphic variable `table`.
The `Rectangle` methods are called when `table` references a `Rectangle` object,
and the `Circle` methods get called when the `table` references a `Circle` object.

We'll explore polymorphism and dynamic binding more in the next lesson.

## Conclusion

An interface is used to group related abstract methods.
The interface is implemented by a class using the **implements** keyword.
The class must provide a method body for each abstract method defined in the interface.

When a variable is declared with an interface type, the variable can reference an
instance of any class that implements the interface.  Dynamic binding happens
when we call a method using a variable declared with an interface type, the
executed method is determined by the type of the referenced object.

## Resources

[Java Tutorial - Interface](https://docs.oracle.com/javase/tutorial/java/concepts/interface.html)
