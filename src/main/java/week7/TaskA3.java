package week7;

import tools.Timing;
import week6.Runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskA3 {
    public static void main(String[] args) {
        int listSize = 100;
        List<Integer> testList = Runner.permutationGenerator(listSize);
        System.out.println(testList);

        List<Integer> sortedList = insertionSort(testList);

        System.out.println(sortedList);
    }

    public static List<Integer> insertionSort(List<Integer> inputList){
        ArrayList<Integer> myList = new ArrayList<>(inputList);

        for (int i = 1; i < myList.size(); i++) {
             int key = myList.get(i);

             // the values up to i, must be sorted
            // find the place where this value fits into the sorted sub array
            int j = i -1;
            while (j >= 0 && myList.get(j) > key){
                myList.set(j + 1, myList.get(j));
                j--;
            }

            myList.set(j + 1, key);
        }

        return myList;
    }
}
