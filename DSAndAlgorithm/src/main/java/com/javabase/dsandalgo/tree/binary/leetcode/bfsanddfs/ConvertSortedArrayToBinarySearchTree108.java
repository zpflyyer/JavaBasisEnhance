package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.Arrays;

public class ConvertSortedArrayToBinarySearchTree108 {

    public static void main(String[] args) {
        int[] array = {-10, -3, 0, 5, 9};
        System.out.println(new ConvertSortedArrayToBinarySearchTree108().sortedArrayToBST(array));
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (mid - 1 >= 0) {
            root.left = this.sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        }
        if (mid + 1 <= nums.length - 1) {
            root.right = this.sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, nums.length));
        }
        return root;
    }
}
