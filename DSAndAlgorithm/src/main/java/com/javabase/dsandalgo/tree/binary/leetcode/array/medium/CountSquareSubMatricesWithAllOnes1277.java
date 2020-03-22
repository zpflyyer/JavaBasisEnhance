package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

public class CountSquareSubMatricesWithAllOnes1277 {
    public int countSquares(int[][] matrix) {
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > 0 && i > 0 & j > 0) {
                    matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1])) + 1;
                }
                result += matrix[i][j];
            }
        }
        return result;
    }
}
