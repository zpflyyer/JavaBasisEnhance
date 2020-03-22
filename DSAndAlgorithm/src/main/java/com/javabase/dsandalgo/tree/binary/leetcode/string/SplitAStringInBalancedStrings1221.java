package com.javabase.dsandalgo.tree.binary.leetcode.string;

public class SplitAStringInBalancedStrings1221 {

    public static void main(String[] args) {

    }

    public int balancedStringSplit(String s) {

        int left = 0;
        int right = 0;
        int balancedCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                left = right = 0;
                balancedCount++;
            }
        }

        return balancedCount;
    }
}
