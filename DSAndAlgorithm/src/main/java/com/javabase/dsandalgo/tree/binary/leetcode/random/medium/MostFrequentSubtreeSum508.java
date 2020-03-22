package com.javabase.dsandalgo.tree.binary.leetcode.random.medium;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.*;

public class MostFrequentSubtreeSum508 {

    public static void main(String[] args) {
        Integer[] input = {0, 2, 3, -1, 2, -2, -1};
        TreeNode root = TreeNode.fromIntegerArray(input);
        System.out.println(Arrays.toString(new MostFrequentSubtreeSum508().findFrequentTreeSum(root)));
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        this.sumSub(root);

        Map<Integer, Integer> value2Times = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            value2Times.compute(node.val, (k, v) -> v == null ? 1 : v + 1);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        int time = value2Times.entrySet().stream().min((e1, e2) -> e2.getValue().compareTo(e1.getValue())).get().getValue();
        return value2Times.entrySet().stream().filter(e -> e.getValue() == time).mapToInt(Map.Entry::getKey).toArray();
    }

    private TreeNode sumSub(TreeNode root) {
        if (root == null) {
            return null;
        }

        int leftVal = 0;
        int rightVal = 0;
        if (root.left != null) {
            leftVal = this.sumSub(root.left).val;
        }
        if (root.right != null) {
            rightVal = this.sumSub(root.right).val;
        }
        root.val += leftVal + rightVal;
        return root;
    }
}
