package com.practice.learningjava.lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//https://www.youtube.com/watch?v=ynM4gk_Nhtg&list=PL5mjp3QjkuoLSUXGu8STyrm-yrh9xi54h

interface Anonymous {
    public void show();

    public void print(int x);
}

@FunctionalInterface
interface AnonymousLambda {
    public int show(int a);

    // there can be n number of stactic and default class in Funtional interface
    // class
    static void print() {
        System.out.println("I am static class in functional interface class");
    }

    default void display() {
        System.out.println("I am default class in functional interface class");
    }
}

class Employee {
    public String name;
    public int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + "]";
    }

    public void setId(int id) {
        this.id = id;
    }
}

interface Operation {
    public int action(int a, int b);
}

class Car {
    private String name;
    private String color;
    private double price;

    public Car(String name, String color, double price) {
        this.name = name;
        this.color = color;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car [name=" + name + ", color=" + color + ", price=" + price + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

interface MyPredicate<T> {
    // boolean test(T t);

    // or
    boolean operation(T t);
}

interface Vehicle {
    void horn();

    static void speed() { // available for all class
        System.out.println("Speed test");
    }

    default void engine() { // can be overidden by some class if needed
        System.out.println("4 stroke engine");
    }
}

class Truck implements Vehicle {
    @Override
    public void horn() {
        System.out.println("Truck...");
    }

    @Override
    public void engine() {
        System.out.println("4 stroke engine");
    }
}

class Bike implements Vehicle {

    @Override
    public void horn() {
        System.out.println("Bike..");
    }

}

public class ProgrankJava8features {

    public static void result(int a, int b, Operation operation) {
        System.out.println("Arithmetic Operation of a and b is " +
                operation.action(a, b));
    }

    public static void main(String[] args) {
        System.out.println("Inside progrankJava8features");

        // lambda expression is a enhancement of anonymous class

        Anonymous anonymous = new Anonymous() {

            @Override
            public void show() {
                System.out.println("I am a show function");
            }

            @Override
            public void print(int x) {
                System.out.println("I am a print function - " + x);
            }

        };

        anonymous.show();
        anonymous.print(27);

        // Lambda
        AnonymousLambda anonymousLambda = a -> 2 * a;
        System.out.println("double - " + anonymousLambda.show(27));

        // collection sort via anonymous/lambda with comparator
        List<Employee> employees = new ArrayList<>();
        employees.addAll(Arrays.asList(new Employee("druv", 0), new Employee("abhi", 1), new Employee("aman", 2),
                new Employee("james", 3), new Employee("Rishi", 5)));

        Comparator<Employee> comparator = new Comparator<Employee>() {

            @Override
            public int compare(Employee a, Employee b) {
                return a.getName().compareTo(b.getName());
            }

        };

        // method-1 to sort
        Collections.sort(employees, comparator);

        // method -2 using lambda
        Collections.sort(employees, (a, b) -> a.getName().compareTo(b.getName()));

        // method 2 - using mehthod refrence -- ClassName :: Method_Name
        Collections.sort(employees, Comparator.comparing(Employee::getName));

        System.out.println(employees);

        // Lamda as an argument
        result(20, 7, (a, b) -> a + b);
        result(20, 7, (a, b) -> a - b);
        result(20, 7, (a, b) -> a * b);

        // predicate functional interface
        List<Car> carList = Arrays.asList(new Car("Mercedes", "Black", 2000000.00),
                new Car("Toyota", "red", 1000000.00),
                new Car("BMW", "Blue", 50000.00));

        // Predicate<Car> predicateCar = x -> x.getPrice() > 50000.00; // similar to
        // action function passed in function
        // argument

        // interface Predicate<T> {
        // boolean test(T t);
        // }

        Predicate<Car> predicateCar = x -> x.getColor().contains("B");

        for (Car car : carList) {
            if (predicateCar.test(car)) {
                System.out.println(car.getName());
            }
        }

        // we can create custom predicate as well

        MyPredicate<Car> myPredicate = x -> x.getName().contains("T");
        for (Car car : carList) {
            if (myPredicate.operation(car)) {
                System.out.println(car.getName());
            }
        }

        // predicate in stream
        List<Car> newCarList = carList.stream().filter(x -> x.getPrice() > 50000.00).collect(Collectors.toList());
        System.out.println(newCarList);

        // stream API

        IntStream.range(1, 11).forEach(x -> System.out.println(x));
        IntStream.range(11, 20).forEach(System.out::println);

        IntStream num = IntStream.iterate(1, x -> x + 2);
        num.limit(5).forEach(System.out::println);

        Stream.of("Java", "Python", "Data structure").filter(x -> x.contains("J")).map(String::toUpperCase)
                .forEach(System.out::println);

        Stream.of("Java", "Python", "Data structure").filter(x -> x.contains("P")).map(String::isEmpty)
                .forEach(System.out::println);

        Stream.generate(Math::random).limit(2).forEach(System.out::println);

        // Collect Element Of Stream from Collection
        System.out.println(Arrays.asList("Java", "Python", "Data structure", "Kotlin", "Javascript", "Java")
                .stream().filter(x -> x.contains("J")).collect(Collectors.toList()));
        System.out.println(Arrays.asList("Java", "Python", "Data structure", "Kotlin", "Javascript", "Java")
                .stream().filter(x -> x.contains("J")).collect(Collectors.toSet()));

        // flat map
        List<String> list1 = Arrays.asList("A", "B", "C");
        List<String> list2 = Arrays.asList("D", "E");

        System.out.println(Stream.of(list1, list2).flatMap(List::stream).collect(Collectors.toList()));

        Map<String, List<Integer>> hMap = new LinkedHashMap<>();
        hMap.put("one", Arrays.asList(1, 2, 3));
        hMap.put("two", Arrays.asList(4, 5));
        System.out.println(hMap.values().stream().flatMap(List::stream).collect(Collectors.toList()));

        // parallel () and parallelStream() Method
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 7, 5, 6);
        list.stream().parallel().forEach(System.out::println);
        list.parallelStream().forEach(System.out::println);

        // Important Java Functional Interface| Java Stream API

        // Predicate -- boolean test(T obj);
        Predicate<Integer> predicate = x -> x > 50;
        System.out.println(predicate.test(51));

        // BiPredicate -- boolean test(T obj1,T obj2)
        BiPredicate<Integer, Integer> biPredicate = (x, y) -> x > y;
        System.out.println(biPredicate.test(4, 5));

        // Consumer -- void accept(T obj)
        Consumer<String> consumer = x -> System.out.println(x.length());
        consumer.accept("Test");

        List<Integer> cIntegers = Arrays.asList(1, 2, 3);
        Consumer<Integer> consumer2 = x -> System.out.println(x);
        cIntegers.stream().forEach(consumer2);

        // BiConsumer -- void accept(T obj, Tobj2)
        BiConsumer<String, String> bConsumer = (x, y) -> System.out.println(x + "-" + y);
        bConsumer.accept("Test", "Vest");

        Map<String, Integer> hMap2 = new HashMap<>();
        hMap2.put("Java", 1);
        hMap2.put("C++", 2);
        hMap2.forEach((x, y) -> System.out.println(x + y));

        // Function -- E apply(T obj)
        Function<Integer, Boolean> function = x -> x > 5;
        System.out.println(function.apply(7));

        // BiFunction -- E apply(T obj1, T obj2)
        BiFunction<Integer, Integer, String> function2 = (x, y) -> x + " " + y;
        System.out.println(function2.apply(7, 8));

        // Supplier E get()
        Supplier<String> supplier = () -> "Not found";
        System.out.println(supplier.get());

        // Unary Operator

        UnaryOperator<String> unaryOperator = x -> "Test " + x;
        System.out.println(unaryOperator.apply("Java"));

        // Binary Operator
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        System.out.println(binaryOperator.apply(4, 5));

        // Java 8 "default" and "static" Methods in Interfaces |
        Vehicle truck = new Truck();
        truck.horn();
        truck.engine();
        Vehicle.speed();

        Vehicle bike = new Bike();
        bike.horn();
        bike.engine();
        Vehicle.speed();

        // Default Method Multiple Inheritance Collision | Diamond Problem
        // -> overide common methods declaration or default method implementation in
        // child class

    }
}
