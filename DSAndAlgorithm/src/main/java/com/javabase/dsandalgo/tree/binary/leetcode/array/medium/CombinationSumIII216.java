package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CombinationSumIII216 {

    public static void main(String[] args) {
        CombinationSumIII216 comb = new CombinationSumIII216();
        List<List<Integer>> results = comb.combinationSum3(3, 15);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        // make sure there is at least 1 combination with n unique numbers which sum up to n
        if (1 <= k && k <= 9 && (1 + k) * k / 2 <= n && n <= (19 - k) * k / 2) {
//            this.combinationSum(results, new ArrayList<>(), k, n);
            return this.bfs(k, n);
        }
        return results;
    }

    private void combinationSum(List<List<Integer>> results, List<Integer> comb, int k, int n) {
        if (k == 0) {
            results.add(comb);
            return;
        }
        int from = comb.isEmpty() ? 1 : comb.get(comb.size() - 1) + 1;
        if (n > (20 - k) * (k - 1) / 2) {
            from = Math.max(n - (20 - k) * (k - 1) / 2, from);
        }
        int to = (n - k * (k - 1) / 2) / k;
        for (int i = from; i <= to; i++) {
            List<Integer> tmp = new ArrayList<>(comb);
            tmp.add(i);
            this.combinationSum(results, tmp, k - 1, n - i);
        }
    }

    private List<List<Integer>> bfs(int k, int n) {
        Queue<List<Integer>> queue = new LinkedList<>();
        for (int i = 1; i <= Math.min(9, n); i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            queue.add(list);
        }

        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> list = queue.remove();
            int sum = list.stream().mapToInt(Integer::intValue).sum();
            if (sum == n && list.size() == k) {
                res.add(list);
            } else {
                Integer tail = list.get(list.size() - 1);
                for (int i = tail + 1; i <= 9; i++) {
                    if (i + sum <= n) {
                        List<Integer> list1 = new ArrayList<>(list);
                        list1.add(i);
                        queue.add(list1);
                    }
                }
            }
        }
        return res;
    }
}
