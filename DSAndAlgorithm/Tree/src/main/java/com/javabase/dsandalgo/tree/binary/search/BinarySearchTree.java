package com.javabase.dsandalgo.tree.binary.search;

import lombok.*;

/**
 * 该BST的几乎全部算法实现都有一个前提：BST里没有重复元素
 * @param <T>
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BinarySearchTree<T extends Comparable<? super T>> {
    //由于子类和BST不在相同的包里，因此除了子类本身以外，在BST的包外无法访问BST的protected成员,
    //我们希望使用子类时能够读取这些语义时可以将它们定义为public，而不是protected。
    //但是对于域，我们有public的getter/setter，因此可以继续保持域为private
    private BinaryNode<T> root;

    //第一部分：插入算法

    /**
     * 思路源于 AbstractExecutorService的newTaskFor方法：其允许子类ExecutorService实现自己的任务封装
     * 整棵树包括root节点都是由该方法创建的
     * @param t 待封装节点值
     * @return 为t创建一个树节点，实现AVLTree继承
     */
    protected BinaryNode<T> newNodeFor(T t){
        return new BinaryNode<>(t);
    }

    /**
     * 默认do nothing
     * 由于树是递归定义的，因此在每次往子树里insert一个节点后可能需要对子树进行调整，譬如自平衡的BST需要reBalance操作
     * @param root ''
     * @return ''
     */
    protected BinaryNode<T> afterInsert(BinaryNode<T> root){
        return root;//do nothing
    }

    /**
     * 默认do nothing
     * 由于树是递归定义的，因此在每次从里delete一个节点后可能需要对子树进行调整，譬如自平衡的BST需要reBalance操作
     * @param root ''
     * @return ''
     */
    protected BinaryNode<T> afterRemove(BinaryNode<T> root){
        return root;//do nothing
    }

    /**
     * insert方法应该只包含最基本的搜索二叉树的插入算法，而不绑定任何特定的节点类型
     * @param root 待插入的树的root
     * @param toBeInserted 待插入元素
     * @return 插入t后新的树的root
     */
    private BinaryNode<T> insert(BinaryNode<T> root, T toBeInserted){
        if (root == null) {
            root = newNodeFor(toBeInserted);//调用newNodeFor方法使得Node的类型可以在运行时动态决定（重写newNodeFor方法）
        }

        int compareResult = root.element.compareTo(toBeInserted);
        if (compareResult > 0) root.left = insert(root.left, toBeInserted);
        else if (compareResult < 0) root.right = insert(root.right, toBeInserted);

        return afterInsert(root);
    }

    /**
     * @throws NullPointerException toBeInserted为空时
     * @param toBeInserted 待插入元素
     */
    public void insert(T toBeInserted){
        this.root = insert(root, toBeInserted);
    }


     //第二部分：查找算法


    /**
     * 递归实现，可读性强
     * @param root 所查找的树
     * @param t 节点值
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
     * @param t 节点值
     * @return 该树中是否包含元素为t的节点
     */
    private boolean contains(BinaryNode<T> root, T t){
        return find(root, t) != null;
    }

    /**
     * @param root 要查找的树的根节点
     * @param t 节点值
     * @return 该树中是否包含元素为t的节点
     */
    private boolean contains0(BinaryNode<T> root, T t){    //非递归实现,效率更高
        while (root != null){
            int compareResult = root.element.compareTo(t);
            if (compareResult == 0) break;//break而不是直接返回,使得方法的return归在一处
            else if (compareResult > 0) root = root.left;
            else root = root.right;
        }
        return root != null;
    }

    /**
     * @throws NullPointerException if t is null
     * @param t element to find
     * @return true if root contains node with element t
     */
    public boolean contains(T t){
        return contains(root, t);
    }

    /**
     * @param root ''
     * @return ''
     */
    private BinaryNode<T> findMax(BinaryNode<T> root){
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

    /**
     * @param root ''
     * @return ''
     */
    private BinaryNode<T> findMin(BinaryNode<T> root){
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

     //第三部分：删除算法

    /**
     * @param root 从root节点为根的树里删除
     * @param toBeRemoved 待删除元素
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
        return afterRemove(root);
    }

    /**
     * @throws NullPointerException if t is null
     * @param t element to be removed
     * @return root after remove t
     */
    public BinaryNode<T> remove(T t){
        this.root = remove(this.root, t);
        return this.root;
    }

    //第四部分：节点定义

    /**
     * constructor will throw NullPointerException if element is null when construct
     * @param <T> 元素类型
     */
    @AllArgsConstructor
    @RequiredArgsConstructor
    @Getter
    @Setter
    public static class BinaryNode<T> {
        @NonNull //@NotNull并不会实际检查也不会抛出异常，但@NonNull可以，这样可以避免树中没有element为null的节点存在
        private T element;
        private BinarySearchTree.BinaryNode<T> left;
        private BinarySearchTree.BinaryNode<T> right;
    }
}
