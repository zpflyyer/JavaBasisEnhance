package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs.good;

import com.alibaba.fastjson.JSON;
import com.javabase.dsandalgo.tree.binary.common.TreeNode;

public class BinarySearchTreeToGreaterSumTree1038 {

    public static void main(String[] args) {
        Integer[] array = {4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8};
        TreeNode root = TreeNode.fromIntegerArray(array);
        BinarySearchTreeToGreaterSumTree1038 bsttgst = new BinarySearchTreeToGreaterSumTree1038();
        bsttgst.inverseInOrder(root);
        System.out.println(JSON.toJSONString(bsttgst.bstToGst(root)));
    }

    public TreeNode bstToGst(TreeNode root) {
        this.inverseInOrder(root, 0);
        return root;
    }

    /**
     * @param sum root作为一颗"左子树"时，其"父亲节点及其右兄弟树上所有节点"的和
     */
    private int inverseInOrder(TreeNode root, int sum) {
        // 拼接边界
        if (root == null) {
            return sum;
        }
        int rightSum = this.inverseInOrder(root.right, sum);
        root.val = root.val + rightSum;

        return this.inverseInOrder(root.left, root.val);
    }

    private int inOrder(TreeNode root, int sum) {
        if (root != null) {
            int leftSum = this.inOrder(root.left, sum);
            root.val = root.val + leftSum;
            return this.inOrder(root.right, root.val);
        }
        return sum;
    }

    private void inverseInOrder(TreeNode root) {
        if (root != null) {
            this.inverseInOrder(root.right);
            System.out.println(root.val);
            this.inverseInOrder(root.left);
        }
    }

}
