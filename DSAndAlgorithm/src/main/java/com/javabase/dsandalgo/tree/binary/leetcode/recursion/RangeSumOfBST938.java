package com.javabase.dsandalgo.tree.binary.leetcode.recursion;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

public class RangeSumOfBST938 {

    public static void main(String[] args) {
        Integer[] array = {10, 5, 15, 3, 7, null, 18};
        TreeNode root = TreeNode.fromIntegerArray(array);
        RangeSumOfBST938 rangeSumOfBST938 = new RangeSumOfBST938();
        System.out.println(rangeSumOfBST938.rangeSumBST(root, 7, 15));
    }

    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        // 在右子树上查找
        if (root.val <= L) {
            return this.rangeSumBST(root.right, L, R) + (root.val == L ? L : 0);
        }
        // 在左子树上查找
        else if (root.val >= R) {
            return this.rangeSumBST(root.left, L, R) + (root.val == R ? R : 0);
        }
        // 整棵树上查找
        else {
            return this.rangeSumBST(root.left, L, R) + this.rangeSumBST(root.right, L, R) + root.val;
        }
    }

}
