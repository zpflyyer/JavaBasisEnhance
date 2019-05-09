package com.javabase.dsandalgo.tree;

import com.sun.istack.internal.NotNull;
import lombok.*;

/**
 * 该BST的几乎全部算法实现都有一个前提：BST里没有重复元素
 * @param <T>
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BinarySearchTree<T extends Comparable<? super T>> {
    private BinaryNode<T> root;

    /**
     * 第一部分：查找算法
     */

    /**
     * 递归实现，可读性强
     * @param root 所查找的树
     * @param t
     * @return 包含t的节点的引用
     */
    private BinaryNode<T> find(BinaryNode<T> root, T t){
        if (root == null) return null;
        int compareResult = t.compareTo(root.element);
        if (compareResult == 0) return root ;
            //在子树里查找
        else if (compareResult < 0) return find(root.left, t);
        else return find(root.right, t);
    }
    /**
     * @param root 要查找的树的根节点
     * @param t
     * @return 该树中是否包含元素为t的节点
     */
    private boolean contains(BinaryNode<T> root, @NotNull T t){
        return find(root, t) != null;
    }
    public boolean contains0(BinaryNode<T> root, @NotNull T t){    //非递归实现,效率更高
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

    public BinaryNode<T> findMax(BinaryNode<T> root){
        BinaryNode<T> maxNode = root;//注意保护root指针
        if (maxNode != null){
            while (maxNode.right != null){
                maxNode = maxNode.right;
            }
        }
        return maxNode;
    }
    public BinaryNode<T> findMax(){
        return findMax(this.root);
    }

    public BinaryNode<T> findMin(BinaryNode<T> root){
        BinaryNode<T> minNode = root;//注意保护root指针
        if (minNode != null){
            while (minNode.left != null){
                minNode = minNode.left;
            }
        }
        return minNode;
    }
    public BinaryNode<T> findMin(){
        return findMin(this.root);
    }


    /**
     * 第二部分：插入算法
     */

    /**
     * @param root 待插入的树的root
     * @param toBeInserted
     * @return 插入t后新的树的root
     */
    private BinaryNode<T> insert(BinaryNode<T> root, @NotNull T toBeInserted){
        if (root == null)
            root = new BinaryNode<T>(toBeInserted ,null, null);

        int compareResult = root.element.compareTo(toBeInserted);
        if (compareResult == 0);//重复元素不插入
        else if (compareResult > 0) root.left = insert(root.left, toBeInserted);
        else root.right = insert(root.right, toBeInserted);
        return root;
    }
    public void insert(T toBeInserted){
        this.root = insert(root, toBeInserted);
    }

    /**
     * 第三部分：删除算法
     */

    /**
     * @param root
     * @param toBeRemoved
     * @return 删除toBeRemoved后的新树的root
     */
    private BinaryNode<T> remove(BinaryNode<T> root, T toBeRemoved){
        if (root != null){
            int compareResult = toBeRemoved.compareTo(root.getElement());
            if (compareResult > 0){
                root.right = remove(root.right, toBeRemoved);
            } else if(compareResult < 0){
                root.left = remove(root.left,toBeRemoved);
            } else {
                if (root.right != null && root.left != null) {//左右子树都不为空：使用右子树最小的节点值来占据要被删除的节点，之后删除右子树上最小的节点即可（最小节点的左子树为空）
                    T rightMin = findMin(root.right).getElement();
                    root.setElement(rightMin);
                    root.right = remove(root.right, rightMin);
                } else if (root.left != null) {
                    root = root.left;
                } else {
                    root = root.right;
                }
            }
        }
        return root;
    }
    public BinaryNode<T> remove(T toBeRemoved){
        this.root = remove(this.root, toBeRemoved);
        return this.root;
    }

    /**
     * 节点定义
     * @param <T>
     */
    @AllArgsConstructor
    @Getter
    @Setter
    public static class BinaryNode<T> {
        private T element;
        private BinarySearchTree.BinaryNode<T> left;
        private BinarySearchTree.BinaryNode<T> right;
    }
}
