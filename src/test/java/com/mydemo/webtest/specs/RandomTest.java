package com.mydemo.webtest.specs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RandomTest {

    public static void main(String[] argv) {
        int[][] parentChildPairs = new int[][]{
                {1, 3}, {2, 3}, {3, 6}, {5, 6}, {15, 9}, {5, 7},
                {4, 5}, {4, 8}, {4, 9}, {9, 11}
        };

        findNodesWithZeroAndOneParents(parentChildPairs);
    }

    private static ArrayList<Integer> getParentList(int[][] pairs) {
        ArrayList<Integer> myList = new ArrayList<Integer>();
        for (int[] numbers : pairs) {
            myList.add(numbers[0]);
        }
        return myList;
    }

    private static ArrayList<Integer> getChildList(int[][] pairs) {
        ArrayList<Integer> myList = new ArrayList<Integer>();
        for (int[] numbers : pairs) {
            myList.add(numbers[1]);
        }
        return myList;
    }

    private static void parseParentList(ArrayList<Integer> parentList, ArrayList<Integer> childList) {
        ArrayList<Integer> myNewList = new ArrayList<Integer>();

        for (int num : parentList) {
            if (!childList.contains(num)) {
                if (!myNewList.contains(num)) {
                    myNewList.add(num);
                }
            }
        }
        System.out.println("Individuals with zero parents => " + myNewList.toString());
    }

    private static void parseChildList(ArrayList<Integer> list) {
        ArrayList<Integer> myNewList = new ArrayList<Integer>();
        HashMap<Integer, Integer> myMap = new HashMap<Integer, Integer>();
        for (int num : list) {
            if (myMap.containsKey(num)) {
                myMap.put(num, 1);
            }
            if (!myMap.containsKey(num)) {
                myMap.put(num, 0);
            }
        }

        for (Map.Entry m : myMap.entrySet()) {
            if (m.getValue().equals(0)) {
                myNewList.add((Integer) m.getKey());
            }
        }
        System.out.println("Individuals with exactly one parent => " + myNewList.toString());
    }

    private static void findNodesWithZeroAndOneParents(int[][] parentChildPairs) {
        ArrayList<Integer> parents = getParentList(parentChildPairs);
        ArrayList<Integer> children = getChildList(parentChildPairs);
        parseParentList(parents, children);
        parseChildList(children);
    }
}
