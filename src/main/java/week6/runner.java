package week6;

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
        ArrayList<Integer> cycles = new ArrayList<Integer>(Collections.nCopies(inputList.size(), 0));
        int numberOfCycles = 0;

        for (int i = 0; i < inputList.size(); i++) {
            int n = inputList.get(i);
            System.out.println("n: " + n + ", i: " + i);

            if (cycles.get(i) == 1){
                System.out.println("skipping n:" + n);
                continue;
            }

            while (cycles.get(n) != 1){
                cycles.set(n, 1);
                n = inputList.get(n);
                System.out.println("while looping: n: " + n);
            }

            System.out.println("increment");
            numberOfCycles++;
        }

        return numberOfCycles;
    }


    public static void statGenereator(){
        final int SIZE = 64;
        final int RUNS = 100_000;

        ArrayList<Integer> count = new ArrayList<Integer>(Collections.nCopies(SIZE, 0));


    }

    public static void main(String[] args) {
        List<Integer> p = permutationGenerator(10);
        System.out.println(p);

        int c = countCycles(p);
        System.out.println(c);


    }


}


