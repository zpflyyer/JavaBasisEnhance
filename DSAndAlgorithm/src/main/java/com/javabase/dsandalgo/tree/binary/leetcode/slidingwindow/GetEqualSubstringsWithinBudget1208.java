package com.javabase.dsandalgo.tree.binary.leetcode.slidingwindow;

public class GetEqualSubstringsWithinBudget1208 {
    public static void main(String[] args) {

    }

    public int equalSubstring(String s, String t, int maxCost) {
        int j = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            maxCost -= Math.abs(s.charAt(i) - t.charAt(i));
            while (maxCost < 0) {
                maxCost += Math.abs(s.charAt(j) - t.charAt(j));
                j++;
            }
            maxLen = Math.max(maxLen, i - j + 1);
        }
        return maxLen;
    }
}
