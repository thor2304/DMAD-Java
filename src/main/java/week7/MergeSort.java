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
}
