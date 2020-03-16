package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs.good;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal94 {

    public static void main(String[] args) {
        Integer[] array = {10, 5, 15, 3, 7, null, 18};
        TreeNode root = TreeNode.fromIntegerArray(array);
        BinaryTreeInorderTraversal94 binaryTreeInorderTraversal94 = new BinaryTreeInorderTraversal94();
        System.out.println(binaryTreeInorderTraversal94.inorderTraversal(root));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            while (!stack.isEmpty() || cur != null) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.pop();
                results.add(cur.val);
                cur = cur.right;
            }
        }
        return results;
    }
}
