package com.javabase.dsandalgo.tree.binary.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Permutations46 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(new Permutations46().permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
//        return this.bfs(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        this.dfs(res, list, nums);
        return res;
    }

    private List<List<Integer>> bfs(int[] nums) {
        Queue<List<Integer>> queue = new LinkedList<>();
        for (int num : nums) {
            List<Integer> list = new ArrayList<>(nums.length);
            list.add(num);
            queue.add(list);
        }
        for (int i = 0; i < nums.length - 1; i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                List<Integer> list = queue.remove();
                for (int num : nums) {
                    if (!list.contains(num)) {
                        List<Integer> list1 = new ArrayList<>(list);
                        list1.add(num);
                        queue.add(list1);
                    }
                }
            }
        }

        return new ArrayList<>(queue);
    }

    private void dfs(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(list);
            return;
        }
        for (int num : nums) {
            if (!list.contains(num)) {
                List<Integer> list1 = new ArrayList<>(list);
                list1.add(num);
                this.dfs(res, list1, nums);
            }
        }
    }
}
