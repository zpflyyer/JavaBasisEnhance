package com.javabase.dsandalgo.tree.binary.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GenerateParentheses22 {

    public static void main(String[] args) {
        GenerateParentheses22 generateParentheses22 = new GenerateParentheses22();
        System.out.println(generateParentheses22.generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        return this.bfs(n);
    }

    private List<String> bfs(int n) {
        int maxLength = 2 * n;
        Queue<String> queue = new LinkedList<>();
        List<String> res = new ArrayList<>();
        queue.add(String.valueOf(0));
        while (!queue.isEmpty()) {
            String s = queue.remove();
            if (s.length() == maxLength + 1) {
                res.add(s.substring(0, maxLength));
            } else {
                int x = Integer.parseInt(s.substring(s.length() - 1));
                if (x == 0) {
                    queue.add(s.replace("0", "(1"));
                } else {
                    queue.add(s.replace(x + "", ")" + (x - 1)));
                    int remain = maxLength - (s.length() - 1);
                    if (remain > x) {
                        queue.add(s.replace(x + "", "(" + (x + 1)));
                    }
                }
            }
        }
        return res;
    }
}
