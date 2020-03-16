package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs.good;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreOrderTraversal144 {

    public static void main(String[] args) {
        Integer[] array = {10, 5, 15, 3, 7, null, 18};
        TreeNode root = TreeNode.fromIntegerArray(array);
        BinaryTreePreOrderTraversal144 binaryTreePreOrderTraversal144 = new BinaryTreePreOrderTraversal144();
        System.out.println(binaryTreePreOrderTraversal144.preOrderTraversal(root));
    }

    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                results.add(node.val);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return results;
    }
}
