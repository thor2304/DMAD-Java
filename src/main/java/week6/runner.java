package week6;

import week6.tools.Timing;

import java.util.*;

import static week6.runner.countCycles;
import static week6.runner.runLoops;

public class runner {
    public static List<Integer> permutationGenerator(int length) {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            out.add(i);
        }
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

    public static ArrayList<Integer> runLoops(int SIZE, int RUNS){
        ArrayList<Integer> counts = new ArrayList<>(Collections.nCopies(SIZE, 0));

        for (int i = 0; i < RUNS; i++) {
            int count = countCycles(permutationGenerator(SIZE));
            counts.set(count, counts.get(count) + 1);
        }

        return counts;
    }

    public static void statGenereator(int SIZE, int RUNS) {
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


    private static void threadedStatTest(int SIZE, int RUNS) {
        int numberOfThreads = 4; // Number of threads
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            countingThread thread
                    = new countingThread(SIZE, RUNS / numberOfThreads);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < CountHolder.counts.size(); i++) {
            int x = CountHolder.counts.get(i);
            System.out.printf("%d: %d - %d %s", i, x, Math.round(((double) x / RUNS) * 100), "%\n");
        }
    }

    public static void main(String[] args) {
//        testCounter();
        final int SIZE = 64;
        final int RUNS = 100_000;

        Timing.start("single thread");
        statGenereator(SIZE, RUNS);
        Timing.end("single thread");

        Timing.start("Multi thread");
        threadedStatTest(SIZE, RUNS);
        Timing.end("Multi thread");
    }
}

class CountHolder{
    public static ArrayList<Integer> counts = null;

    public static synchronized void addCounts(ArrayList<Integer> input){
        if (counts == null){
            counts = input;
            return;
        }
        for (int i = 0; i < counts.size(); i++) {
            int val = counts.get(i) + input.get(i);
            counts.set(i, val);
        }
    }
}

class countingThread extends Thread {
    private final int SIZE;
    private final int RUNS;
    private ArrayList<Integer> counts;

    public countingThread(int SIZE, int RUNS) {
        this.SIZE = SIZE;
        this.RUNS = RUNS;
    }

    public void run() {
        this.counts = runLoops(this.SIZE, this.RUNS);
        saveCounts();
    }

    private synchronized void saveCounts(){
        CountHolder.addCounts(this.counts);
    }
}

