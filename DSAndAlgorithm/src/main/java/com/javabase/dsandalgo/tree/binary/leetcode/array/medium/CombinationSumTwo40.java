package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CombinationSumTwo40 {

    public static void main(String[] args) {
        CombinationSumTwo40 combinationSumTwo40 = new CombinationSumTwo40();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSumTwo40.combinationSum2(candidates, 8));
    }

    // all candidates are positive integers
    private List<Integer> candidates;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.candidates = IntStream.of(candidates).boxed().sorted().collect(Collectors.toList());
        List<List<Integer>> res = new ArrayList<>();
        this.dfs(res, new ArrayList<>(), -1, target);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> list, int lastIdx, int target) {
        if (target == 0) {
            res.add(list);
        } else if (target > 0 && lastIdx < this.candidates.size() - 1) {
            int prev = -1;
            for (int i = lastIdx + 1; i < this.candidates.size(); i++) {
                int cur = this.candidates.get(i);
                if (prev <= 0 || cur != prev) {
                    List<Integer> list1 = new ArrayList<>(list);
                    list1.add(cur);
                    this.dfs(res, list1, i, target - cur);
                    prev = cur;
                }
            }
        }
    }
}
