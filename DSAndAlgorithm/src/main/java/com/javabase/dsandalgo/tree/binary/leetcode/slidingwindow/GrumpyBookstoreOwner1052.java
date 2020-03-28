package com.javabase.dsandalgo.tree.binary.leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GrumpyBookstoreOwner1052 {

    public static void main(String[] args) {
        int[] customer = {9, 10, 4, 5};
        int[] grumpy = {1, 0, 1, 1};
        GrumpyBookstoreOwner1052 grumpyBookstoreOwner1052 = new GrumpyBookstoreOwner1052();
        System.out.println(grumpyBookstoreOwner1052.maxSatisfied(customer, grumpy, 1));
    }

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        List<Integer> grumpIdx = new ArrayList<>();
        for (int i = 0; i < grumpy.length; i++) {
            if (grumpy[i] == 1) {
                grumpIdx.add(i);
            }
        }

        int maxUnsatisfied = 0;
        int curUnsatisfied = 0;
        int startX = 0;
        Queue<Integer> unsatisfiedQueue = new LinkedList<>();
        for (Integer idx : grumpIdx) {
            while (!unsatisfiedQueue.isEmpty() && idx > unsatisfiedQueue.element() + X - 1) {
                curUnsatisfied -= customers[unsatisfiedQueue.remove()];
            }
            unsatisfiedQueue.add(idx);
            curUnsatisfied += customers[idx];

            if (curUnsatisfied > maxUnsatisfied) {
                startX = idx - X + 1;
                maxUnsatisfied = curUnsatisfied;
            }
        }

        int result = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0 || i >= startX && i <= startX + X - 1) {
                result += customers[i];
            }
        }

        return result;
    }
}
