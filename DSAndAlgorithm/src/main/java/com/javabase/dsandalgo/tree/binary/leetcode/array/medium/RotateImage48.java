package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

public class RotateImage48 {

    public static void main(String[] args) {
        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        RotateImage48.rotate90(matrix);
        RotateImage48.rotate180(matrix);
    }

    public static void rotate90(int[][] matrix) {
        for (int i = matrix.length; i > 1; i -= 2) {
            int l = (matrix.length - i) / 2;
            int r = (matrix.length + i) / 2 - 1;
            for (int j = l; j < r; j++) {
                int tmp = matrix[l][j];
                matrix[l][j] = matrix[matrix.length - 1 - j][l];
                matrix[matrix.length - 1 - j][l] = matrix[r][matrix.length - 1 - j];
                matrix[r][matrix.length - 1 - j] = matrix[j][r];
                matrix[j][r] = tmp;
            }
        }
    }

    public static void rotate180(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - i][j];
                matrix[matrix.length - 1 - i][j] = tmp;
            }
        }

        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[j][matrix.length - 1 - i];
                matrix[j][matrix.length - 1 - i] = tmp;
            }
        }
    }
}
