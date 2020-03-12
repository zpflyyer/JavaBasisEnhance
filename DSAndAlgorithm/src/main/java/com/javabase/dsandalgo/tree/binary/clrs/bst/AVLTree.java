package com.javabase.dsandalgo.tree.binary.clrs.bst;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {
    private static final Integer ALLOWED_IMBALANCE = 1;

    //第一部分：插入算法

    @Override
    protected AVLTreeNode<T> newNodeFor(T t) {
        return new AVLTreeNode<>(t);
    }

    @Override
    protected AVLTreeNode<T> afterInsert(BinaryNode<T> root,T toBeInserted) {
        return reBalance((AVLTreeNode<T>) root);//再平衡子树
    }

    @Override
    protected BinaryNode<T> afterRemove(BinaryNode<T> root) {
        return reBalance((AVLTreeNode<T>) root);//再平衡子树
    }


    /**
     * 再平衡算法：每次插入新节点后，仅需要沿着该节点向上查找第一个不平衡的子树，对该子树进行再平衡操作即可使得整棵树恢复平衡
     * @param root root node before reBalance
     * @return root after reBalance
     */
    private AVLTreeNode<T> reBalance(AVLTreeNode<T> root){
        //insert时：root不会为null，且新插入节点不需要再平衡（仅有一个节点的子树）和更新高度信息（叶子节点高度为0）
        //delete时：root可能为1.null;  2.一个叶子节点; 3.非叶子节点。情形2不需要再平衡， 2,3两种情形都需要更新root的高度信息.
        // 因此这里root节点为叶子节点时，并不直接返回root，就是因为delete操作后，依然要更新root的高度
        if (root == null){
            return null;
        }

        Integer leftHeight = height((AVLTreeNode<T>) root.getLeft());
        Integer rightHeight = height((AVLTreeNode<T>) root.getRight());
        //第一步：检查是否平衡以及在不平衡时进行再平衡操作，这个过程中左右子树的高度可能发生变化。
        //  1.该操作每次insert操作里最多只发生一次,因为最小不平衡子树经过reBalance之后高度会继续保持原来的高度
        //  2.但是remove不一样，因为最小不平衡子树经过调整后高度可能下降1，即在整个树里再次产生一个新的最小不平衡子树，即可能递归调整至整棵树的root
        if (leftHeight - rightHeight > ALLOWED_IMBALANCE){
            /*
              insert后不会发生==的情形，但是remove后会
             */
            if (height((AVLTreeNode<T>) root.getLeft().getLeft()) >= height((AVLTreeNode<T>) root.getLeft().getRight())){
                 root = rotateWithLeftChild(root);//左旋
            } else {
                root = doubleWithLeftChild(root);//双旋转：先右后左
            }
        } else if (rightHeight - leftHeight > ALLOWED_IMBALANCE){
            if (height((AVLTreeNode<T>) root.getRight().getRight()) >= height((AVLTreeNode<T>) root.getRight().getLeft())){
                root = rotateWithRightChild(root);//右旋转
            } else {
                root = doubleWithRightChild(root);//双旋转：先左后右
            }
        }

        //第二步： 更新newRoot节点的height，每次有新节点插入或者节点删除，则根节点和变动节点以及二者之间路径上的节点的高度信息都要更新
        root.height = Math.max(height((AVLTreeNode<T>) root.getLeft()), height((AVLTreeNode<T>) root.getRight())) + 1;

        return root;
    }

    /**
     * 右旋转
     * @param root ''
     * @return ''
     */
    private AVLTreeNode<T> rotateWithLeftChild(AVLTreeNode<T> root){
        AVLTreeNode<T> leftChild = (AVLTreeNode<T>) root.getLeft();
        root.setLeft(leftChild.getRight());
        leftChild.setRight(root);

        root.height = Math.max(height((AVLTreeNode<T>) root.getLeft()), height((AVLTreeNode<T>) root.getRight())) + 1;
        leftChild.height = Math.max(height((AVLTreeNode<T>) leftChild.getLeft()), root.height) + 1;

        return leftChild;
    }

    /**
     * 先左旋左孩子，再右旋root节点
     * @param root ''
     * @return ''
     */
    private AVLTreeNode<T> doubleWithLeftChild(AVLTreeNode<T> root){
        root.setLeft(rotateWithRightChild((AVLTreeNode<T>) root.getLeft()));
        return rotateWithLeftChild(root);
    }

    /**
     * 左旋转
     * @param root ''
     * @return ''
     */
    private AVLTreeNode<T> rotateWithRightChild(AVLTreeNode<T> root){
        AVLTreeNode<T> rightChild = (AVLTreeNode<T>) root.getRight();
        root.setRight(rightChild.getLeft());
        rightChild.setLeft(root);

        root.height = Math.max(height((AVLTreeNode<T>) root.getLeft()), height((AVLTreeNode<T>) root.getRight())) + 1;
        rightChild.height = Math.max(height((AVLTreeNode<T>) rightChild.getRight()), root.height) + 1;

        return rightChild;
    }

    /**
     * 先右旋右孩子，再左旋root节点
     * @param root ''
     * @return ''
     */
    private AVLTreeNode<T> doubleWithRightChild(AVLTreeNode<T> root){
        root.setRight(rotateWithLeftChild((AVLTreeNode<T>) root.getRight()));
        return rotateWithRightChild(root);
    }

    private Integer height(AVLTreeNode<T> node){
        return node == null ? -1 : node.getHeight();
    }

    public static class AVLTreeNode<T> extends BinarySearchTree.BinaryNode<T>{
        @Getter
        @Setter
        @NonNull
        private Integer height;

        public AVLTreeNode(T element){
            this(element, null, null, 0);
        }

        public AVLTreeNode(T element, AVLTreeNode<T> left,AVLTreeNode<T> right, Integer height){
            super(element,left,right);
            this.height = height;
        }
    }
}
