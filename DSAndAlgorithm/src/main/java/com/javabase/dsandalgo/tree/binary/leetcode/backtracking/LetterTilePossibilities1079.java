package com.javabase.dsandalgo.tree.binary.leetcode.backtracking;

public class LetterTilePossibilities1079 {

    public static void main(String[] args) {
        LetterTilePossibilities1079 tilePossibilities1079 = new LetterTilePossibilities1079();
        System.out.println(tilePossibilities1079.numTilePossibilities("AAABBC"));
    }

    public int numTilePossibilities(String tiles) {
        int[] counts = new int[26];
        for (int i = 0; i < tiles.length(); i++) {
            counts[tiles.charAt(i) - 'A']++;
        }
        return this.dfs(counts);
    }

    private int dfs(int[] counts) {
        int res = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 0) {
                continue;
            }
            // current counts can make a valid combination since there're still remaining one
            res++;
            // try to find more counts to this existing combination
            counts[i]--;
            res += this.dfs(counts);
            // restore this and try to find other combinations
            counts[i]++;
        }
        return res;
    }
}
