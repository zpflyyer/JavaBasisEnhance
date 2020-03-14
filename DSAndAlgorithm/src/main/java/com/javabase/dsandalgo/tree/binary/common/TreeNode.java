package com.javabase.dsandalgo.tree.binary.common;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode fromIntegerArray(Integer[] integers) {
        TreeNode[] nodes = new TreeNode[integers.length + 1];
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] != null) {
                nodes[i + 1] = new TreeNode(integers[i]);
            } else {
                nodes[i + 1] = null;
            }
        }

        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i] != null) {
                if (i * 2 < nodes.length) {
                    nodes[i].left = nodes[i * 2];
                }
                if (i * 2 + 1 < nodes.length) {
                    nodes[i].right = nodes[i * 2 + 1];
                }
            }
        }
        return nodes[1];
    }
}
