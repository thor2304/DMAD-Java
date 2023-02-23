package week7;

import tools.Timing;
import week6.Runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static week7.TaskA3.insertionSort;

public class SpeedTest {
    public static void main(String[] args) {
        int listSize = 100_000;
        speedTestMergeSort(listSize);
        speedTestInsertionSort(listSize);
    }

    public static void speedTestInsertionSort(int listSize){
        List<Integer> testList = Runner.permutationGenerator(listSize);

        String name1 = "Insertion sort random list";
        List<Integer> sortedList = new ArrayList<>();

        int loops = 10;
        long time = 0;

        for (int i = 0; i < loops; i++) {
//            System.out.println(testList);
            Timing.start(name1);
            sortedList = insertionSort(testList);
            Timing.end(name1);
            time += Timing.getTime(name1);
            testList = Runner.permutationGenerator(listSize);
        }
        System.out.printf("Average time of %s was %d %s", name1, time / loops, "\n\n");
    }


    public static void speedTestMergeSort(int listSize){
        List<Integer> testList = Runner.permutationGenerator(listSize);

        int loops = 100;
        long time = 0;

        String name2 = "Merge sort random arrayDriven";
        Integer[] sortedArray = new Integer[listSize];

        time = 0;

        Integer[] testArray = testList.toArray(new Integer[0]);

        sortedArray = MergeSort.mergeSortUsingArray(testArray);
        sortedArray = MergeSort.mergeSortUsingArray(testArray);

        for (int i = 0; i < loops; i++) {
//            System.out.println(testList);
            Timing.start(name2);
            sortedArray = MergeSort.mergeSortUsingArray(testArray);
            Timing.end(name2);
            time += Timing.getTime(name2);
            testList = Runner.permutationGenerator(listSize);
            testArray = testList.toArray(new Integer[0]);
        }
        System.out.printf("Average time of %s was %d %s", name2, time / loops, "\n\n");

        String name3 = "Merge sort random listDriven";
        List<Integer> sortedList = new ArrayList<>();

        sortedList = MergeSort.mergeSort(testList);
        sortedList = MergeSort.mergeSort(testList);

        for (int i = 0; i < loops; i++) {
//            System.out.println(testList);
            Timing.start(name3);
            sortedList = MergeSort.mergeSort(testList);
            Timing.end(name3);
            time += Timing.getTime(name3);
            testList = Runner.permutationGenerator(listSize);
        }
        System.out.printf("Average time of %s was %d %s", name3, time / loops, "\n\n");
    }
}
