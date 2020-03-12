package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import java.util.LinkedList;
import java.util.Queue;

public class CousinsInBinaryTree993 {

    public static void main(String[] args) {

    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if (x == y) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // current level
        int curLevel = 1;
        // total nodes in this level
        int curLevelCount = 1;
        // serial number of current node in a top-down & left-right traversal
        int curCount = 0;

        // mark level and serial number of the 2 given nodes
        int xPCount = 0;
        int yPCount = 0;
        int xPLevel = 0;
        int yPLevel = 0;

        while (!queue.isEmpty() && (xPCount <= 0 || yPCount <= 0)) {
            // get down to a new level
            if (curLevelCount == 0) {
                curLevel++;
                curLevelCount = queue.size();
            }
            TreeNode node = queue.remove();
            curCount++;
            curLevelCount--;

            if (node.left != null) {
                queue.add(node.left);
                if (node.left.val == x) {
                    xPCount = curCount;
                    xPLevel = curLevel;
                }
                if (node.left.val == y) {
                    yPCount = curCount;
                    yPLevel = curLevel;
                }
            }
            if (node.right != null) {
                queue.add(node.right);
                if (node.right.val == x) {
                    xPCount = curCount;
                    xPLevel = curLevel;
                }
                if (node.right.val == y) {
                    yPCount = curCount;
                    yPLevel = curLevel;
                }
            }
        }

        return xPLevel == yPLevel && Math.abs(xPCount - yPCount) > 0;
    }

    private static final class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
