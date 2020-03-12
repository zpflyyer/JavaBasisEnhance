package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepthOfBinaryTree111 {

    public static void main(String[] args) {

    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int curLevel = 1;
        int curLevelCount = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (curLevelCount == 0) {
                curLevelCount = queue.size();
                curLevel++;
            }
            TreeNode node = queue.remove();
            curLevelCount--;
            if (node.left == null && node.right == null) {
                return curLevel;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return curLevel;
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
