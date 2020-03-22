package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortTheMatrixDiagonally1329 {

    // time:O(mnLogD); space(mn)
    public int[][] diagonalSort(int[][] mat) {
        Map<Integer, PriorityQueue<Integer>> diagonal2Value = new HashMap<>();

        // i - j means a specific diagonal
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                diagonal2Value.putIfAbsent(i - j, new PriorityQueue<>());
                diagonal2Value.get(i - j).add(mat[i][j]);
            }
        }

        // the remove is from top-left to down-right direct
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = diagonal2Value.get(i - j).remove();
            }
        }

        return mat;
    }
}
