package com.javabase.dsandalgo.tree.binary.leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku36 {

    public static void main(String[] args) {

    }

    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!set.add("r-" + i + "-" + board[i][j])
                            || !set.add("c-" + j + "-" + board[i][j])
                            || !set.add("s-" + i / 3 + "-" + j / 3 + "-" + board[i][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
