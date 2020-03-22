package com.javabase.dsandalgo.tree.binary.leetcode.string;

import java.util.Arrays;

public class GenerateAStringWithCharactersThatHaveOddCounts1374 {

    public static void main(String[] args) {

    }

    public String generateTheString(int n) {
        char[] chars = new char[n];
        Arrays.fill(chars, 'a');
        chars[n - 1] = n % 2 == 1 ? 'a' : 'b';
        return new String(chars);
    }
}
