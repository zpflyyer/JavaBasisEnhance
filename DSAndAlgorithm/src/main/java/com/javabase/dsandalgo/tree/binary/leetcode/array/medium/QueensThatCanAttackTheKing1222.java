package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QueensThatCanAttackTheKing1222 {

    public static void main(String[] args) {
        int[][] queens = {{0, 0}, {1, 1}, {2, 2}, {3, 4}, {3, 5}, {4, 4}, {4, 5}};
        int[] king = {3, 3};
        System.out.println(new QueensThatCanAttackTheKing1222().queensAttacktheKing(queens, king));
    }

    private int kingX;
    private int kingY;
    private Map<String, Pair> direct2Coordinate;

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        this.kingX = king[0];
        this.kingY = king[1];
        this.direct2Coordinate = new HashMap<>();

        for (int[] queen : queens) {
            int i = queen[0];
            int j = queen[1];
            if (i == kingX) {
                if (j < kingY) {
                    this.computePair("left", i, j);
                } else if (j > kingY) {
                    this.computePair("right", i, j);
                }
            } else if (j == kingY) {
                if (i < kingX) {
                    this.computePair("top", i, j);
                } else {
                    this.computePair("bot", i, j);
                }
            } else if (i - j == kingX - kingY) {
                if (i < kingX) {
                    this.computePair("topLeft", i, j);
                } else {
                    this.computePair("botRight", i, j);
                }
            } else if (i + j == kingX + kingY) {
                if (i < kingX) {
                    this.computePair("botLeft", i, j);
                } else {
                    this.computePair("topRight", i, j);
                }
            }
        }

        return direct2Coordinate.values().stream().map(pair -> Arrays.asList(pair.x, pair.y)).collect(Collectors.toList());
    }

    private void computePair(String direct, int x, int y) {
        Pair pair = this.direct2Coordinate.get(direct);
        Pair pair1 = new Pair(x, y);
        if (pair == null) {
            direct2Coordinate.put(direct, pair1);
        } else {
            if (pair.compareTo(pair1) > 0) {
                direct2Coordinate.put(direct, pair1);
            }
        }
    }

    private final class Pair implements Comparable<Pair> {
        private int x;
        private int y;

        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {
            return Double.compare(Math.pow(x - kingX, 2) + Math.pow(y - kingY, 2), Math.pow(o.x - kingX, 2) + Math.pow(o.y - kingY, 2));
        }
    }
}
