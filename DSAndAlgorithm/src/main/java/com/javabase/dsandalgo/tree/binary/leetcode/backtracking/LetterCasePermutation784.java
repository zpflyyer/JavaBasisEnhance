package com.javabase.dsandalgo.tree.binary.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LetterCasePermutation784 {
    public static void main(String[] args) {
        LetterCasePermutation784 permutation784 = new LetterCasePermutation784();
        System.out.println(permutation784.letterCasePermutation("a1b2"));
    }

    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        this.dfsPermutation(res, s, 0);
        return res;
    }

    private List<String> bfsPermutation(String s) {
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        for (int pos = 0; pos < s.length(); pos++) {
            char curChar = queue.element().charAt(pos);
            if (!Character.isDigit(curChar)) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String s1 = queue.remove();
                    char[] chars = s1.toCharArray();

                    chars[pos] = Character.toLowerCase(chars[pos]);
                    queue.add(new String(chars));

                    chars[pos] = Character.toUpperCase(chars[pos]);
                    queue.add(new String(chars));
                }
            }
        }
        return new ArrayList<>(queue);
    }

    private void dfsPermutation(List<String> res, String s, int pos) {
        if (pos == s.length()) {
            res.add(s);
            return;
        }
        if (Character.isDigit(s.charAt(pos))) {
            this.dfsPermutation(res, s, pos + 1);
        } else {
            char[] chars = s.toCharArray();

            chars[pos] = Character.toLowerCase(chars[pos]);
            this.dfsPermutation(res, new String(chars), pos + 1);

            chars[pos] = Character.toUpperCase(chars[pos]);
            this.dfsPermutation(res, new String(chars), pos + 1);
        }
    }
}
