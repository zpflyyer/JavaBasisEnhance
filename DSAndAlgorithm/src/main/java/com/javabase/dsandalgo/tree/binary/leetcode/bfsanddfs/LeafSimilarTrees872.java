package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import com.javabase.dsandalgo.tree.binary.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees872 {

    public static void main(String[] args) {
        Integer[] array = {3, 5, 1, 6, 2, 9, 8, null, null, 7, 4, null, null, null, null};
        TreeNode root = TreeNode.fromIntegerArray(array);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        }
        List<TreeNode> leavesSequence1 = this.leavesSequence(root1);
        List<TreeNode> leavesSequence2 = this.leavesSequence(root2);
        if (leavesSequence1.size() != leavesSequence2.size()) {
            return false;
        }
        for (int i = 0; i < leavesSequence1.size(); i++) {
            if (leavesSequence1.get(i).val != leavesSequence2.get(i).val) {
                return false;
            }
        }
        return true;
    }

    private List<TreeNode> leavesSequence(TreeNode root) {
        List<TreeNode> leavesList = new ArrayList<>();
        if (root.left == null && root.right == null) {
            leavesList.add(root);
        } else {
            if (root.left != null) {
                leavesList.addAll(this.leavesSequence(root.left));
            }
            if (root.right != null) {
                leavesList.addAll(this.leavesSequence(root.right));
            }
        }
        return leavesList;
    }

}
