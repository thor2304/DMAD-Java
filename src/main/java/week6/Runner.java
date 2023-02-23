package week6;

import tools.Timing;

import java.util.*;


public class Runner {

    public static List<Integer> generateList(int length){
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            out.add(i);
        }
        return out;
    }
    public static List<Integer> permutationGenerator(int length) {
        List<Integer> out = generateList(length);
        Collections.shuffle(out);
        return out;
    }

    public static int countCycles(List<Integer> inputList) {
        ArrayList<Integer> cycles = new ArrayList<>(Collections.nCopies(inputList.size(), 0));
        int numberOfCycles = 0;

        for (int i = 0; i < inputList.size(); i++) {
            int n = inputList.get(i);
//            System.out.println("n: " + n + ", i: " + i);

            if (cycles.get(i) == 1) {
//                System.out.println("skipping n:" + n);
                continue;
            }

            while (cycles.get(n) != 1) {
                cycles.set(n, 1);
                n = inputList.get(n);
//                System.out.println("while looping: n: " + n);
            }

//            System.out.println("increment");
            numberOfCycles++;
        }

        return numberOfCycles;
    }

    public static ArrayList<Integer> runLoops(int SIZE, int RUNS) {
        ArrayList<Integer> counts = new ArrayList<>(Collections.nCopies(SIZE, 0));

        for (int i = 0; i < RUNS; i++) {
            int count = countCycles(permutationGenerator(SIZE));
            counts.set(count, counts.get(count) + 1);
        }

        return counts;
    }

    public static void statGenerator(int SIZE, int RUNS) {
        ArrayList<Integer> counts = runLoops(SIZE, RUNS);

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
        final int SIZE = 64;
        final int RUNS = 10_000_000;

        Timing.start("single thread");
        statGenerator(SIZE, RUNS);
        Timing.end("single thread");
    }
}


