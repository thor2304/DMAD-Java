package week6.tools;

import java.util.HashMap;

public class Timing {
    public static final String DEFAULT_BLOCK_NAME = "the block";

    private static final HashMap<String, Long> startTimes = new HashMap<>();

    public static void start() {
        start(DEFAULT_BLOCK_NAME);
    }

    public static void end() {
        end(DEFAULT_BLOCK_NAME);
    }

    public static void start(String name){
        long startTime = System.currentTimeMillis();
        startTimes.put(name, startTime);
    }

    public static void end(String name) {
        long endTime = System.currentTimeMillis();
        long startTime = startTimes.get(name);

        System.out.printf("The execution time of %s was %d milliseconds\n\n", name, endTime - startTime);
    }
}
