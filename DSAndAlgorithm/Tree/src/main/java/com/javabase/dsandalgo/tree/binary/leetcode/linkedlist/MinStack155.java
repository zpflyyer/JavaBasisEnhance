package com.javabase.dsandalgo.tree.binary.leetcode.linkedlist;

public class MinStack155 {

    public static void main(String[] args) {
        MinStack155 minStack = new MinStack155();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }

    private ListNode head;

    public MinStack155() {
    }

    public void push(int x) {
        // 链表头插法简历链表
        if (this.head == null) {
            this.head = new ListNode(x, null, x);
        } else {
            this.head = new ListNode(x, this.head, Math.min(this.head.min, x));
        }
    }

    public void pop() {
        if (this.head != null) {
            this.head = this.head.next;
        }
    }

    public int top() {
        if (this.head == null) {
            throw new IllegalStateException("stack is empty");
        }
        return this.head.val;
    }

    public int getMin() {
        if (this.head == null) {
            throw new IllegalStateException("stack is empty");
        }
        return this.head.min;
    }

    private static final class ListNode {
        private int val;
        private ListNode next;
        private int min;

        public ListNode(int val, ListNode next, int min) {
            this.val = val;
            this.next = next;
            this.min = min;
        }

        public int getVal() {
            return val;
        }

        public ListNode setVal(int val) {
            this.val = val;
            return this;
        }

        public ListNode getNext() {
            return next;
        }

        public ListNode setNext(ListNode next) {
            this.next = next;
            return this;
        }

        public int getMin() {
            return min;
        }

        public ListNode setMin(int min) {
            this.min = min;
            return this;
        }
    }
}
