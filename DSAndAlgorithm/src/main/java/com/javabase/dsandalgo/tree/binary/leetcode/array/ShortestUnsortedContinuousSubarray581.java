package com.javabase.dsandalgo.tree.binary.leetcode.array;

public class ShortestUnsortedContinuousSubarray581 {

    public static void main(String[] args) {
        int[] input = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(new ShortestUnsortedContinuousSubarray581().findUnsortedSubarray(input));
    }

    public int findUnsortedSubarray(int[] nums) {
        int leftMost = nums.length;
        int rightMost = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                int pivot = nums[i];
                int j = i - 1;
                for (; j >= 0 && nums[j] > pivot; j--) {
                    nums[j + 1] = nums[j];
                }
                nums[j + 1] = pivot;
                leftMost = Math.min(j + 1, leftMost);
                rightMost = Math.max(i, rightMost);
            }
        }
        return Math.max(rightMost - leftMost + 1, 0);
    }

}
