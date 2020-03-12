package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversalII107 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int curLevelCount = 1;
            List<Integer> curLevelList = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                curLevelCount--;
                curLevelList.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

                if (curLevelCount == 0) {
                    result.add(curLevelList);
                    curLevelList = new ArrayList<>();
                    curLevelCount = queue.size();
                }
            }

            for (int i = 0; i < result.size() / 2; i++) {
                List<Integer> tmp = result.get(i);
                result.set(i, result.get(result.size() - 1 - i));
                result.set(result.size() - 1 - i, tmp);
            }
        }
        return result;
    }

    private static final class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
