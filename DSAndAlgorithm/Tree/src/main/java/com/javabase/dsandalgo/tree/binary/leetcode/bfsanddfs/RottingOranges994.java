package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RottingOranges994 {

    private static final int MARKED = 3;

    public static void main(String[] args) {
        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        RottingOranges994 rottingOranges994 = new RottingOranges994();
        System.out.println(rottingOranges994.orangesRotting(grid));
    }

    public int orangesRotting(int[][] grid) {
        int totalCount = 0;
        int rottenCount = 0;
        int time = 0;
        Queue<Coordinate> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    totalCount++;
                }
                if (grid[i][j] == 2) {
                    rottenCount++;
                    queue.addAll(this.findAja(i, j, grid));
                }
            }
        }

        int curCount = queue.size();
        while (!queue.isEmpty()) {
            Coordinate c = queue.remove();
            curCount--;
            grid[c.x][c.y] = 2;
            rottenCount++;
            queue.addAll(this.findAja(c.x, c.y, grid));

            if (curCount == 0) {
                curCount = queue.size();
                time++;
            }
        }

        return rottenCount == totalCount ? time : -1;
    }

    private List<Coordinate> findAja(int i, int j, int[][] grid) {
        List<Coordinate> aja = new ArrayList<>();
        // top
        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
            aja.add(new Coordinate(i - 1, j));
            grid[i - 1][j] = MARKED;
        }
        // left
        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
            aja.add(new Coordinate(i, j - 1));
            grid[i][j - 1] = MARKED;
        }
        // bottom
        if (i + 1 < grid.length && grid[i + 1][j] == 1) {
            aja.add(new Coordinate(i + 1, j));
            grid[i + 1][j] = MARKED;
        }
        // top
        if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
            aja.add(new Coordinate(i, j + 1));
            grid[i][j + 1] = MARKED;
        }
        return aja;
    }

    private static final class Coordinate {
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
