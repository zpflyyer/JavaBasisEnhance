package com.javabase.dsandalgo.tree.binary.leetcode.random.ez;

public class DetectCapital {

    public static void main(String[] args) {
        DetectCapital dc = new DetectCapital();
        System.out.println(dc.detectCapitalUse("FlaG"));
    }

    public boolean detectCapitalUse(String word) {
        // if start with lower case
        if ('a' <= word.charAt(0)) {
            for (int i = 1; i < word.length(); i++) {
                // then all lower case
                if (word.charAt(i) < 'a') {
                    return false;
                }
            }
        }
        // if start with upper case
        else {
            for (int i = 2; i < word.length(); i++) {
                if (word.charAt(i) >= 'a') {
                    if (word.charAt(1) < 'a') {
                        return false;
                    }
                } else {
                    if (word.charAt(1) >= 'a') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
