package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs.good;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostOrderTraversal145 {

    public static void main(String[] args) {
        Integer[] array = {10, 5, 15, 3, 7, null, 18};
        TreeNode root = TreeNode.fromIntegerArray(array);
        BinaryTreePostOrderTraversal145 binaryTreePostOrderTraversal145 = new BinaryTreePostOrderTraversal145();
        System.out.println(binaryTreePostOrderTraversal145.postOrderTraversal(root));
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        LinkedList<Integer> results = new LinkedList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                results.addFirst(node.val);
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
        }
        return results;
    }
}
