package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FloodFill733 {

    public static void main(String[] args) {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(Arrays.asList(new FloodFill733().floodFill(image, 1, 1, 2)));
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int markColor = -1;
        int originColor = image[sr][sc];
        Queue<Coordinate> queue = new LinkedList<>();
        if (newColor != originColor) {
            queue.add(new Coordinate(sr, sc));
        }
        while (!queue.isEmpty()) {
            Coordinate c = queue.poll();
            image[c.x][c.y] = newColor;
            // left
            if (c.y - 1 >= 0 && this.mark(image, c.x, c.y - 1, originColor, markColor)) {
                queue.offer(new Coordinate(c.x, c.y - 1));
            }
            // top
            if (c.x - 1 >= 0 && this.mark(image, c.x - 1, c.y, originColor, markColor)) {
                queue.offer(new Coordinate(c.x - 1, c.y));
            }
            // right
            if (c.y + 1 < image[0].length && this.mark(image, c.x, c.y + 1, originColor, markColor)) {
                queue.offer(new Coordinate(c.x, c.y + 1));
            }
            // bottom
            if (c.x + 1 < image.length && this.mark(image, c.x + 1, c.y, originColor, markColor)) {
                queue.offer(new Coordinate(c.x + 1, c.y));
            }
        }
        return image;
    }

    private boolean mark(int[][] image, int x, int y, int originColor, int markColor) {
        if (image[x][y] == originColor) {
            image[x][y] = markColor;
            return true;
        }
        return false;
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
