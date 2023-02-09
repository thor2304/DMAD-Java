package week6;

import week6.tools.Timing;

import java.util.*;

public class runner {
    public static List<Integer> permutationGenerator(int length) {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            out.add(i);
        }
        Collections.shuffle(out);
        return out;
    }

    public static int countCycles(List<Integer> inputList){
        ArrayList<Integer> cycles = new ArrayList<>(Collections.nCopies(inputList.size(), 0));
        int numberOfCycles = 0;

        for (int i = 0; i < inputList.size(); i++) {
            int n = inputList.get(i);
//            System.out.println("n: " + n + ", i: " + i);

            if (cycles.get(i) == 1){
//                System.out.println("skipping n:" + n);
                continue;
            }

            while (cycles.get(n) != 1){
                cycles.set(n, 1);
                n = inputList.get(n);
//                System.out.println("while looping: n: " + n);
            }

//            System.out.println("increment");
            numberOfCycles++;
        }

        return numberOfCycles;
    }


    public static void statGenereator(){
        final int SIZE = 64;
        final int RUNS = 10_000_000;

        ArrayList<Integer> counts = new ArrayList<>(Collections.nCopies(SIZE, 0));

        for (int i = 0; i < RUNS; i++) {
            int count = countCycles(permutationGenerator(SIZE));
            counts.set(count, counts.get(count) + 1);
        }

        for (int i = 0; i < counts.size(); i++) {
            int x = counts.get(i);
            System.out.printf("%d: %d - %d %s", i, x, Math.round(((double) x / RUNS) * 100), "%\n");
        }
    }

    private static void testCounter() {
        List<Integer> p = permutationGenerator(10);
        System.out.println(p);

        int c = countCycles(p);
        System.out.println(c);
    }

    public static void main(String[] args) {
//        testCounter();
        Timing.start();
        statGenereator();
        Timing.end();
    }


}


