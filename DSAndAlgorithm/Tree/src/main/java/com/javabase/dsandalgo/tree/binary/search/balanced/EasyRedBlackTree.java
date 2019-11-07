package com.javabase.dsandalgo.tree.binary.search.balanced;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@SuppressWarnings("unchecked")
public class EasyRedBlackTree<T extends Comparable<? super T>> {

    public static final String RED = "red";
    public static final String BLACK = "black";
    // 红黑树里所有的空指针全部使用NIL代替
    public final static RedBlackTreeNode NIL = new RedBlackTreeNode<>(null, null, null, null, BLACK);

    //红黑树初始状态:一个黑色的NIL节点
    private RedBlackTreeNode<T> root = NIL;

    private RedBlackTreeNode<T> recentInsertedNode = NIL;

    /**
     * 插入算法
     */
    private RedBlackTreeNode<T> newNodeFor(T t, RedBlackTreeNode<T> parent) {
        // 强制新创建出来的节点颜色为红色，红黑树的再平衡交给专门的re-balance函数去做
        return new RedBlackTreeNode<>(t, NIL, NIL, parent, RED);
    }

    public void insert(T toBeInserted) {
        this.root = insert(NIL, this.root, toBeInserted);
        this.afterInsert();
    }

    private RedBlackTreeNode<T> insert(RedBlackTreeNode<T> parent, RedBlackTreeNode<T> current, T tobeInserted) {
        // 仅新插入节点的父亲需要配置
        if (current.equals(NIL)) {
            current = this.newNodeFor(tobeInserted, parent);
            current.parent = parent;
            this.recentInsertedNode = current;
        } else {
            int compareResult = current.getElement().compareTo(tobeInserted);
            if (compareResult > 0) {
                current.left = this.insert(current, current.getLeft(), tobeInserted);
            } else if (compareResult < 0) {
                current.right = this.insert(current, current.getRight(), tobeInserted);
            }
        }
        return current;
    }

    public void remove(RedBlackTreeNode<T> toBeRemovedNode) {
        if (toBeRemovedNode != NIL) {
            RedBlackTreeNode<T> x;
            String y_original_color = toBeRemovedNode.color;
            if (toBeRemovedNode.left == NIL) {
                x = toBeRemovedNode.right;
                this.transplant(toBeRemovedNode, x);
            } else if (toBeRemovedNode.right == NIL) {
                x = toBeRemovedNode.left;
                this.transplant(toBeRemovedNode, x);
            } else {
                RedBlackTreeNode<T> successor = this.findSuccessor(toBeRemovedNode);
                y_original_color = successor.color;
                x = successor.right;
                toBeRemovedNode.element = successor.element;
                this.transplant(successor, successor.right);
            }
            if (y_original_color.equals(BLACK)){
                this.afterRemove(x);
            }
        }
    }

    // x is dual black node and w is his brother(which is not NIL)
    public void afterRemove(RedBlackTreeNode<T> x){
        while (x.color.equals(BLACK) && x != this.root){
            if (x == x.parent.left){
                RedBlackTreeNode<T> w = x.parent.right;
                if (w.color.equals(RED)){
                    w.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color.equals(BLACK) && w.right.color.equals(BLACK)){
                    w.color = RED;
                    //如果是从情形1进入的情形2，则x.parent.color = red，即新的x是红色节点，退出循环
                    x = x.parent;
                }
                else {
                    if (w.right.color.equals(BLACK)){
                        w.color = RED;
                        w.left.color = BLACK;
                        this.rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    this.leftRotate(x.parent);
                    //情形4终结时，x指向黑色节点，故而需要手动结束循环
                    break;
                }
            } else {
                RedBlackTreeNode<T> w = x.parent.left;
                if (w.color.equals(RED)){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.left.color.equals(BLACK) && w.right.color.equals(BLACK)) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if (w.left.color.equals(BLACK)) {
                        w.color = RED;
                        w.right.color = BLACK;
                        this.leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    this.rightRotate(x.parent);
                    break;
                }
            }
        }
        // 去除红黑节点上的红色或者如果是根，整体高降低1即可
        x.color = BLACK;
    }

    // replace old subtree whose root is oldRoot with new subTree whose root is newRoot
    public void transplant(RedBlackTreeNode<T> oldRoot, RedBlackTreeNode<T> newRoot){
        if (oldRoot.parent == NIL){
            this.root = newRoot;
        }
        if (oldRoot.parent.left == oldRoot){
            oldRoot.parent.left = newRoot;
        } else {
            oldRoot.parent.right = newRoot;
        }
        newRoot.parent = oldRoot.parent;
    }

  // re-balance function
    private void afterInsert() {
        if (!this.recentInsertedNode.equals(NIL)) {
            RedBlackTreeNode<T> z = this.recentInsertedNode;
            //如果z的父亲p是红色，说明z的的祖父g是黑色，且z的祖父g高度<=根的高度
            //如果z的父亲p是黑色，那么p有可能是NIL
            while (z.parent.color.equals(RED)) {
                RedBlackTreeNode<T> p = z.parent;
                RedBlackTreeNode<T> g = p.parent;
                if (g.left.equals(p)) {
                    if (g.right.color.equals(RED)) {
                        p.color = BLACK;
                        g.right.color = BLACK;
                        g.color = RED;
                        z = g;
                    } else {
                        if (z == p.right) {
                            this.leftRotate(p);
                            p = g.left;
                            z = p.left;
                        }
                        g.color = RED;
                        p.color = BLACK;
                        this.rightRotate(g);
                        //结束时需要修复根节点的引用
                        if (p.parent == NIL) {
                            this.root = p;
                        }
                    }
                } else {
                    if (g.left.color.equals(RED)) {
                        p.color = BLACK;
                        g.left.color = BLACK;
                        g.color = RED;
                        z = g;
                    } else {
                        if (z == p.left) {
                            this.rightRotate(p);
                            p = g.right;
                            z = p.right;
                        }
                        g.color = RED;
                        p.color = BLACK;
                        this.leftRotate(g);
                        //结束时需要修复根节点的引用
                        if (p.parent == NIL) {
                            this.root = p;
                        }
                    }
                }
            }
            // don't forget
            this.root.color = BLACK;
        }
    }

    private void leftRotate(RedBlackTreeNode<T> p) {
        RedBlackTreeNode<T> g = p.parent;
        RedBlackTreeNode<T> z = p.right;
        RedBlackTreeNode<T> b = z.left;
        p.right = b;
        if (b != NIL) {
            b.parent = p;
        }

        p.parent = z;
        z.left = p;

        z.parent = g;
        if (g.left == p) {
            g.left = z;
        } else {
            g.right = z;
        }
        if (z.parent == NIL) {
            this.root = z;
        }
    }

    private void rightRotate(RedBlackTreeNode<T> p) {
        RedBlackTreeNode<T> g = p.parent;
        RedBlackTreeNode<T> z = p.left;
        RedBlackTreeNode<T> b = z.right;

        p.left = b;
        if (b != NIL) {
            b.parent = p;
        }

        p.parent = z;
        z.right = p;

        z.parent = g;
        if (g.left == p) {
            g.left = z;
        } else {
            g.right = z;
        }
        if (z.parent == NIL) {
            this.root = z;
        }
    }

    /**
     * 查找算法
     */

    public boolean contains(T element) {
        return !this.search(element).equals(NIL);
    }

    public RedBlackTreeNode<T> search(T element) {
        return this.search(this.root, element);
    }

    private RedBlackTreeNode<T> search(RedBlackTreeNode<T> root, T element) {
        if (root.equals(NIL)) {
            return NIL;
        }

        int compareResult = root.getElement().compareTo(element);
        if (compareResult < 0) {
            return this.search(root.right, element);
        } else if (compareResult > 0) {
            return this.search(root.left, element);
        } else {
            return root;
        }
    }

    public RedBlackTreeNode<T> findMin() {
        return this.findMin(this.root);
    }

    private RedBlackTreeNode<T> findMin(RedBlackTreeNode<T> root) {
        if (!root.equals(NIL)) {
            while (!root.getLeft().equals(NIL)) {
                root = root.getLeft();
            }
        }
        return root;
    }

    public RedBlackTreeNode<T> findMax() {
        return this.findMax(this.root);
    }

    private RedBlackTreeNode<T> findMax(RedBlackTreeNode<T> root) {
        if (!root.equals(NIL)) {
            while (!root.getRight().equals(NIL)) {
                root = root.getRight();
            }
        }
        return root;
    }

    public RedBlackTreeNode<T> findPredecessor(RedBlackTreeNode<T> current) {
        //NIL没有前置和后继节点
        if (current.equals(NIL)) {
            return NIL;
        }

        // 寻找左子树上最大节点
        if (!current.getLeft().equals(NIL)) {
            return findMax(current.getLeft());
        }

        // 左子树为空，说明该节点的子树上的元素全部都比其更大
        RedBlackTreeNode<T> parent = current.getParent();
        while (parent != NIL && !parent.getRight().equals(current)) {
            current = parent;
            parent = parent.getParent();
        }

        // parent可能为NIL，表示该节点值为这棵树上最小的节点了
        return parent;
    }

    public RedBlackTreeNode<T> findSuccessor(RedBlackTreeNode<T> current) {
        if (current.equals(NIL)) {
            return NIL;
        }

        if (!current.getRight().equals(NIL)) {
            return this.findMin(current.getRight());
        }

        RedBlackTreeNode<T> parent = current.getParent();
        while (!parent.equals(NIL) && !parent.getLeft().equals(current)) {
            current = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    /**
     * 节点定义
     */

    @Getter
    @Setter
    public static class RedBlackTreeNode<T> {
        private T element;
        private RedBlackTreeNode<T> left;
        private RedBlackTreeNode<T> right;
        private RedBlackTreeNode<T> parent;
        @NonNull
        private String color;

        public RedBlackTreeNode(T element, RedBlackTreeNode<T> left, RedBlackTreeNode<T> right, RedBlackTreeNode<T> parent, String color) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.color = color;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof RedBlackTreeNode) {
                if (((RedBlackTreeNode) obj).getElement() != null) {
                    return ((RedBlackTreeNode) obj).getElement().equals(this.element);
                }
                return this.element == null;
            } else {
                return false;
            }
        }
    }
}
