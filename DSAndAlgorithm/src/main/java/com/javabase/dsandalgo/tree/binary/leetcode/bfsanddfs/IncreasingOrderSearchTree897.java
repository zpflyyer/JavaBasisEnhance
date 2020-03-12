package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

public class IncreasingOrderSearchTree897 {

    public static void main(String[] args) {

    }

    public TreeNode increasingBST(TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                TreeNode left = this.increasingBST(root.left);
                left.right = root;
            }
            if (root.right != null) {
                root.right = this.increasingBST(root.right);
            }
        }
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
