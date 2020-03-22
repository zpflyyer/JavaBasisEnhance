package com.javabase.dsandalgo.tree.binary.leetcode.string;

public class RobotReturnToOrigin657 {

    public static void main(String[] args) {

    }

    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'U': {
                    y++;
                    break;
                }
                case 'D': {
                    y--;
                    break;
                }
                case 'L': {
                    x--;
                    break;
                }
                case 'R': {
                    x++;
                    break;
                }
            }
        }

        return x == 0 && y == 0;
    }
}
