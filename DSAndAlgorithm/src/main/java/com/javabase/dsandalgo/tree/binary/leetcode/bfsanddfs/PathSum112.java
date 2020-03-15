package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

public class PathSum112 {

    public static void main(String[] args) {
        Integer[] input = {1, 2};
        TreeNode root = TreeNode.fromIntegerArray(input);
        PathSum112 pathSum112 = new PathSum112();
        System.out.println(pathSum112.hasPathSum(root, 1));
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        return this.hasPathSum0(root, sum);
    }

    private boolean hasPathSum0(TreeNode root, int sum) {
        if (root.left == null && root.right == null) {
            return root.val == sum;
        } else if (root.left == null) {
            return this.hasPathSum0(root.right, sum - root.val);
        } else if (root.right == null) {
            return this.hasPathSum0(root.left, sum - root.val);
        } else {
            return this.hasPathSum0(root.left, sum - root.val) || this.hasPathSum0(root.right, sum - root.val);
        }
    }
}
