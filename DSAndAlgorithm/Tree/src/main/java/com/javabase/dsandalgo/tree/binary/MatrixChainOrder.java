package com.huadongmedia.infrastructure;

import java.util.HashMap;
import java.util.Map;

public class MatrixChainOrder {

    private Map<Integer, Map<Integer, MinCostRecord>> len2Start2MinCost = new HashMap<>();

    private int[] matrix;

    // 标记位更新发生在同步块里，所以不必担心可见性问题，无需volatile关键字
    private boolean costCalculated;

    public MatrixChainOrder(int[] matrix) {
        this.matrix = matrix;
    }

    public static void main(String[] args) {
        int[] matrixArray = {5, 10, 3, 12, 5, 50, 6};
        MatrixChainOrder matrixChainOrder = new MatrixChainOrder(matrixArray);
        String one2Six = matrixChainOrder.getMinCost(1, 6);
        String two2five = matrixChainOrder.getMinCost(2, 5);
        String two2Four = matrixChainOrder.getMinCost(2, 4);
    }

    public String getMinCost(int start, int end) {
        if (start < 0 || start > end || end > matrix.length - 1) {
            return "";
        } else if (start == end) {
            return "A" + start;
        }
        if (!this.costCalculated) {
            synchronized (this) {
                if (!this.costCalculated) {
                    this.calculateMinCost();
                    this.costCalculated = true;
                }
            }
        }
        MinCostRecord minCostRecord = this.len2Start2MinCost.get(end - start + 1).get(start);
        int splitIdx = minCostRecord.getSplitIdx();
        String leftStr = this.getMinCost(start, splitIdx);
        if (leftStr.length() > 2) {
            leftStr = "(" + leftStr + ")";
        }
        String rightStr = this.getMinCost(splitIdx + 1, end);
        if (rightStr.length() > 2) {
            rightStr = "(" + rightStr + ")";
        }
        return leftStr + "*" + rightStr;
    }

    private void calculateMinCost() {
        int matrixNum = matrix.length - 1;
        // 初始化长度为1的子序列的最优代价
        Map<Integer, MinCostRecord> start2MinCostOfOne = new HashMap<>();
        for (int i = 1; i <= matrixNum; i++) {
            start2MinCostOfOne.put(i, new MinCostRecord(0, i));
        }
        this.len2Start2MinCost.put(1, start2MinCostOfOne);

        // 问题的规模为矩阵链的长度
        for (int len = 2; len <= matrixNum; len++) {
            // 对于给定长度len，计算所有该长度的子链的最优代价
            Map<Integer, MinCostRecord> start2MinCostOfLen = new HashMap<>();
            for (int end = len; end <= matrixNum; end++) {
                // 对于给定起始点的子链，计算其最优代价
                int start = end - len + 1;
                int minCost = Integer.MAX_VALUE;
                int splitIdx = -1;
                for (int leftLen = 1; leftLen <= len - 1; leftLen++) {
                    int startOfRight = start + leftLen;
                    MinCostRecord leftRecord = this.len2Start2MinCost.get(leftLen).get(start);
                    MinCostRecord rightRecord = this.len2Start2MinCost.get(len - leftLen).get(startOfRight);
                    int cost = leftRecord.getCost() + rightRecord.getCost() + matrix[start - 1] * matrix[start + leftLen - 1] * matrix[startOfRight + len - leftLen - 1];
                    if (cost < minCost) {
                        minCost = cost;
                        splitIdx = start + leftLen - 1;
                    }
                }
                start2MinCostOfLen.put(start, new MinCostRecord(minCost, splitIdx));
            }
            this.len2Start2MinCost.put(len, start2MinCostOfLen);
        }
    }

    private static class MinCostRecord {
        private int cost;
        // inclusive
        private int splitIdx;

        public MinCostRecord(int cost, int splitIdx) {
            this.cost = cost;
            this.splitIdx = splitIdx;
        }

        public int getCost() {
            return cost;
        }

        public MinCostRecord setCost(int cost) {
            this.cost = cost;
            return this;
        }

        public int getSplitIdx() {
            return splitIdx;
        }

        public MinCostRecord setSplitIdx(int splitIdx) {
            this.splitIdx = splitIdx;
            return this;
        }
    }
}
