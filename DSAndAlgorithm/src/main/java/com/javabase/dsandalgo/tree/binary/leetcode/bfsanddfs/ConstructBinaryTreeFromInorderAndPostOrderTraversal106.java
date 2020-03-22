package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.Arrays;

public class ConstructBinaryTreeFromInorderAndPostOrderTraversal106 {

    public static void main(String[] args) {
        int[] inOrder = {9, 3, 15, 20, 7};
        int[] postOrder = {9, 15, 7, 20, 3};
        ConstructBinaryTreeFromInorderAndPostOrderTraversal106 constructBinaryTreeFromInorderAndPostOrderTraversal106 = new ConstructBinaryTreeFromInorderAndPostOrderTraversal106();
        constructBinaryTreeFromInorderAndPostOrderTraversal106.buildTree(inOrder, postOrder);
    }

    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        if (inOrder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(postOrder[postOrder.length - 1]);

        int inOrderIdx = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == root.val) {
                inOrderIdx = i;
                break;
            }
        }

        root.left = this.buildTree(Arrays.copyOf(inOrder, inOrderIdx), Arrays.copyOf(postOrder, inOrderIdx));
        root.right = this.buildTree(Arrays.copyOfRange(inOrder, inOrderIdx + 1, inOrder.length), Arrays.copyOfRange(postOrder, inOrderIdx, postOrder.length - 1));

        return root;
    }
}
