package com.javabase.dsandalgo.tree.binary.leetcode.binarysearch;

public class SearchInRotatedSortedArrayII81 {

    public static void main(String[] args) {

    }

    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (right >= left) {
            mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return true;
            }

            // can't judge whether left is in order
            if (nums[mid] == nums[left]) {
                left++;
                continue;
            }

            // left is in order
            if (nums[mid] > nums[left]) {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // right is in order
            else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}
