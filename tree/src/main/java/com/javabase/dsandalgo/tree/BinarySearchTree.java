package com.javabase.dsandalgo.tree;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BinarySearchTree<T extends Comparable<? super T>> {
    private BinaryNode<T> root;

    @AllArgsConstructor
    @RequiredArgsConstructor
    private static class BinaryNode<T> {
        @NonNull
        private T element;
        private BinarySearchTree.BinaryNode<T> left;
        private BinarySearchTree.BinaryNode<T> right;
    }

    public boolean containsRecursive(BinaryNode<T> root, @NotNull T t){
        if(root == null)
            return false;

        int compareResult = root.element.compareTo(t);
        if (compareResult == 0) return true;
        else if (compareResult > 0)
            return contains(root.left, t);
        else return contains(root.right, t);
    }

    //非递归实现,效率更高
    public boolean contains(BinaryNode<T> root, @NotNull T t){
        while (root != null){
            int compareResult = root.element.compareTo(t);
            if (compareResult == 0) break;//break而不是直接返回,使得方法的return归在一处
            else if (compareResult > 0) root = root.left;
            else root = root.right;
        }
        return root != null;
    }

    public boolean contains(@NotNull T t){
        return contains(root, t);
    }

    /**
     * 如何建立一棵树
     * 这个方法实现有几点做的很好：
     *  1.采取了二叉树搜索的算法，最后一个搜索的节点就是要插入节点的父亲
     *  2.insert的返回结果是新树的root,代码很统一高效
     */
    private BinaryNode<T> insert(BinaryNode<T> root, @NotNull T t){
        if (root == null)
            root = new BinaryNode<T>(t);

        int compareResult = root.element.compareTo(t);
        if (compareResult == 0);
        else if (compareResult > 0) root.left = insert(root.left, t);
        else root.right = insert(root.right, t);
        return root;
    }

    private BinarySearchTree<T> insert(BinarySearchTree<T> tree, T t){
        //insert后root的指向可能会发生变化,而传入参数的变化并不影响原来参数的变化
        tree.root = insert(tree.root, t);
        return tree;
    }

    public void insert(T t){
        insert(this, t);
    }



//    public BinaryNode<T> findMax(){}
//
//    public BinaryNode<T> findMin(){}
}
