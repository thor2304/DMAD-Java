package week7;

import tools.Timing;
import week6.Runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static week7.TaskA3.insertionSort;

public class TaskB1 {
    public static void main(String[] args) {
        int listSize = 10_000;
//        int listSize = 10_000;
        List<Integer> testList = Runner.permutationGenerator(listSize);

//        System.out.println(testList);
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
        }
        System.out.printf("Average time of %s was %d %s", name1, time / loops, "\n\n");


        String name2 = "Insertion sort on sorted list";
        time = 0;
        for (int i = 0; i < loops; i++) {
            Timing.start(name2);
            sortedList = insertionSort(sortedList);
            Timing.end(name2);
            time += Timing.getTime(name2);
        }
        System.out.printf("Average time of %s was %d %s", name2, time / loops, "\n\n");



        String name3 = "Insertion sort on reverse sorted list";
        time = 0;
        for (int i = 0; i < loops; i++) {
            Collections.reverse(sortedList);
//            System.out.println(sortedList);
            Timing.start(name3);
            sortedList = insertionSort(sortedList);
            Timing.end(name3);
            time += Timing.getTime(name3);
        }
        System.out.printf("Average time of %s was %d %s", name3, time / loops, "\n\n");


//        System.out.println(thirdList);
    }
}
