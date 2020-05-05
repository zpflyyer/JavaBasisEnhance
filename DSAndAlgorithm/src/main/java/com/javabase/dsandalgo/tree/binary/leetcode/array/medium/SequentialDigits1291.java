package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.ArrayList;
import java.util.List;

public class SequentialDigits1291 {

    public static void main(String[] args) {
        SequentialDigits1291 sequentialDigits1291 = new SequentialDigits1291();
        System.out.println(sequentialDigits1291.sequentialDigits(1000, 13000));
    }

    public List<Integer> sequentialDigits(int low, int high) {
        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        List<Integer> res = new ArrayList<>();
        for (int len = lowLen; len <= highLen; len++) {
            for (int startDigit = 1; startDigit <= 10 - len; startDigit++) {
                int sequentialDigit = this.getSequentialDigit(len, startDigit);
                if (low <= sequentialDigit && sequentialDigit <= high) {
                    res.add(sequentialDigit);
                }
            }
        }
        return res;
    }

    private int getSequentialDigit(int len, int startDigit) {
        int res = startDigit;
        for (int i = 1; i < len; i++) {
            res = res * 10 + (res % 10 + 1);
        }
        return res;
    }
}
