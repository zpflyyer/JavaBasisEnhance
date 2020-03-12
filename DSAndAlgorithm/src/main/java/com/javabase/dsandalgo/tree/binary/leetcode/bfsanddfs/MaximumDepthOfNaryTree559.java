package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumDepthOfNaryTree559 {

    public static void main(String[] args) {
        MaximumDepthOfNaryTree559 maximumDepthOfNaryTree559 = new MaximumDepthOfNaryTree559();
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int curLevelCount = 1;
        int depth = 1;
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            if (curLevelCount == 0) {
                curLevelCount = nodeQueue.size();
                depth++;
            }
            Node node = nodeQueue.remove();
            curLevelCount--;
            nodeQueue.addAll(node.children);
        }
        return depth;
    }

    private static final class Node {
        public int val;
        public List<Node> children;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }
}
