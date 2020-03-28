package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.*;

public class MaxAreaOfIsland695 {

    public static void main(String[] args) {

    }

    public int maxAreaOfIsland(int[][] grid) {
        List<Set<Pair>> islands = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (this.mark(grid, i, j)) {
                    Queue<Pair> queue = new LinkedList<>();
                    Set<Pair> island = new HashSet<>();
                    queue.add(new Pair(i, j));
                    while (!queue.isEmpty()) {
                        Pair pair = queue.remove();
                        island.add(pair);
                        queue.addAll(this.expandIsland(grid, pair.x, pair.y));
                    }
                    islands.add(island);
                }
            }
        }

        int maxArea = 0;
        for (Set<Pair> island : islands) {
            maxArea = Math.max(maxArea, island.size());
        }
        return maxArea;
    }

    private Set<Pair> expandIsland(int[][] grid, int i, int j) {
        Set<Pair> results = new HashSet<>();
        if (j - 1 >= 0 && this.mark(grid, i, j - 1)) {
            results.add(new Pair(i, j - 1));
        }
        if (j + 1 < grid[0].length && this.mark(grid, i, j + 1)) {
            results.add(new Pair(i, j + 1));
        }
        if (i - 1 >= 0 && this.mark(grid, i - 1, j)) {
            results.add(new Pair(i - 1, j));
        }
        if (i + 1 < grid.length && this.mark(grid, i + 1, j)) {
            results.add(new Pair(i + 1, j));
        }
        return results;
    }

    private boolean mark(int[][] grid, int i, int j) {
        if (grid[i][j] == 1) {
            grid[i][j] = -1;
            return true;
        }
        return false;
    }

    private static final class Pair {
        private int x;
        private int y;

        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
