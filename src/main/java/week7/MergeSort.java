package week7;

import week6.Runner;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        int listSize = 100;
        List<Integer> testList = Runner.permutationGenerator(listSize);
        System.out.println(testList);

        List<Integer> sortedList = mergeSort(testList);

        System.out.println(sortedList);
    }

    public static List<Integer> mergeSort(List<Integer> inputList) {
        return mergeSplitterListDriven(inputList);
    }

    public static Integer[] mergeSortUsingArray(Integer[] inputList){
        return mergeSplitterArrayDriven(inputList);
    }

    /**
     * Assumes that the two inputs are sorted
     *
     * @param inputListA Sorted list
     * @param inputListB Sorted List
     * @return A sorted list that contains the elements from both lists
     */
    private static List<Integer> mergeCombinerListDriven(List<Integer> inputListA, List<Integer> inputListB) {
        int indexA = 0, indexB = 0;
        ArrayList<Integer> output = new ArrayList<>();


        while (indexA < inputListA.size() && indexB < inputListB.size()){
            int a = inputListA.get(indexA);
            int b = inputListB.get(indexB);

            if(a < b){
                indexA++;
                output.add(a);
            }else {
                indexB++;
                output.add(b);
            }
        }

        if (indexA < inputListA.size()){
            output.addAll(
                    inputListA.subList(indexA, inputListA.size())
            );
        }else if (indexB < inputListB.size()){
            output.addAll(inputListB.subList(indexB, inputListB.size()));
        }

        return output;
    }

    private static List<Integer> mergeSplitterListDriven(List<Integer> inputList) {
        if (inputList.size() > 2) {
            int size = inputList.size();
            int half = size / 2;
            List<Integer> listA = inputList.subList(0, half);
            List<Integer> listB = inputList.subList(half, size);

            return mergeCombinerListDriven(
                    mergeSplitterListDriven(listA), mergeSplitterListDriven(listB)
            );
        }else if(inputList.size() == 2){
            return mergeCombinerListDriven(inputList.subList(0, 1), inputList.subList(1,2));
        }else {
            return inputList;
        }
    }

    private static Integer[] mergeCombinerArrayDriven(Integer[] inputArrayA, Integer[] inputArrayB){
        int indexA = 0, indexB = 0, outputIndex = 0;

        Integer[] output = new Integer[inputArrayA.length + inputArrayB.length];

        while (indexA < inputArrayA.length && indexB < inputArrayB.length){
            int a = inputArrayA[indexA];
            int b = inputArrayB[indexB];

            if (a < b){
                indexA++;
                output[outputIndex++] = a;
            }else {
                indexB++;
                output[outputIndex++] = b;
            }
        }

        if (indexA < inputArrayA.length){
            outputIndex = addToArray(output, outputIndex, inputArrayA, indexA);
        }else if(indexB < inputArrayB.length){
            outputIndex = addToArray(output, outputIndex, inputArrayB, indexB);
        }

        return output;
    }

    /**
     *
     * @param addTo
     * @param addToIndex
     * @param addFrom
     * @param addFromIndex
     * @return The new value of output index, addToIndex
     */
    private static int addToArray(Integer[] addTo, int addToIndex, Integer[] addFrom, int addFromIndex){
        while (addFromIndex < addTo.length && addFromIndex < addFrom.length){
            addTo[addToIndex++] = addFrom[addFromIndex++];
        }

        return addToIndex;
    }

    private static Integer[] mergeSplitterArrayDriven(Integer[] inputArray){
        if (inputArray.length > 2){
            int half = inputArray.length / 2;
            Integer[] listA = new Integer[half];
            Integer[] listB = new Integer[inputArray.length - half];
            System.arraycopy(inputArray, 0, listA, 0, half);
            System.arraycopy(inputArray, half, listB, 0, inputArray.length - half);

            return mergeCombinerArrayDriven(
                    mergeSplitterArrayDriven(listA), mergeSplitterArrayDriven(listB)
            );
        }else if (inputArray.length == 2){
            return mergeCombinerArrayDriven(new Integer[]{inputArray[0]}, new Integer[]{inputArray[1]});
        }else {
            return inputArray;
        }

    }
}
