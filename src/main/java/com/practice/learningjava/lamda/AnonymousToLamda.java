package com.practice.learningjava.lamda;

//https://www.youtube.com/watch?v=FTk7bGPAYpo

interface AnonyDemo {
    void method1();

    void method2();
}

class DemoAnonymousToLamda implements AnonyDemo {

    @Override
    public void method1() {
        System.out.println("I am Method-1");
    }

    @Override
    public void method2() {
        System.out.println("I am Method-2");
    }

}

// Interface having single method are called functional interface
@FunctionalInterface
interface AnonyDemoFunctionalInterface {
    void method1(int a);
}

/**
 * 
 */
class AnonyDemoFunctionalInterfaceImpl implements AnonyDemoFunctionalInterface {

    @Override
    public void method1(int a) {
        System.out.println("I am method-1 of AnonyDemoFunctionalInterfaceImpl " + a);
    }

}

public class AnonymousToLamda {
    public static void main(String[] args) {
        System.out.println("AnonymousToLamda main function is running...");

        // traditonal way to run/call function
        AnonyDemo demoAnonymousToLamda = new DemoAnonymousToLamda();
        demoAnonymousToLamda.method1();
        demoAnonymousToLamda.method2();

        // Anonymous class of way implementing methods and running it there.
        AnonyDemo anonyDemo = new AnonyDemo() {

            @Override
            public void method1() {
                System.out.println("I am method-1 of anonymous class");
            }

            @Override
            public void method2() {
                System.out.println("I am method-2 of anonymous class");
            }

        };
        anonyDemo.method1();
        anonyDemo.method2();

        // functional interface
        AnonyDemoFunctionalInterface anonyDemoFunctionalInterface = new AnonyDemoFunctionalInterfaceImpl();
        anonyDemoFunctionalInterface.method1(27);

        // lamda expression
        AnonyDemoFunctionalInterface anonyDemoFunctionalInterface2 = new AnonyDemoFunctionalInterface() {

            @Override
            public void method1(int a) {
                System.out.println("I am method-1 of function interface written like anonymous class " + a);
            }

        };
        anonyDemoFunctionalInterface2.method1(27);

        // lamda expression having multiple statements
        AnonyDemoFunctionalInterface anonyDemoFunctionalInterface3 = (a) -> {
            System.out.println("I am method-1 in lamda expression " + a);
        };
        anonyDemoFunctionalInterface3.method1(27);

        // lamda expression haviig single statement
        AnonyDemoFunctionalInterface anonyDemoFunctionalInterface4 = (a) -> System.out
                .println("I am method-1 in lamda expression having single statement " + a);
        anonyDemoFunctionalInterface4.method1(27);

    }
}
