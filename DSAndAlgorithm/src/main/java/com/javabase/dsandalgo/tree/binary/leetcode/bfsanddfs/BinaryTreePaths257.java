package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths257 {

    public static void main(String[] args) {
        Integer[] array = {1};
        TreeNode root = TreeNode.fromIntegerArray(array);
        System.out.println(new BinaryTreePaths257().binaryTreePaths(root));
    }

    private List<String> results = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root != null) {
            String val = String.valueOf(root.val);
            if (root.left == null && root.right == null) {
                results.add(val);
            } else {
                if (root.left != null) {
                    this.binaryTreePaths0(root.left, val);
                }
                if (root.right != null) {
                    this.binaryTreePaths0(root.right, val);
                }
            }
        }
        return this.results;
    }

    private void binaryTreePaths0(TreeNode root, String prefix) {
        String val = String.valueOf(root.val);
        if (root.left == null && root.right == null) {
            results.add(prefix + "->" + val);
        } else {
            if (root.left != null) {
                this.binaryTreePaths0(root.left, prefix + "->" + val);
            }
            if (root.right != null) {
                this.binaryTreePaths0(root.right, prefix + "->" + val);
            }
        }
    }

}
