package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.Arrays;

public class ConstructBinaryTreeFromPreOrderAndPostOrderTraversal889 {

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] post = {4, 5, 2, 6, 7, 3, 1};
        ConstructBinaryTreeFromPreOrderAndPostOrderTraversal889 c = new ConstructBinaryTreeFromPreOrderAndPostOrderTraversal889();
        c.constructFromPrePost(pre, post);
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(pre[0]);

        if (pre.length >= 2) {
            int leftRootVal = pre[1];
            int leftIdx = 0;
            for (int i = 0; i < post.length; i++) {
                if (post[i] == leftRootVal) {
                    leftIdx = i;
                    break;
                }
            }

            root.left = this.constructFromPrePost(Arrays.copyOfRange(pre, 1, 1 + leftIdx + 1), Arrays.copyOf(post, leftIdx + 1));
            root.right = this.constructFromPrePost(Arrays.copyOfRange(pre, 1 + leftIdx + 1, pre.length), Arrays.copyOfRange(post, leftIdx + 1, post.length - 1));
        }

        return root;
    }
}
