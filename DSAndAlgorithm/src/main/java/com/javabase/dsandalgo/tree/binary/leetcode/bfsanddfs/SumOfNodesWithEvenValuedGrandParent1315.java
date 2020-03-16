package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

public class SumOfNodesWithEvenValuedGrandParent1315 {

    public static void main(String[] args) {

    }

    public int sumEvenGrandparent(TreeNode root) {
        int result = 0;
        if ((root.val & 1) == 0) {
            result += this.sumGrandChildren(root);
        }
        if (root.left != null) {
            result += this.sumEvenGrandparent(root.left);
        }
        if (root.right != null) {
            result += this.sumEvenGrandparent(root.right);
        }

        return result;
    }


    private int sumGrandChildren(TreeNode grandParent) {
        int result = 0;
        if (grandParent.left != null) {
            if (grandParent.left.left != null) {
                result += grandParent.left.left.val;
            }
            if (grandParent.left.right != null) {
                result += grandParent.left.right.val;
            }
        }
        if (grandParent.right != null) {
            if (grandParent.right.left != null) {
                result += grandParent.right.left.val;
            }
            if (grandParent.right.right != null) {
                result += grandParent.right.right.val;
            }
        }

        return result;
    }

}
