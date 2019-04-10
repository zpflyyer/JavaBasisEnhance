package com.javabase.dsandalgo.tree;

import lombok.AllArgsConstructor;

/**
 * 二叉搜索树节点定义
 */
@AllArgsConstructor
public class BinaryNode<T> {
    private T element;
    private BinaryNode<T> left;
    private BinaryNode<T> right;
}
