package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DeepestLeavesSum1302 {

    public static void main(String[] args) {

    }

    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<TreeNode> deepestLevel = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            deepestLevel.clear();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                deepestLevel.add(node);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        int result = 0;
        for (TreeNode node : deepestLevel) {
            result += node.val;
        }
        return result;
    }
}
