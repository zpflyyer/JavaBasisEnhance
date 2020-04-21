package com.javabase.dsandalgo.tree.binary.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryWatch401 {

    public static void main(String[] args) {
        BinaryWatch401 watch401 = new BinaryWatch401();
        System.out.println(watch401.readBinaryWatch(1));
    }

    public List<String> readBinaryWatch(int num) {
        List<String> list = new ArrayList<>();
        this.readWatch(list, "", num);
        return list;
    }

    private void readWatch(List<String> res, String s, int remains) {
        if (remains > 0) {
            if (remains >= 10 - s.length()) {
                char[] remain = new char[10 - s.length()];
                Arrays.fill(remain, '1');
                s = s + new String(remain);
                this.readWatch(res, s, 0);
            } else {
                this.readWatch(res, s + "1", remains - 1);
                this.readWatch(res, s + "0", remains);
            }
        } else if (remains == 0) {
            if (s.length() < 10) {
                char[] remain = new char[10 - s.length()];
                Arrays.fill(remain, '0');
                s = s + new String(remain);
            }
            int hour = this.binaryString2Int(s.substring(0, 4));
            int minute = this.binaryString2Int(s.substring(4));
            if (hour <= 11 && minute <= 59) {
                res.add(hour + ":" + (minute >= 10 ? minute : "0" + minute));
            }
        }
    }

    private int binaryString2Int(String binaryStr) {
        int res = 0;
        for (int i = 0; i < binaryStr.length(); i++) {
            if (binaryStr.charAt(binaryStr.length() - 1 - i) == '1') {
                res += Math.pow(2, i);
            }
        }
        return res;
    }
}
