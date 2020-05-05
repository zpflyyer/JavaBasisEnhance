package com.javabase.dsandalgo.tree.binary.leetcode.backtracking;

public class PathWithMaximumGold1219 {

    public static void main(String[] args) {

    }

    private int[] direct = new int[]{0, 1, 0, -1, 0};

    public int getMaximumGold(int[][] grid) {
        int maxGold = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    maxGold = Math.max(maxGold, this.dfs(grid, i, j));
                }
            }
        }
        return maxGold;
    }

    private int dfs(int[][] grid, int x, int y) {
        // can't visit the same cell more than once
        int original = grid[x][y];
        grid[x][y] = 0;

        int res = 0;
        for (int i = 0; i < 4; i++) {
            int x1 = x + this.direct[i];
            int y1 = y + this.direct[i + 1];
            if (0 <= x1 && x1 < grid.length
                    && 0 <= y1 && y1 < grid[0].length
                    && grid[x1][y1] != 0) {
                res = Math.max(res, this.dfs(grid, x1, y1));
            }
        }

        grid[x][y] = original;

        return grid[x][y] + res;
    }
}
