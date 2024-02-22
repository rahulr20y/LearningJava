package com.practice.learningjava.dsa;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Learning DSA in java");
        List<Integer> list = new ArrayList<>();
        list.add(27);
        list.add(23);
        for (int i = 0; i < list.size(); i++) {
            list.add(i);
            if (i == 1) {
                list.remove(i);
                list.add(55);
            }
        }
        System.out.println(list);
    }
}
