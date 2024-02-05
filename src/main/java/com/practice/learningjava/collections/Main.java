package com.practice.learningjava.collections;

public class Main {
    public static void main(String[] args) {
        System.out.println("Collection in java..");
        /*
         * Collection:
         * 1. List
         * 2. SET
         * 3. Queue
         * 
         * Map - part of collection but it does not implement collection Interface
         * 
         * 
         * #Concurrent Collections:
         * Concurrent collections (e.g. ConcurrentHashMap), achieve thread-safety by
         * dividing their data into segments. In a ConcurrentHashMap, for example,
         * different threads can acquire locks on each segment, so multiple threads can
         * access the Map at the same time (a.k.a. concurrent access).
         * Concurrent collections are much more performant than synchronized
         * collections, due to the inherent advantages of concurrent thread access.
         * So, the choice of what type of thread-safe collection to use depends on the
         * requirements of each use case, and it should be evaluated accordingly.
         * 
         * Note: In collection inside a loop if you try to add and delete simultaneously
         * it will throw an concurrent exception error
         * eg. Fail Fast and Fail Safe Iterator in Java
         * 
         * 
         * https://www.geeksforgeeks.org/collections-in-java-2/
         * https://www.javatpoint.com/collections-in-java
         * 
         */

    }
}
