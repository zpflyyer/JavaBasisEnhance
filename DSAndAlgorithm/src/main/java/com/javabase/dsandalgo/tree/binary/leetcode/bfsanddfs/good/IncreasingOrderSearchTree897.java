package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs.good;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

public class IncreasingOrderSearchTree897 {

    public static void main(String[] args) {
        IncreasingOrderSearchTree897 inc = new IncreasingOrderSearchTree897();
        Integer[] array = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9};
        TreeNode root = TreeNode.fromIntegerArray(array);
        System.out.println(inc.increasingBST(root));
    }

    private TreeNode increasingBST(TreeNode root) {
        return this.dfs(root, null);
    }

    /**
     * @param root 一棵子树
     * @param next 中序遍历里，以root为根的子树的后继节点，遍历过程中，对root整棵树都有意义
     * @return 顺序化后的新树的根节点
     */
    // 右链，递增
    private TreeNode dfs(TreeNode root, TreeNode next) {
        if (root == null) {
            return next;
        }
        TreeNode head = this.dfs(root.left, root);
        root.left = null;
        root.right = this.dfs(root.right, next);
        return head;
    }

    // 左链，递增
    private TreeNode dfs1(TreeNode root, TreeNode next) {
        if (root != null) {
            TreeNode head = this.dfs1(root.left, root);
            root.left = this.dfs1(root.right, next);
            root.right = null;
            return head;
        }
        return next;
    }

    // 左链，递减
    private TreeNode dfs2(TreeNode root, TreeNode next) {
        if (root != null) {
            TreeNode head = this.dfs2(root.right, root);
            root.right = null;
            root.left = this.dfs2(root.left, next);
            return head;
        }
        return next;
    }

    // 右链，递减
    private TreeNode dfs3(TreeNode root, TreeNode next) {
        if (root != null) {
            TreeNode head = this.dfs3(root.right, root);
            root.right = this.dfs3(root.left, next);
            root.left = null;
            return head;
        }
        return next;
    }

}
