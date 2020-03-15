package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BalancedBinaryTree110 {

    public static void main(String[] args) {
        Integer[] array = {3, 9, 20, null, null, 15, 7};
        TreeNode root = TreeNode.fromIntegerArray(array);
        BalancedBinaryTree110 balancedBinaryTree110 = new BalancedBinaryTree110();
        System.out.println(balancedBinaryTree110.isBalanced(root));
    }

    private Map<TreeNode, Integer> node2Depth = new HashMap<>();

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftDepth = this.depth(root.left);
        int rightDepth = this.depth(root.right);
        return Math.abs(leftDepth - rightDepth) <= 1 && this.isBalanced(root.left) && this.isBalanced(root.right);
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (node2Depth.containsKey(root)) {
            return node2Depth.get(root);
        }
        int depth = Math.max(this.depth(root.left), this.depth(root.right)) + 1;
        this.node2Depth.put(root, depth);
        return depth;
    }
}
