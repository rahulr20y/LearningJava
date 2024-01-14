package com.practice.learningjava.Java8features;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowire;

import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy.Provider;

//https://www.youtube.com/watch?v=tCwg16PUZyo&list=PL0zysOflRCenwK0kbJMliCeELVv9SSUbk

@FunctionalInterface // optional Annotation
interface MyFuntionalInterface {
    void sayHello();
}

class MyFuntionalInterfaceImpl implements MyFuntionalInterface {

    @Override
    public void sayHello() {
        System.out.println("Class funtion 'sayHello'");
    }

}

interface ArithmeticFunctionInterface {
    int solve(int a, int b);
}

class Stuff {
    public static void doStuff() {
        System.out.println("I am stuff method");
        System.out.println("Current time is " + LocalDate.now());
    }

    public static void threadTask() {
        for (int i = 0; i <= 10; i++) {
            System.out.println("i is " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printNumbers() {
        for (int i = 0; i <= 5; i++) {
            System.out.println("Number is " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

@FunctionalInterface
interface WorkInterface {
    public void doTask();
}

class Student {
    public Student() {
        System.out.println("I am a Student Constuctor");
    }

    public void display() {
        System.out.println("I am display method of a student class");
    }
}

@FunctionalInterface
interface StudentProvider {
    public Student getStudent();
}

class Box<T> {

    // Object class is top most parent class of all java classes

    T container;

    public Box(T container) {
        this.container = container;
    }

    public void performSomeTask() {
        if (container instanceof String) {
            System.out.println("length of " + container + " is " + (((String) this.container).length()));
        } else if (container instanceof Integer) {
            System.out.println("This is integer value " + container);
        }
    }

    public Object getValue() {
        return this.container;
    }
}


public class Java8FeatureDurg {
    public static void main(String[] args) {
        System.out.println("Main func starts here...");

        // create searate class and implement the interface
        MyFuntionalInterface myFuntionalInterface = new MyFuntionalInterfaceImpl();
        myFuntionalInterface.sayHello();

        // create anonymous class and run the function
        MyFuntionalInterface myFuntionalInterface2 = new MyFuntionalInterface() {

            @Override
            public void sayHello() {
                System.out.println("Anonymous fun 'sayHello'");
            }

        };

        myFuntionalInterface2.sayHello();

        MyFuntionalInterface myFuntionalInterface3 = () -> System.out.println("Lamda Func - 'say hello' ");
        myFuntionalInterface3.sayHello();

        ArithmeticFunctionInterface arithmeticFunctionInterface = (a, b) -> a + b;

        System.out.println(arithmeticFunctionInterface.solve(5, 6));

        ArithmeticFunctionInterface arithmeticFunctionInterface2 = (a, b) -> a * b;
        System.out.println(arithmeticFunctionInterface2.solve(5, 6));

        // lamda in thread
        Runnable runnable = () -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println("value of i " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread myThread = new Thread(runnable);
        myThread.setName("John");
        // myThread.start();

        Runnable runnable2 = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(" 2 * " + i + " = " + 2 * i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread tableThread = new Thread(runnable2);
        tableThread.setName("table");
        // tableThread.start();

        // desktop application using lamda
        JFrame jFrame = new JFrame("My Window");

        jFrame.setSize(400, 500);
        JButton jButton = new JButton("Anonymous Button");
        JButton jButton2 = new JButton("Lamda Button");
        jFrame.add(jButton);
        jFrame.add(jButton2);
        jFrame.setLayout(new FlowLayout());

        // //using anonymous class
        jButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Anonymous Button Click");
                JOptionPane.showMessageDialog(null, "Hey Button!!! I am Anonymous Class");
            }

        });

        // using lamda
        jButton2.addActionListener(e -> {
            System.out.println("Lamda Button Click");
            JOptionPane.showMessageDialog(null, "Hey Button! I am lamda");
        });

        // jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // jFrame.setVisible(true);

        // *************************************************************************************************************
        // */
        // stream API

        List<Integer> list = List.of(1, 2, 3, 4, 5);
        System.out.println(list);

        List<Integer> list2 = new ArrayList<>();
        list2.add(12);
        list2.add(59);
        list2.add(58);
        System.out.println(list2);

        List<Integer> list3 = Arrays.asList(44, 5, 6, 699, 9, 9);
        System.out.println(list3);

        List<Integer> evIntegers = new ArrayList<>();
        // without stream even number
        for (Integer x : list) {
            if (x % 2 == 0) {
                evIntegers.add(x);
            }
        }
        System.out.println(evIntegers);

        // using stream
        Stream<Integer> stream = list.stream();
        List<Integer> evIntegers2 = stream.filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(evIntegers2);
        // or

        List<Integer> evIntegers3 = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(evIntegers3);

        List<Integer> evIntegers4 = list.stream().filter(x -> x > 4).collect(Collectors.toList());
        System.out.println(evIntegers4);

        // stream object - basic ways

        // 1-blank
        Stream<Object> emptyStream = Stream.empty();

        // 2-method
        String names[] = { "archana", "priya", "krishna" };

        Stream<String> nameStream = Stream.of(names);
        nameStream.forEach((x) -> System.out.println(x));

        // 3rd method
        Stream<Object> buildStream = Stream.builder()
                .build();

        // 4th method
        IntStream intStream = Arrays.stream(new int[] { 2, 4, 5, 6, 7, 8, 9, 10 });

        intStream.forEach(e -> System.out.println(e));

        // 5-method list, set
        List<Integer> intliList = Arrays.asList(1, 5, 8, 9, 8, 5, 5, 5);
        Stream<Integer> inStream2 = intliList.stream();
        inStream2.forEach(e -> System.out.println(e));

        // stream operations

        // filter method - predicate - boolean valued function
        // e-> e*2;

        // map - funtion - each element operation

        List<String> citieStrings = List.of("Delhi", "Mumbai", "Pune", "Hyderabad");
        List<String> filterCities = citieStrings.stream().filter(e -> e.startsWith("M")).collect(Collectors.toList());
        System.out.println(filterCities);

        List<Integer> integersList = List.of(1, 4, 3, 2, 5);
        List<Integer> integersList2 = integersList.stream().map(e -> 2 * e).collect(Collectors.toList());
        System.out.println((integersList2));
        integersList2.stream().forEach(e -> System.out.println(e));
        integersList2.stream().forEach(System.out::println);

        // sort

        integersList.stream().sorted().forEach(System.out::println);

        // comparator

        Integer integerMin = integersList.stream().min((x, y) -> x.compareTo(y)).get();
        System.out.println(integerMin);

        Integer integerMax = integersList.stream().max((x, y) -> x.compareTo(y)).get();
        System.out.println(integerMax);

        /********************************************************************************************* */

        // Method and Constructor References
        // method ref are special type of lambda expression

        // using anonymous
        WorkInterface workInterface = () -> System.out.println("I am work inteface lamda");
        workInterface.doTask();

        // now using method ref of existing implemented method which will be refering to
        // implement our class -- here we referred static method
        WorkInterface workInterface2 = Stuff::doStuff;
        workInterface2.doTask();

        Runnable runnable3 = Stuff::threadTask;
        Thread thread = new Thread(runnable3);
        // thread.start();

        // referring non static method
        Stuff stuffObj = new Stuff();
        Runnable runnable4 = stuffObj::printNumbers;
        Thread thread2 = new Thread(runnable4);
        // thread2.start();

        // Constructor reference
        StudentProvider studentProvider = () -> {
            return new Student();
        };
        Student student = studentProvider.getStudent();
        student.display();

        StudentProvider studentProvider2 = Student::new; // using constructor ref
        Student student2 = studentProvider2.getStudent();
        student2.display();

        List<Integer> list4 = List.of(1, 2, 3, 4, 5);
        System.out.println(list4);
        list4.stream().forEach(System.out::println);

        /********************************************************************************************* */

        // Java Optional Class

        String str = null;
        // old way to avoid exception
        if (str == null) {
            System.out.println(str);
        } else {
            System.out.println("String is null");
        }

        // using optional class

        Optional<String> strOptional = Optional.ofNullable(str);
        System.out.println(strOptional.isPresent());
        System.out.println(strOptional.orElse("str is null"));

        /********************************************************************************************** */

        // Java Generic

        List<String> list5 = new ArrayList<>(); // type safe
        list5.add("gf");

        List list6 = new ArrayList<>();// not a type safe
        list6.add("f");
        list6.add(56);

        System.out.println(list5);
        System.out.println(list6);



        Box<String> box = new Box<>("Wow this is amazing");
        System.out.println(box.getValue());
        System.out.println(box.container.getClass().getName());

        Box<Integer> box1 = new Box<>(121);
        System.out.println(box1.getValue());
        System.out.println(box1.container.getClass().getName());

        box.container = "subscribe to youtube channel";
        box1.container = 1452;
        Box<Boolean> box2 = new Box<>(true);
        System.out.println(box2.getValue());
        box.performSomeTask();
        box1.performSomeTask();

        /******************************************************************************************************* */

    }
}
