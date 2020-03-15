package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

public class SameTree100 {

    public static void main(String[] args) {

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q!= null) {
            return p.val == q.val && this.isSameTree(p.left, q.left) && this.isSameTree(p.right, q.right);
        } else return p == null && q == null;
    }

}
