package com.bobocode;

import java.util.concurrent.ForkJoinPool;

public class Main {
    static int[] sourceArray;

    public static void main(String[] args) {
        sourceArray = Utils.generateRandomArray(10);
        long start = System.nanoTime();
        Utils.mergeSort(sourceArray, 0, sourceArray.length - 1);
        System.out.printf("Simple time: \t%d ns\n", System.nanoTime() - start);

        int[] ordinarySorted = new int[sourceArray.length];
        System.arraycopy(sourceArray, 0, ordinarySorted, 0, sourceArray.length);

        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        start = System.nanoTime();
        commonPool.invoke(new MergeSortTask(sourceArray, 0, sourceArray.length - 1));
        System.out.printf("ForkJoin time: \t%d ns\n", System.nanoTime() - start);

        if (Utils.isEqual(ordinarySorted, sourceArray)) {
            System.out.println("Success! ForkJoin sorted == Ordinary sorted");
        }else{
            System.out.println("Error! ForkJoin sorted != Ordinary sorted");
        }
    }
}
