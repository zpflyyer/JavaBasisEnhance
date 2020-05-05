package com.javabase.dsandalgo.tree.binary.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CombinationSum39 {
    public static void main(String[] args) {
        int[] candidates = {2, 3, 5};
        CombinationSum39 combinationSum39 = new CombinationSum39();
        System.out.println(combinationSum39.combinationSum(candidates, 8));
    }

    private List<Integer> candidates;
    private int target;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<Integer> candidateList = new ArrayList<>();
//        for (int candidate : candidates) {
//            candidateList.add(candidate);
//        }
//        return this.bfs(candidateList, target);
        this.candidates = new ArrayList<>();
        for (int candidate : candidates) {
            this.candidates.add(candidate);
        }
        this.candidates.sort(Integer::compareTo);

        this.target = target;

        List<List<Integer>> res = new ArrayList<>();
        this.dfs(res, new ArrayList<>());
        return res;
    }

    private List<List<Integer>> bfs(List<Integer> candidateList, int target) {
        // sort
        candidateList.sort(Integer::compareTo);

        Queue<List<Integer>> queue = new LinkedList<>();
        candidateList.stream().filter(candidate -> candidate <= target).forEach(candidate -> {
            List<Integer> list = new ArrayList<>();
            list.add(candidate);
            queue.add(list);
        });

        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> list = queue.remove();
            int sum = list.stream().mapToInt(Integer::intValue).sum();
            if (sum == target) {
                res.add(list);
            } else if (sum < target) {
                int tail = list.get(list.size() - 1);
                int tailIdx = candidateList.indexOf(tail);
                // 让所有序列增序
                for (int i = tailIdx; i < candidateList.size(); i++) {
                    int val = candidateList.get(i);
                    if (val + sum <= target) {
                        List<Integer> list1 = new ArrayList<>(list);
                        list1.add(val);
                        queue.add(list1);
                    } else {
                        break;
                    }
                }
            }
        }
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> list) {
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        if (sum == this.target) {
            res.add(list);
        } else {
            int tailIdx = list.isEmpty() ? 0 : this.candidates.indexOf(list.get(list.size() - 1));
            for (int i = tailIdx; i < this.candidates.size(); i++) {
                if (sum + this.candidates.get(i) <= this.target) {
                    List<Integer> list1 = new ArrayList<>(list);
                    list1.add(this.candidates.get(i));
                    this.dfs(res, list1);
                }
            }
        }
    }
}
