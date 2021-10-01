package com.bobocode;

import java.util.concurrent.RecursiveAction;

public class MergeSortTask extends RecursiveAction {
    private final int leftIndex, rightIndex;
    private final int[] sourceArray;


    public MergeSortTask(int[] sourceArray, int leftIndex, int rightIndex) {
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
        this.sourceArray = sourceArray;
    }

    /**
     * Divides array into halves until it can
     * Then merges those into sorted pieces
     * Until it merges whole array back
     */
    @Override
    protected void compute() {
        if (leftIndex < rightIndex) {
            // Find the middle point
            int middlePos = leftIndex + (rightIndex - leftIndex) / 2;

            // Recursive call to divide halves
            MergeSortTask left = new MergeSortTask(sourceArray, leftIndex, middlePos);
            MergeSortTask right = new MergeSortTask(sourceArray, middlePos + 1, rightIndex);
            invokeAll(left, right);

            // Merge the sorted halves
            merge(sourceArray, leftIndex, middlePos, rightIndex);
        }
    }
    /**
     * Merges left and right sorted subarrays of sourceArray.
     *
     * @param sourceArray array being sorted
     * @param leftIndex   start of left subarray
     * @param middlePos   index divider of subarrays
     * @param rightIndex  end of right subarray
     */
    static void merge(int[] sourceArray, int leftIndex, int middlePos, int rightIndex) {
        // Find sizes of two subarrays to be merged
        int leftSize = middlePos - leftIndex + 1;
        int rightSize = rightIndex - middlePos;

        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];

        // Copy data to temp arrays
        System.arraycopy(sourceArray, leftIndex, leftArr, 0, leftSize);
        System.arraycopy(sourceArray, middlePos + 1, rightArr, 0, rightSize);

        // Merge the temp arrays
        int i = 0, j = 0;
        int k = leftIndex; // Initial index of merged subarray array
        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                sourceArray[k] = leftArr[i];
                i++;
            } else {
                sourceArray[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArr if any left
        System.arraycopy(leftArr, i, sourceArray, k, leftSize - i);
        // Copy remaining elements of rightArr if any left
        k += leftSize - i;
        System.arraycopy(rightArr, j, sourceArray, k, rightSize - j);
    }
}
