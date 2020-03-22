package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import java.util.*;

public class NArrayTreePostOrderTraversal590 {

    public static void main(String[] args) {
        NArrayTreePostOrderTraversal590 nArrayTreePostOrderTraversal590 = new NArrayTreePostOrderTraversal590();
        Node root = new Node(1);
        List<Node> rootChildren = new ArrayList<>();
        rootChildren.add(new Node(3, Arrays.asList(new Node(5), new Node(6))));
        rootChildren.add(new Node(2));
        rootChildren.add(new Node(4));
        root.children = rootChildren;
        nArrayTreePostOrderTraversal590.postOrder(root);
    }

    public List<Integer> postOrder(Node root) {
        LinkedList<Integer> results = new LinkedList<>();
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                results.addFirst(node.val);
                if (node.children != null) {
                    node.children.forEach(stack::push);
                }
            }
        }
        return results;
    }

    private static final class Node {
        private int val;
        private List<Node> children;

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
