package com.practice.learningjava.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Pair {
    public String key;
    public int value;

    public Pair(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {

    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair [key=" + key + ", value=" + value + "]";
    }

}

public class Morgan {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Pineapple", "Apple", "Mango", "Apple", "Pineapple", "Mango", "Mango",
                "Banana");
        Map<String, Integer> h = new HashMap<>();
        for (String str : list) {
            if (h.containsKey(str)) {
                int val = h.get(str);
                h.put(str, val + 1);
            } else {
                h.put(str, 1);
            }
        }
        List<Pair> newList = new ArrayList<>();
        for (String key : h.keySet()) {
            Pair p = new Pair(key, h.get(key));
            p.key = key;
            p.value = h.get(key);
            newList.add(p);
        }

        // print Array list
        for (Pair p : newList) {
            System.out.println(p);
        }

        // sort collection using collection object
        newList.sort((a, b) -> {
            if (a.getValue() == b.getValue()) {
                return (a.getKey().compareTo(b.getKey()));
            }
            return -1 * (a.getValue() - b.getValue());
        });

        // Collections reverse
        Collections.reverse(newList);

        // Using factory class List Collections sorting
        Collections.sort(newList, (a, b) -> {
            if (a.getValue() == b.getValue()) {
                return a.getKey().compareTo(b.getKey());
            }
            return -1 * (a.getValue() - b.getValue());
        });

        System.out.println(newList);

        // Traditional Array
        Pair[] arrPair = new Pair[newList.size()];
        Pair arrPair2[] = new Pair[newList.size()];
        newList.toArray(arrPair);

        // traditional Array sort
        Arrays.sort(arrPair, (a, b) -> {
            if (a.getValue() == b.getValue()) {
                return a.getKey().compareTo(b.getKey());
            }
            return -1 * (a.getValue() - b.getValue());
        });

        // Traversing traditional array
        for (Pair p : arrPair) {
            System.out.println(p);
        }

        // Array to Array List conversion
        List<Pair> newList2 = Arrays.asList(arrPair);
        System.out.println(newList2);

        // Array initialization in c++ as well as java
        int myArray[] = { 0, 1, 2, 3 };
        for (int i = 0; i < myArray.length; i++) {
            System.out.println(myArray[i]);
        }
        for (int a : myArray) {
            System.out.println(a);
        }

        // List initalization
        List<Integer> list2 = new ArrayList<>(Arrays.asList(7, 8, 9));
        for (int i = 0; i < list2.size(); i++) {
            System.out.println(list2.get(i));
        }
        for (int a : list2) {
            System.out.println(a);
        }

        List<Integer> list3 = new ArrayList<>(List.of(10, 11, 12));
        System.out.println(list3);

    }
}
