package com.javabase.dsandalgo.tree.binary.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class TheKthLexicographicalString1415 {

    public static void main(String[] args) {
        TheKthLexicographicalString1415 kthLexicographicalString1415 = new TheKthLexicographicalString1415();
        System.out.println(kthLexicographicalString1415.getHappyString(4, 9));
    }

    private int count = 0;
    private int n = 0;
    private int k = 0;
    private String s = "";

    public String getHappyString(int n, int k) {
        if (k <= 3 * Math.pow(2, n - 1)) {
            this.n = n;
            this.k = k;
            this.dfs("");
        }
        return this.s;
    }

    private void dfs(String s) {
        if (s.length() == this.n) {
            this.count++;
            if (this.count == this.k) {
                this.s = s;
            }
        } else {
            List<Character> lists = new ArrayList<>();
            lists.add('a');
            lists.add('b');
            lists.add('c');
            if (!s.isEmpty()) {
                lists.remove(Character.valueOf(s.charAt(s.length() - 1)));
            }
            for (Character c : lists) {
                this.dfs(s + c);
            }
        }
    }

}
