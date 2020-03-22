package com.javabase.dsandalgo.tree.binary.leetcode.string;

public class IncreasingDecreasingString {

    public static void main(String[] args) {

    }

    public String sortString(String s) {
        int[] freq = new int[26];
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        while (count < s.length()) {
            for (int i = 0; i < freq.length; i++) {
                if (freq[i] > 0) {
                    sb.append((char) (i + 'a'));
                    freq[i]--;
                    count++;
                }
            }

            for (int i = freq.length - 1; i >= 0; i--) {
                if (freq[i] > 0) {
                    sb.append((char) (i + 'a'));
                    freq[i]--;
                    count++;
                }
            }
        }

        return sb.toString();
    }
}
