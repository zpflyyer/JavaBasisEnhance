package com.javabase.dsandalgo.tree.binary.search.balanced;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@SuppressWarnings("unchecked")
public class EasyRedBlackTree<T extends Comparable<? super T>> {

    // 红黑树里所有的空指针全部使用NIL代替
    public final static RedBlackTreeNode NIL = new RedBlackTreeNode<>(null, null, null, null, COLOR.BLACK);

    //红黑树初始状态:一个黑色的NIL节点
    private RedBlackTreeNode<T> root = NIL;

    private RedBlackTreeNode<T> recentInsertedNode = NIL;

    /**
     * 插入算法
     */
    private RedBlackTreeNode<T> newNodeFor(T t, RedBlackTreeNode<T> parent) {
        // 强制新创建出来的节点颜色为红色，红黑树的再平衡交给专门的re-balance函数去做
        return new RedBlackTreeNode<>(t, NIL, NIL, parent, COLOR.RED);
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

    // re-balance function
    private void afterInsert() {
        if (!this.recentInsertedNode.equals(NIL)) {
            RedBlackTreeNode<T> z = this.recentInsertedNode;
            //如果z的父亲p是红色，说明z的的祖父g是黑色，且z的祖父g高度<=根的高度
            //如果z的父亲p是黑色，那么p有可能是NIL
            while (z.parent.color == COLOR.RED) {
                RedBlackTreeNode<T> p = z.parent;
                RedBlackTreeNode<T> g = p.parent;
                if (g.left.equals(p)) {
                    if (g.right.color == COLOR.RED) {
                        p.color = COLOR.BLACK;
                        g.right.color = COLOR.BLACK;
                        g.color = COLOR.RED;
                        z = g;
                    } else {
                        if (z == p.right) {
                            this.leftRotate(p);
                            p = g.left;
                            z = p.left;
                        }
                        g.color = COLOR.RED;
                        p.color = COLOR.BLACK;
                        this.rightRotate(g);
                        //结束时需要修复根节点的引用
                        if (p.parent == NIL) {
                            this.root = p;
                        }
                    }
                } else {
                    if (g.left.color == COLOR.RED) {
                        p.color = COLOR.BLACK;
                        g.left.color = COLOR.BLACK;
                        g.color = COLOR.RED;
                        z = g;
                    } else {
                        if (z == p.left) {
                            this.rightRotate(p);
                            p = g.right;
                            z = p.right;
                        }
                        g.color = COLOR.RED;
                        p.color = COLOR.BLACK;
                        this.leftRotate(g);
                        //结束时需要修复根节点的引用
                        if (p.parent == NIL) {
                            this.root = p;
                        }
                    }
                }
            }
            // don't forget
            this.root.color = COLOR.BLACK;
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
        private COLOR color;

        public RedBlackTreeNode(T element, RedBlackTreeNode<T> left, RedBlackTreeNode<T> right, RedBlackTreeNode<T> parent, COLOR color) {
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

    /**
     * 颜色定义
     */

    public enum COLOR {
        RED, BLACK
    }
}