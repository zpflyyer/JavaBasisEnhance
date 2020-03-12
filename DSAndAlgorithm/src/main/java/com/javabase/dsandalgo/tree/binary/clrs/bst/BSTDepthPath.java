package com.javabase.dsandalgo.tree.binary.clrs.bst;

import com.alibaba.fastjson.JSON;
import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.*;

public class BSTDepthPath {

    public static void main(String[] args) {
        Integer[] array = {4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8};
        TreeNode root = TreeNode.fromIntegerArray(array);
        BSTDepthPath bstDepthPath = new BSTDepthPath();
        System.out.println(JSON.toJSON(bstDepthPath.deepestPath(root)));
    }

    private Map<TreeNode, TreeNode> child2Parent = new HashMap<>();

    private List<TreeNode> deepestPath(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        TreeNode deepestNode = this.bfsTraversal(root);
        if (root != null) {
            result.add(deepestNode);
            TreeNode parent = child2Parent.get(deepestNode);
            while (parent != null) {
                result.add(parent);
                parent = child2Parent.get(parent);
            }
        }
        return result;
    }

    private TreeNode bfsTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode lastOne = null;
        while (!queue.isEmpty()) {
            // 最后一个出队的节点即最深一层的节点
            lastOne = queue.remove();
            if (lastOne.left != null) {
                this.child2Parent.put(lastOne.left, lastOne);
                queue.add(lastOne.left);
            }
            if (lastOne.right != null) {
                this.child2Parent.put(lastOne.right, lastOne);
                queue.add(lastOne.right);
            }
        }

        return lastOne;
    }

}
