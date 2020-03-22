package com.javabase.dsandalgo.tree.binary.leetcode.string;

public class DecryptStringFromAlphabetToIntegerMapping1309 {

    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
                sb.append((char) ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0') - 10 + 'j'));
                i += 2;
            } else {
                sb.append((char) (s.charAt(i) - '1' + 'a'));
            }
        }

        return sb.toString();
    }
}
