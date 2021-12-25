import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.lang.System;

public class Hw4_p6 {
    /**
     * Creates HashMap, ArrayList, and LinkedList;
     * generates 100,000 distinct random integers and inserts integers in HashMap,
     * ArrayList, and LinkedList, respectively, and calculates total insertion
     * times; generates another 100,000 distinct random integers and searches for
     * integers in HashMap, ArrayList, and LinkedList and calculates total search
     * times. Repeats this 10 times. And calculates and prints total averages.
     * 
     * @param args command-line args as array of String objects.
     * @return no return.
     */
    public static void main(String[] args) {
        // Declares data structures.
        HashMap<Integer, Integer> myMap;
        ArrayList<Integer> myArrayList;
        LinkedList<Integer> myLinkedList;
        // Declares variables.
        long startTime, endTime, elapsedTime;
        int[] insertKeys;
        int[] searchKeys;
        // Initializes variables.
        float hashMapAvgTotalInsertTime = .0F;
        float arrayListAvgTotalInsertTime = .0F;
        float linkedListAvgTotalInsertTime = .0F;
        float hashMapAvgTotalSearchTime = .0F;
        float arrayListAvgTotalSearchTime = .0F;
        float linkedListAvgTotalSearchTime = .0F;
        final int REPEATNTIMES = 10;
        final int INSERTKEYSSIZE = 100000;
        final int SEARCHKEYSSIZE = INSERTKEYSSIZE;

        // Repeats 10 times:
        // calculates total insertion times and total search times for HashMap,
        // ArrayList, and LinkedList.
        for (int index = 0; index < REPEATNTIMES; index++) {
            // Generates 100,000 distinct random integers in range [1, 1,000,000] with
            // currentTime as seed and stores them in insertKeys array.
            insertKeys = new Random(System.currentTimeMillis()).ints(1, 1000000 + 1).distinct().limit(INSERTKEYSSIZE)
                    .toArray();

            // Creates empty myHap, myArrayList, and myLinkedList.
            myMap = new HashMap<>();
            myArrayList = new ArrayList<>();
            myLinkedList = new LinkedList<>();

            // Inserts keys.
            // Inserts all integers in insertKeys array into myMap
            // and measures total insert time.
            startTime = System.currentTimeMillis();
            for (int i = 0; i < INSERTKEYSSIZE; i++) {
                myMap.put(insertKeys[i], i);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            hashMapAvgTotalInsertTime += elapsedTime;

            // Inserts all integers in insertKeys array into myArrayList
            // and measures total insert time.
            startTime = System.currentTimeMillis();
            for (int i = 0; i < INSERTKEYSSIZE; i++) {
                myArrayList.add(insertKeys[i]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            arrayListAvgTotalInsertTime += elapsedTime;

            // Inserts all integers in insertKeys array into myLinkedList
            // and measures total insert time.
            startTime = System.currentTimeMillis();
            for (int i = 0; i < INSERTKEYSSIZE; i++) {
                myLinkedList.add(insertKeys[i]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            linkedListAvgTotalInsertTime += elapsedTime;

            // Generates 100,000 distinct random integers in range [1, 2,000,000] with
            // currentTime as seed and stores them in searchKeys array.
            searchKeys = new Random(System.currentTimeMillis()).ints(1, 2000000 + 1).distinct().limit(SEARCHKEYSSIZE)
                    .toArray();

            // Searches keys.
            // Searches all integers in searchKeys array in myMap
            // and measures total search time.
            startTime = System.currentTimeMillis();
            for (int i = 0; i < SEARCHKEYSSIZE; i++) {
                myMap.containsKey(searchKeys[i]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            hashMapAvgTotalSearchTime += elapsedTime;

            // Searches all integers in searchKeys array in myArrayList
            // and measures total search time.
            startTime = System.currentTimeMillis();
            for (int i = 0; i < SEARCHKEYSSIZE; i++) {
                myArrayList.contains(searchKeys[i]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            arrayListAvgTotalSearchTime += elapsedTime;

            // Searches all integers in searchKeys array in myLinkedList
            // and measures total search time.
            startTime = System.currentTimeMillis();
            for (int i = 0; i < SEARCHKEYSSIZE; i++) {
                myLinkedList.contains(searchKeys[i]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            linkedListAvgTotalSearchTime += elapsedTime;
        }

        // Prints number of keys - and average total insert times and search times.
        System.out.println("Number of keys = " + INSERTKEYSSIZE);
        System.out.println("");
        System.out.println("HashMap average total insert time = " + hashMapAvgTotalInsertTime / REPEATNTIMES);
        System.out.println("ArrayList average total insert time = " + arrayListAvgTotalInsertTime / REPEATNTIMES);
        System.out.println("LinkedList average total insert time = " + linkedListAvgTotalInsertTime / REPEATNTIMES);
        System.out.println("");
        System.out.println("HashMap average total search time = " + hashMapAvgTotalSearchTime / REPEATNTIMES);
        System.out.println("ArrayList average total search time = " + arrayListAvgTotalSearchTime / REPEATNTIMES);
        System.out.println("LinkedList average total search time = " + linkedListAvgTotalSearchTime / REPEATNTIMES);
    }

}