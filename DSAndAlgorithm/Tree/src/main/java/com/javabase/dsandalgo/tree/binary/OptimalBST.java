package com.huadongmedia.infrastructure;

import com.alibaba.fastjson.JSON;

public class OptimalBST {

    public static void main(String[] args) {
        double[] kArray = {0.0, 0.15, 0.10, 0.05, 0.10, 0.20};
        double[] dArray = {0.05, 0.10, 0.05, 0.05, 0.05, 0.10};
        TreeNode optimalBSTRoot = new OptimalBST().buildOptimalBST(kArray, dArray);
    }

    public TreeNode buildOptimalBST(double[] kArray, double[] dArray) {
        int i = 1;
        int j = kArray.length - 1;

        // 概率求和矩阵
        double[][] pSumMatrix = new double[j + 1][j + 1];
        for (int k = 1; k <= j; k++) {
            for (int l = k; l <= j; l++) {
                pSumMatrix[k][l] = 0;
                for (int m = k; m <= l; m++) {
                    pSumMatrix[k][l] += kArray[m];
                }
                for (int m = k - 1; m <= l; m++) {
                    pSumMatrix[k][l] += dArray[m];
                }
            }
        }

        // 自底向上求解最优BST
        int maxLen = j - i + 1;
        TreeNode[][] optimalMatrix = new TreeNode[j + 2][j + 1];
        for (int k = 1; k <= j + 1; k++) {
            optimalMatrix[k][k - 1] = new TreeNode(null, null, "d" + (k - 1), dArray[k - 1]);
        }

        for (int len = 1; len <= maxLen; len++) {
            for (int start = i; start <= j - len + 1; start++) {
                int end = start + len - 1;
                double optimalExp = Integer.MAX_VALUE;
                String rootVal = null;
                TreeNode left = null;
                TreeNode right = null;
                for (int r = start; r <= end; r++) {
                    double exp = optimalMatrix[start][r - 1].exp + optimalMatrix[r + 1][end].exp + pSumMatrix[start][end];
                    if (exp < optimalExp) {
                        optimalExp = exp;
                        rootVal = "k" + r;
                        left = optimalMatrix[start][r - 1];
                        right = optimalMatrix[r + 1][end];
                    }
                }
                optimalMatrix[start][start + len - 1] = new TreeNode(left, right, rootVal, optimalExp);
            }
        }

        return optimalMatrix[i][j];
    }


    public static final class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private String val;
        private double exp;

        private TreeNode(TreeNode left, TreeNode right, String val, double exp) {
            this.left = left;
            this.right = right;
            this.val = val;
            this.exp = exp;
        }
    }
}
