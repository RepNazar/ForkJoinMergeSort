package com.bobocode;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    // Main function that sorts array[leftIndex...rightIndex] using merge()
    static void mergeSort(int[] sourceArray, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            // Find the middle point
            int middlePos = leftIndex + (rightIndex - leftIndex) / 2;

            // Sort first and second halves
            mergeSort(sourceArray, leftIndex, middlePos);
            mergeSort(sourceArray, middlePos + 1, rightIndex);

            // Merge the sorted halves
            MergeSortTask.merge(sourceArray, leftIndex, middlePos, rightIndex);
        }
    }
    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] generateRandomArray(int length) {
        return ThreadLocalRandom.current().ints(length).toArray();
    }

    public static int[] generateRandomArray(int origin, int bound, int length) {
        return ThreadLocalRandom.current().ints(length, origin, bound).toArray();
    }
}
