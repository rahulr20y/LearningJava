package com.practice.learningjava.lamda;
//https://www.youtube.com/watch?v=gpIUfj3KaOc&list=PLqq-6Pq4lTTa9YGfyhyW2CqdtW9RtY-I3&index=1

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

@FunctionalInterface // optional but informa that it should have only a single method
interface Greeting {
    void perform();
}

class HelloWorldGreeting implements Greeting {
    @Override
    public void perform() {
        System.out.println("Hello world");
    }
}

class Greeter {
    public void greet(Greeting greeting) {
        greeting.perform();
    }
}

interface StringLengthLambda {
    public int getLength(String s);
}

class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
    }

}

@FunctionalInterface
interface Condition {
    boolean test(Person p);
}

public class LamdaFuncPractice {

    public static void printLamda(StringLengthLambda l) {
        System.out.println("Inside printLamda " + l.getLength("Hello"));
    }

    public static void printConditionally(List<Person> people, Condition condition) {
        for (Person p : people) {
            if (condition.test(p)) {
                System.out.println(p);
            }
        }
    }

    public static void printConditionallyWithPredicate(List<Person> people, Predicate<Person> predicate) {
        for (Person p : people) {
            if (predicate.test(p)) {
                System.out.println(p);
            }
        }
    }

    public static void performConditionallyWithPredicateAndConsumer(List<Person> people, Predicate<Person> predicate,
            Consumer<Person> consumer) {
        for (Person p : people) {
            if (predicate.test(p)) {
                consumer.accept(p);
            }
        }
    }

    public static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> consumer) {
        for (int i : someNumbers) {
            consumer.accept(i, key);
        }
    }

    interface ProcessClosure {
        void process(int i);
    }

    public static void doProcessClosure(int i, ProcessClosure p) {
        p.process(i);
    }

    public static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> consumer) {
        // return consumer;

        // return (v, k) -> System.out.println(v + k);

        // return (v, k) -> {
        // System.out.println("Executiing from wrapper");
        // consumer.accept(v, k);
        // };

        return (v, k) -> {
            try {
                consumer.accept(v, k);
            } catch (ArithmeticException e) {
                System.out.println("Arithmetic Exception caught in warpper lamda" + e);
            }
        };

    }

    interface ProcessThisRef {
        void process(int i);
    }

    public void doProcessThisRef(int i, ProcessThisRef p) {
        p.process(i);
    }

    public void executeThisRef() {
        doProcessThisRef(5, i -> {
            System.out.println("value of i " + i);
            System.out.println(this);
        });
    }

    public static void printMessage() {
        System.out.println("Hello");
    }

    public static <T> void main(String[] args) {
        System.out.println("LamdaFuncPractice main fuction...");

        // java 8 lamda
        // -understanding lamdas
        // -using lamdas
        // -funtional interfaces
        // -method refrences
        // -collection improvements

        // passing and executing a behaviour
        Greeting greeting = new HelloWorldGreeting();
        Greeter greeter = new Greeter();
        greeter.greet(greeting);

        // public void greet(action){
        // action()
        // }

        // --function as a value
        // DataType ablockofcode = public void perform(){
        // system.out.println("Hello World!");
        // }

        // ablockofcode = ()->{system.out.println("Hello World!")};
        // ablockofcode = ()->system.out.println("Hello World!");

        // greetingFunction = () -> system.out.println("Greeting");

        // doubleNumberFunction = public int double(int a){
        // return 2*a;
        // }

        // doubleNumberFunction = (int a) -> {
        // return 2*a;
        // }

        // doubleNumberFunction = (int a) -> 2 * a;

        // addfunc = (int a, int b) -> a + b;

        // safeDivideFunction = (int a,int b)->{
        // if(b==0)
        // return 0;
        // return a/b;
        // }

        // anonymous inner class
        Greeting innerGreeting = new Greeting() {
            @Override
            public void perform() {
                System.out.println("Anonymous inner class");
            }
        };
        innerGreeting.perform();

        // greeting via lamda expression
        Greeting greetinglamdafunc = () -> {
            System.out.println("Greeting from lamda function");
        };
        greetinglamdafunc.perform();

        // type interface
        // StringLengthLambda stringLengthLambda = (String s) -> s.length();
        // StringLengthLambda stringLengthLambda = (String s) -> s.length();
        StringLengthLambda stringLengthLambda = s -> s.length();
        System.out.println("Lamda expression : " + stringLengthLambda.getLength("stringLengthLambda"));

        // passing lamda as a function arguement
        printLamda(stringLengthLambda);

        // runnable using lambda
        Thread myThread = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("Inside Runnable func");
            }

        });
        myThread.run();

        Thread myThreadLamda = new Thread(() -> System.out.println("Lambda runnable function"));
        myThreadLamda.run();

        // functional Interface

        // lamda exercise
        List<Person> people = Arrays.asList(
                new Person("Rahul", "yadav", 0),
                new Person("archana", "rajak", 1),
                new Person("jim", "harper", 2),
                new Person("pam", "besley", 3));

        // above steps soln without lamda
        // step 1: sort list by last name
        System.out.println("sort list by last name");
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person arg0, Person arg1) {
                return arg0.getLastName().compareTo(arg1.getLastName());
            }

        });
        // step 2:create method that prints all the elements in the list
        System.out.println("prints all the elements in the list");
        for (Person p : people) {
            System.out.println(p);
        }
        // step 3: create a method that prints all the people that have last name with Y

        // for (Person p : people) {
        // if (p.getLastName().startsWith("y")) {
        // System.out.println(p);
        // }
        // }
        System.out.println("prints all the people that have last name start with r");
        printConditionally(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return p.getLastName().startsWith("r");
            }
        });
        System.out.println("prints all the people that have last name end with K");
        printConditionally(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return p.getLastName().endsWith("k");
            }
        });

        // soln with lamda
        System.out.println("Soln with lambda:");
        // step 1: sort list by last name
        System.out.println("sort list by last name");
        Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));

        // step 2:create method that prints all the elements in the list
        System.out.println("prints all the elements in the list");
        for (Person p : people) {
            System.out.println(p);
        }
        // another way to print all the elements
        printConditionally(people, p -> true);

        // step 3: create a method that prints all the people that have last name with r
        System.out.println("prints all the people that have last name start with r");
        printConditionally(people, (p) -> p.getLastName().startsWith("r"));
        System.out.println("prints all the people that have last name end with K");
        printConditionally(people, (p) -> p.getLastName().endsWith("k"));

        // ---------------------------------------------------------------------------------------------------//
        System.out.println("Unit- 2");
        // unit-2
        // ############ using function interfaces #########
        // predicate interface example
        printConditionallyWithPredicate(people, p -> true);

        // another example
        System.out.println("example with prediacte and consumer");
        performConditionallyWithPredicateAndConsumer(people, (p) -> true, (p) -> System.out.println(p));
        performConditionallyWithPredicateAndConsumer(people, (p) -> true, (p) -> System.out.println(p.getFirstName()));

        // Exeception handling examples - important
        int[] someNumbers = { 1, 2, 3, 4, 5 };
        int key = 2;// 0 -> for exception
        process(someNumbers, key, (v, k) -> System.out.println("val: " + v + " key: " + k));

        process(someNumbers, key, (v, k) -> {
            try {
                System.out.println(v / k);
            } catch (ArithmeticException e) {
                System.out.println("Arithmetic" + e);
            }
        });

        // to handle exception wrap it in another lamda
        process(someNumbers, key, wrapperLambda((v, k) -> System.out.println(v / k)));

        // closures in lamda expressions
        int a = 10;
        int b = 5;
        doProcessClosure(2, new ProcessClosure() {
            @Override
            public void process(int i) {
                System.out.println(i + b);
            }
        });

        doProcessClosure(5, i -> System.out.println(i + b));

        // this reference in lamdas
        LamdaFuncPractice lamdaFuncPractice = new LamdaFuncPractice();
        lamdaFuncPractice.doProcessThisRef(5, new ProcessThisRef() {

            @Override
            public void process(int i) {
                System.out.println("value of i " + i);
                System.out.println(this); // value of new anonymous class
            }
        });

        lamdaFuncPractice.doProcessThisRef(25, i -> {
            System.out.println("value of i " + i);
            // System.out.println(this);//this will not work coz parent is static function
            // no having nay object
        });

        lamdaFuncPractice.executeThisRef();// this will work -> meaning lamda take ref from its parent function itself

        // -------------------------------------------------------------------------------------------------------------//
        // UNIT-3
        // Method references and collections

        Thread myThread2 = new Thread(() -> printMessage());
        myThread2.start();

        // LamdaFuncPractice::printMessage == ()->printMessage
        Thread myThread3 = new Thread(LamdaFuncPractice::printMessage);
        myThread3.start();

        // performConditionallyWithPredicateAndConsumer(people, (p) -> true, p ->
        // System.out::println);// (p) -> method(p)

        // The For Each Iteration
        System.out.println("Using for loop");
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }

        System.err.println("using for in loop");
        for (Person p : people) {
            System.out.println(p);
        }

        System.out.println("using for each loop");
        people.forEach(p -> System.out.println(p));
        people.forEach(System.out::println);// method ref

        // Streams -> a sequence of element supporting sequential and parallel aggregate
        // operations

        people.stream().forEach(p -> System.out.println(p.getFirstName()));

        people.stream().filter(p -> p.getLastName().startsWith("r")).forEach(p -> System.out.println(p));

        long cnt = people.stream().filter(p -> p.getLastName().startsWith("r")).count();
        System.out.println(cnt);

        // wrap up
    }

    @Override
    public String toString() {
        return "LamdaFuncPractice []";
    }

}
