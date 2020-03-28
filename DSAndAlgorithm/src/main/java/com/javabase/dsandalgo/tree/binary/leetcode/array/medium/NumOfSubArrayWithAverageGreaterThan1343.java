package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

public class NumOfSubArrayWithAverageGreaterThan1343 {

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum = 0;
        int num = 0;
        threshold = k * threshold;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (i > k - 1) {
                sum = sum - arr[i - k];
            }
            if (i >= k - 1) {
                if (sum >= threshold) {
                    num++;
                }
            }
        }
        return num;
    }
}
