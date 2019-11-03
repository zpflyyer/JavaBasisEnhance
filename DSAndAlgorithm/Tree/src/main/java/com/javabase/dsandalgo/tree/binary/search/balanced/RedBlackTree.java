//package com.javabase.dsandalgo.tree.binary.search.balanced;
//
//// copy from the author of this book, cant understand program like this
//
//import lombok.Getter;
//
///**
// * Implements a red-black tree.
// * Note that all "matching" is based on the compareTo method.
// *
// * @author Mark Allen Weiss
// */
//@Getter
//@Deprecated
//public class RedBlackTree<T extends Comparable<? super T>> {
//
//    /**
//     * 0. 红黑树的定义和基本基本操作
//     */
//
//    @Getter
//    public static class RedBlackNode<AnyType> {
//        // Constructors
//        RedBlackNode(AnyType theElement) {
//            this(theElement, null, null);
//        }
//
//        RedBlackNode(AnyType theElement, RedBlackNode<AnyType> lt, RedBlackNode<AnyType> rt) {
//            element = theElement;
//            left = lt;
//            right = rt;
//            color = RedBlackTree.BLACK;
//        }
//
//        AnyType element;    // The data in the node
//        RedBlackNode<AnyType> left;       // Left child
//        RedBlackNode<AnyType> right;      // Right child
//        int color;      // Color
//    }
//
//    private RedBlackNode<T> header;
//    private RedBlackNode<T> nullNode;
//
//    private static final int BLACK = 1;    // BLACK must be 1
//    private static final int RED = 0;
//
//    // Used in insert routine and its helpers
//    private RedBlackNode<T> current;
//    private RedBlackNode<T> parent;
//    private RedBlackNode<T> grand;
//    private RedBlackNode<T> great;
//
//    /**
//     * Construct the tree.
//     */
//    RedBlackTree() {
//        nullNode = new RedBlackNode<>(null);
//        nullNode.left = nullNode.right = nullNode;
//        header = new RedBlackNode<>(null);
//        header.left = header.right = nullNode;
//    }
//
//    /**
//     * Compare item and t.element, using compareTo, with
//     * caveat that if t is header, then item is always larger.
//     * This routine is called if is possible that t is header.
//     * If it is not possible for t to be header, use compareTo directly.
//     */
//    private int compare(T item, RedBlackNode<T> t) {
//        return t == header ? 1 : item.compareTo(t.element);
//    }
//
//    /**
//     * Test if the tree is logically empty.
//     *
//     * @return true if empty, false otherwise.
//     */
//    private boolean isEmpty() {
//        return header.right == nullNode;
//    }
//
//    /**
//     * Make the tree logically empty.
//     */
//    public void makeEmpty() {
//        header.right = nullNode;
//    }
//
//    /**
//     * Print the tree contents in sorted order.
//     */
//    public void printTree() {
//        if (isEmpty())
//            System.out.println("Empty tree");
//        else
//            printTree(header.right);
//    }
//
//    /**
//     * Internal method to print a subtree in sorted order.
//     *
//     * @param t the node that roots the subtree.
//     */
//    private void printTree(RedBlackNode<T> t) {
//        if (t != nullNode) {
//            printTree(t.left);
//            System.out.println(t.element);
//            printTree(t.right);
//        }
//    }
//
//    /**
//     * 1.搜索算法
//     */
//
//    RedBlackNode<T> getRoot(){
//        return header.getRight();
//    }
//
//    /**
//     * Find the smallest item  the tree.
//     *
//     * @return the smallest item or throw UnderflowExcepton if empty.
//     */
//    RedBlackNode<T> findMin() {
//        if (isEmpty())
//            throw new UnderflowException();
//
//        RedBlackNode<T> minNode = header.right;
//
//        while (minNode.left != nullNode)
//            minNode = minNode.left;
//
//        return minNode;
//    }
//
//    /**
//     * Find the largest item in the tree.
//     *
//     * @return the largest item or throw UnderflowExcepton if empty.
//     */
//    RedBlackNode<T> findMax() {
//        if (isEmpty())
//            throw new UnderflowException();
//
//        RedBlackNode<T> maxNode = header.right;
//
//        while (maxNode.right != nullNode)
//            maxNode = maxNode.right;
//
//        return maxNode;
//    }
//
//    /**
//     * Find an item in the tree.
//     *
//     * @param x the item to search for.
//     * @return true if x is found; otherwise false.
//     */
//    boolean contains0(T x) {
//        nullNode.element = x;
//        current = header.right;
//
//        while(current != nullNode) {
//            if (x.compareTo(current.element) < 0)
//                current = current.left;
//            else if (x.compareTo(current.element) > 0)
//                current = current.right;
//            else break;
//        }
//        return current != nullNode;
//    }
//
//    private boolean contains(RedBlackNode<T> root, T t){
//        if (root == nullNode) return false;
//        else {
//            int compareResult = compare(t, root);
//            if (compareResult == 0){
//                return true;
//            } else if (compareResult > 0){
//                return contains(root.getRight(), t);
//            } else return contains(root.getLeft(), t);
//        }
//    }
//
//
//    /**
//     * 2.插入算法
//     */
//
//    /**
//     * Insert into the tree.
//     *
//     * @param item the item to insert.
//     */
//    void insert(T item) {
//        current = parent = grand = header;
//
//        nullNode.element = item;
//
//        while (compare(item, current) != 0) {
//            great = grand;
//            grand = parent;
//            parent = current;
//            current = compare(item, current) < 0 ?
//                    current.left : current.right;
//            if (current.left.color == RED && current.right.color == RED) //在搜寻item应该被插入的位置的过程中将所有 red brothers和其父亲颜色对调
//                // ，这样最后insert item时并不会发生旋转子树的根为红色的情形，即自顶向下调整而非自底向上调整，从而node中不必存储parent域
//                handleReorient(item);
//        }
//
//        // item已经在树里， do nothing and return
//        if (current != nullNode)
//            return;
//
//        // Attach to parent
//        current = new RedBlackNode<>(item, nullNode, nullNode);
//        if (compare(item, parent) < 0)
//            parent.left = current;
//        else
//            parent.right = current;
//        handleReorient(item);//parent和uncle节点不会同时为red
//    }
//
//    /**
//     * Internal routine that is called during an insertion
//     * if a node has two red children. Performs flip and rotations.
//     *
//     * @param item the item being inserted.
//     */
//    private void handleReorient(T item) {
//
//        // Do the color flip
//        current.color = RED;
//        current.left.color = BLACK;
//        current.right.color = BLACK;
//
//        if (parent.color == RED)   // Have to rotate
//        {
//            grand.color = RED;
//            if ((compare(item, grand) < 0) !=
//                    (compare(item, parent) < 0))
//                parent = rotate(item, grand);  // Start dbl rotate
//            current = rotate(item, great);
//            current.color = BLACK;
//        }
//        header.right.color = BLACK; // Make root black
//    }
//
//    /**
//     * Internal routine that performs a single or double rotation.
//     * Because the result is attached to the parent, there are four cases.
//     * Called by handleReorient.
//     *
//     * @param item   the item in handleReorient.
//     * @param parent the parent of the root of the rotated subtree.
//     * @return the root of the rotated subtree.
//     */
//    private RedBlackNode<T> rotate(T item, RedBlackNode<T> parent) {
//        if (compare(item, parent) < 0)
//            return parent.left = compare(item, parent.left) < 0 ?
//                    rotateWithLeftChild(parent.left) :  // LL
//                    rotateWithRightChild(parent.left);  // LR
//        else
//            return parent.right = compare(item, parent.right) < 0 ?
//                    rotateWithLeftChild(parent.right) :  // RL
//                    rotateWithRightChild(parent.right);  // RR
//
//    }
//
//    /**
//     * Rotate binary tree node with left child.
//     */
//    private RedBlackNode<T> rotateWithLeftChild(RedBlackNode<T> k2) {
//        RedBlackNode<T> k1 = k2.left;
//        k2.left = k1.right;
//        k1.right = k2;
//        return k1;
//    }
//
//    /**
//     * Rotate binary tree node with right child.
//     */
//    private RedBlackNode<T> rotateWithRightChild(RedBlackNode<T> k1) {
//        RedBlackNode<T> k2 = k1.right;
//        k1.right = k2.left;
//        k2.left = k1;
//        return k2;
//    }
//
//    /**
//     * 3.删除算法
//     */
//
//    /**
//     * Remove from the tree.
//     *
//     * @param x the item to remove.
//     * @throws UnsupportedOperationException if called.
//     */
//    public void remove(T x) {
//        throw new UnsupportedOperationException();
//    }
//
//
//    // Test program
//    public static void main(String[] args) {
//        RedBlackTree<Integer> t = new RedBlackTree<>();
//        final int NUMS = 400000;
//        final int GAP = 35461;
//
//        System.out.println("Checking... (no more output means success)");
//
//        for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
//            t.insert(i);
//
//        if (t.findMin().getElement() != 1 || t.findMax().getElement() != NUMS - 1)
//            System.out.println("FindMin or FindMax error!");
//
//        for (int i = 1; i < NUMS; i++)
//            if (!t.contains0(i))
//                System.out.println("Find error1!");
//    }
//}
