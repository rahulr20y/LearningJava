package com.practice.learningjava.multithreading;

class MyThreadSubclass extends Thread {

    public void run() {
        System.out.println("MyThreadSubclass running");
        System.out.println("MyThreadSubclass Finished");
    }
}

class MyRunnableThread implements Runnable {

    public void run() {
        System.out.println("MyRunnableThread running");
        System.out.println("MyRunnableThread finished");
    }
}

class StoppableRunnable implements Runnable {

    private boolean doStop = false;

    public synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }

    @Override
    public void run() {
        while (keepRunning()) {
            // keep doing what this thread should do.
            System.out.println("Running...");

            try {
                Thread.sleep(3L * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stoppable Runnable stopped...");

    }
}

public class ThreadCreate {
    public static void main(String[] args) {
        // null thread - not doing anything
        Thread thread = new Thread();
        thread.start();

        // Method 1: subclass extends thread class
        MyThreadSubclass myThreadSubclass = new MyThreadSubclass();
        myThreadSubclass.start();

        // Method 1.1: Anonymous Subclass

        Thread threadAnonymousSubclass = new Thread() {
            public void run() {
                System.out.println("threadAnonymousSubclass Running");
                System.out.println("threadAnonymousSubclass Finished");
            }
        };

        threadAnonymousSubclass.start();

        // Method 2: Implement Runnable Interface

        // MyRunnableThread myRunnableThread = new MyRunnableThread();
        // myRunnableThread.run();

        Thread runnableThread = new Thread(new MyRunnableThread());
        runnableThread.start();

        // Method 3: Java Class Implements Runnable

        Runnable myRunnable = new Runnable() {
            public void run() {
                System.out.println("Runnable Anonymous running");
                System.out.println("Runnable Anonymous finished");
            }
        };

        Thread threadAnonymousRunnable = new Thread(myRunnable);
        threadAnonymousRunnable.start();

        // method 4:Java Lambda Implementation of Runnable
        Runnable runnableLamda = () -> {
            System.out.println("Lambda Runnable running");
            System.out.println("Lambda Runnable finished");
        };

        runnableLamda.run();// here it will run as normal method not as thread

        Thread threadLambda2 = new Thread(runnableLamda);
        threadLambda2.start();

        // obtain reference of current thread, get and set thread name

        Runnable runnableLamda2 = () -> {
            String threadname = Thread.currentThread().getName();
            System.out.println("Lambda Runnable " + threadname);
        };
        Thread thread2 = new Thread(runnableLamda2, "Thread27");
        thread2.start();

        // main thread
        String mainThread = Thread.currentThread().getName();
        System.out.println("Main Thread Name: " + mainThread);

        // start multiple thread
        Thread thread3 = new Thread(runnableLamda2, "Thread3");
        thread3.start();
        Thread thread4 = new Thread(runnableLamda2, "Thread4");
        thread4.start();

        // Thread sleep

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("Thread Sleep Started");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread Sleep Finished");
            }

        };
        Thread thread5 = new Thread(runnable);
        thread5.start();

        // stop a java thread
        StoppableRunnable stoppableRunnable = new StoppableRunnable();

        Thread threadStop = new Thread(stoppableRunnable);

        threadStop.start();

        try {
            Thread.sleep(10L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Requesting stop");
        stoppableRunnable.doStop();
        System.out.println("Stop requested");

        // Daemon thread

        Runnable daemoRunnable = () -> {
            while (true) {
                System.out.println("Daemon running..");
            }
        };
        Thread daemonThread = new Thread(daemoRunnable);
        // daemonThread.start(); // will keep on running even after main completes it's
        // execution so to prevent it we are marking it as Daemon
        daemonThread.setDaemon(true);
        daemonThread.start();

        // join a thread - wait for java thread to terminate

        Runnable waitRunnable = () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("count - " + i);
            }
        };
        Thread waitThread = new Thread(waitRunnable);
        waitThread.setDaemon(true);
        waitThread.start();

        try {
            waitThread.join(); // main thread will wait here
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Project loom -fibers

        /*
         * -- Runnable is better as it can implements (multiple interface as multiple
         * inheritance is allowed in interfaces) thread and can extend another class
         * which will not be possible while extending thread class(multiple inheritance
         * in not allowed in java)
         * -- run() vs start()
         * -- Also we cannot call run method directly if we are calling run method then
         * it will behave as normal class on current thread, it is beacuse while calling
         * start function
         * of thread internally it is creating a new thread for us and calling run func
         * -- Only one time we can call thread.start() function
         * -- Advance Topics: Executor Framework , Thread Pool , Atomic Integers
         */
    }
}
