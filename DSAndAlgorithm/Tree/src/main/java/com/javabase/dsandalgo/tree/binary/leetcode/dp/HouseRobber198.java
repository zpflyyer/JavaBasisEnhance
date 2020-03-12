package com.javabase.dsandalgo.tree.binary.leetcode.dp;

public class HouseRobber198 {

    public static void main(String[] args) {
        int[] array = {2, 7, 9, 3, 1};
        System.out.println(new HouseRobber198().rob(array));
    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else {
            int prev1 = 0;
            int prev2 = nums[0];
            for (int i = 1; i < nums.length; i++) {
                int temp = prev2;
                prev2 = Math.max(prev1 + nums[i], prev2);
                prev1 = temp;
            }
            return prev2;
        }
    }

}
