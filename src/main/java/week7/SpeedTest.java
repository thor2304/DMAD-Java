package week7;

import tools.Timing;
import week6.Runner;

import java.util.ArrayList;
import java.util.List;

import static week7.TaskA3.insertionSort;

public class SpeedTest {
    public static void main(String[] args) {
        int listSize = 10_000;
        speedTestInsertionSort(listSize);
        speedTestMergeSort(listSize);
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

    }
}
