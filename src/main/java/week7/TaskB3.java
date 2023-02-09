package week7;

import week6.Runner;

import java.util.List;

public class TaskB3 {
    public static void main(String[] args) {
        List<Integer> list = Runner.generateList(100);

        System.out.println(list);
        System.out.println(binarySearch(73, list));

    }

    /** Performs binary search for the given value in the input list
     *
     * @param value The value that will be searched for in the list
     * @param list Must be a sorted list
     * @return -1 if the value was not found, the index of the first occurence otherwise
     */
    public static int binarySearch(int value, List<Integer> list){
        return binaryDriver(value, list, 0, list.size()-1);
    }

    private static int binaryDriver(int value, List<Integer> list, int lower, int upper){
        if (list.size() < 1){
            return -1;
        }
        if (list.size() < 2 && list.get(0) != value){
            return -1;
        }

        int halfway = list.size() / 2;
        int trueHalfwayIndex = lower + halfway;
        int halfwayValue = list.get(halfway);

        System.out.println(halfway);
        System.out.println(halfwayValue);
        System.out.println(list);
        System.out.println(upper);
        System.out.println(lower);
        System.out.println(trueHalfwayIndex);
        System.out.println();

        if (value > halfwayValue){
            return binaryDriver(value, list.subList(halfway, list.size()), trueHalfwayIndex, upper);
        }

        if (value < halfwayValue){
            return binaryDriver(value, list.subList(0, halfway), lower, trueHalfwayIndex);
        }

        return trueHalfwayIndex;
    }
}
