package com.practice.learningjava.collections.examples;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

class Student implements Comparable<Student> {
    private int maths;
    private int physics;

    public Student(int maths, int physics) {
        this.maths = maths;
        this.physics = physics;
    }

    public int getMaths() {
        return maths;
    }

    public int getPhysics() {
        return physics;
    }

    public void setMaths(int maths) {
        this.maths = maths;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    @Override
    public String toString() {
        return "Student [maths=" + maths + ", physics=" + physics + "]";
    }

    @Override
    public int compareTo(Student o) {
        return this.maths - o.getMaths();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + maths;
        result = prime * result + physics;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (maths != other.maths)
            return false;
        if (physics != other.physics)
            return false;
        return true;
    }

}

public class CollectionsExample2 {
    public static void main(String[] args) {
        // for Normal Queue we will use linked list for simplicity
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(5);
        System.out.println(queue.toString());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());
        System.out.println(queue.toString());

        System.out.println("*********************************************");

        // for stack
        Stack<Integer> st = new Stack<>();
        st.push(3);
        st.push(7);
        while (!st.isEmpty()) {
            System.out.println(st.peek());
            System.out.println(st.pop());
        }
        System.out.println("*********************************************");

        // for deque
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerFirst(5);
        dq.offerFirst(6);
        System.out.println(dq.toString());
        System.out.println(dq.peekFirst());
        dq.pollFirst();
        System.out.println(dq.toString());
        dq.offerLast(8);
        System.out.println(dq.toString());

        System.out.println("*********************************************");

        // priority queue by default min priority Queue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(0);
        pq.offer(1);
        pq.offer(2);
        pq.offer(100);
        // add first k=2 element
        List<Integer> listK = new ArrayList<>();
        int k = 2;
        while (!pq.isEmpty() && k > 0) {
            listK.add(pq.peek());
            pq.poll();
            k--;
        }
        System.out.println(listK);
        System.out.println(pq.toString());

        // create max priority Queue
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> -1 * (a - b)); // using comparator function
        pq2.offer(0);
        pq2.offer(1);
        pq2.offer(2);
        pq2.offer(100);
        // add first k=2 element
        List<Integer> listK2 = new ArrayList<>();
        k = 2;
        while (!pq2.isEmpty() && k > 0) {
            listK2.add(pq2.peek());
            pq2.poll();
            k--;
        }
        System.out.println("ListK2 max PQ-- " + listK2);
        System.out.println(pq2.toString());

        // priority queue with class
        PriorityQueue<Student> studentsPQ = new PriorityQueue<>();
        studentsPQ.add(new Student(50, 90));
        studentsPQ.add(new Student(60, 80));
        studentsPQ.add(new Student(70, 70));
        studentsPQ.add(new Student(80, 60));
        studentsPQ.add(new Student(90, 50));
        List<Student> listSK = new ArrayList<>();

        /*
         * //error it is because priority doesn't know how to compare element
         * k = 3;
         * while (!studentsPQ.isEmpty() && k > 0) {
         * listSK.add(studentsPQ.poll());
         * k--;
         * }
         * System.out.println(listK);
         * System.out.println(studentsPQ.toString());
         */

        // After implementing Comparable function in Student class
        k = 3;
        while (!studentsPQ.isEmpty() && k > 0) {
            listSK.add(studentsPQ.poll());
            k--;
        }
        System.out.println("listSK--" + listSK);
        System.out.println(studentsPQ.toString());

        // Now Using Comparator

        PriorityQueue<Student> studentsPQComp = new PriorityQueue<>((a, b) -> a.getPhysics() - b.getPhysics());
        studentsPQComp.add(new Student(50, 90));
        studentsPQComp.add(new Student(60, 80));
        studentsPQComp.add(new Student(70, 70));
        studentsPQComp.add(new Student(80, 60));
        studentsPQComp.add(new Student(90, 50));
        List<Student> listSKComp = new ArrayList<>();
        k = 3;
        while (!studentsPQComp.isEmpty() && k > 0) {
            listSKComp.add(studentsPQComp.poll());
            k--;
        }
        System.out.println("listSKComp--" + listSKComp);
        System.out.println(studentsPQComp.toString());

        // sets
        System.out.println("*********************************************");

        Set<Integer> set = new HashSet<>(); // any order
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        set.add(4);
        System.out.println("set" + set);

        Set<Integer> set2 = new LinkedHashSet<>();// insertion order is maintained
        set2.add(1);
        set2.add(2);
        set2.add(2);
        set2.add(3);
        set2.add(4);
        System.out.println("set2" + set2);

        Set<Student> studentsSet = new LinkedHashSet<>();
        studentsSet.add(new Student(40, 40));
        studentsSet.add(new Student(30, 40));
        studentsSet.add(new Student(60, 50));

        System.out.println(studentsSet.contains(new Student(60, 50))); // false even though this value exist it is
                                                                       // because we have to implement hascode and
                                                                       // equals in our custom class

        // after implementing hascode and equal in student class
        System.out.println(studentsSet.contains(new Student(60, 50)));

        // Set<Student> studentsSet2 = new TreeSet<>();// will get sort based on
        // compareTo function in student class
        Set<Student> studentsSet2 = new TreeSet<>((a, b) -> a.getPhysics() - b.getPhysics());
        studentsSet2.add(new Student(40, 40));
        studentsSet2.add(new Student(30, 40));
        studentsSet2.add(new Student(60, 50));
        System.out.println(studentsSet2);

        NavigableSet<Integer> set3 = new TreeSet<>();
        set3.add(8);
        set3.add(3);
        set3.add(1);
        set3.add(0);
        System.out.println(set3);
        System.out.println(set3.floor(2));
        System.out.println(set3.ceiling(2));

        System.out.println("*********************************************");

        // Map
        Map<String, Integer> hmap = new HashMap<>();
        hmap.put("rahul", 1);
        hmap.put("raj", 2);
        System.out.println(hmap.get("raj"));
        System.out.println(hmap.get("sahil"));
        System.out.println(hmap.getOrDefault("sahil", 3));
        System.out.println(hmap.containsKey("sahil"));

        // adjacency list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        if (adj.get(1) == null)
            adj.put(1, new ArrayList<>());
        adj.get(1).add(2);
        adj.computeIfAbsent(2, f -> new ArrayList<>()).add(1);
        System.out.println(adj);

        Set<Map.Entry<Integer, List<Integer>>> setMap = adj.entrySet();
        for (Map.Entry<Integer, List<Integer>> entry : setMap) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        for (String s : hmap.keySet()) {
            System.out.println(s + " " + hmap.get(s));
        }

        NavigableMap<Integer, String> treeMap = new TreeMap<>();
        // NavigableMap<Integer, String> treeMap = new TreeMap<>((a, b) -> b - a);//desc
        // order
        treeMap.put(1, "rahul");
        treeMap.put(2, "sahil");
        System.out.println(treeMap);
        System.out.println(treeMap.ceilingKey(0));

        System.out.println("*********************************************");

        // array sort
        Integer arr[] = new Integer[] { 1, 3, 5, 2, 4 };
        Arrays.sort(arr);
        for (int s : arr) {
            System.out.println(s);
        }

        // Collections static class

        // Collections.sort(arr, Collections.reverseOrder());

        // Arrays.asList(1,2,3)
    }
}
