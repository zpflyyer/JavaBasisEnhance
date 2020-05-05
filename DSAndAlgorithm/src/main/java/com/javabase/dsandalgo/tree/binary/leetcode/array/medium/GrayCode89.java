package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.ArrayList;
import java.util.List;

public class GrayCode89 {

    public static void main(String[] args) {
        GrayCode89 grayCode89 = new GrayCode89();
        System.out.println(grayCode89.grayCode(4));
    }

    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int power = 0; power < n; power++) {
            int to = (int) Math.pow(2, power) - 1;
            int plus = (int) Math.pow(2, power);
            for (int idx = 0; idx <= to; idx++) {
                res.add(res.get(to - idx) + plus);
            }
        }
        return res;
    }
}
