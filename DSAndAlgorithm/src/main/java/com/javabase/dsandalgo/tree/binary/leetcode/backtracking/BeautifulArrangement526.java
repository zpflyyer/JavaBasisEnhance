package com.javabase.dsandalgo.tree.binary.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class BeautifulArrangement526 {

    public static void main(String[] args) {
        BeautifulArrangement526 beautifulArrangement526 = new BeautifulArrangement526();
        System.out.println(beautifulArrangement526.countArrangement(4));
    }

    private int n;

    public int countArrangement(int n) {
        this.n = n;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> start = new ArrayList<>();
        this.dfs(res, start);
        System.out.println(res);
        return res.size();
    }

    private void dfs(List<List<Integer>> res, List<Integer> list) {
        if (list.size() == n) {
            res.add(list);
            return;
        }
        int idx = list.size() + 1;
        for (int num = 1; num <= n; num++) {
            if (!list.contains(num) && (num % idx == 0 || idx % num == 0)) {
                List<Integer> list1 = new ArrayList<>(list);
                list1.add(num);
                this.dfs(res, list1);
            }
        }
    }
}
