package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Combinations77 {

    public static void main(String[] args) {
        Combinations77 combinations77 = new Combinations77();
        System.out.println(combinations77.combine(4, 2));
    }

    public List<List<Integer>> combine(int n, int k) {
        Queue<List<Integer>> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            queue.add(list);
        }

        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> list = queue.remove();
            if (list.size() == k) {
                res.add(list);
            } else {
                int tail = list.get(list.size() - 1);
                for (int i = tail + 1; i <= n; i++) {
                    if (!list.contains(i)) {
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
