package com.javabase.dsandalgo.tree.binary.leetcode.linkedlist;

public class PalindromeLinkedList234 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1, new ListNode(1, null)))));
        System.out.println(new PalindromeLinkedList234().isPalindrome(listNode));
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode walker = head;
        ListNode runner = head.next;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }

        // 右半部分
        ListNode rightHead;
        // 奇数个节点， walker位于正中央
        if (runner == null) {
            rightHead = new ListNode(walker.val, walker.next);
            walker.next = null;
        }
        // 偶数个节点，walker位于前半部分的末尾
        else {
            rightHead = walker.next;
            walker.next = null;
        }
        // 左半部分
        head = this.inverseList(head);

        while (head != null && rightHead != null) {
            if (head.val != rightHead.val) {
                return false;
            }
            head = head.next;
            rightHead = rightHead.next;
        }
        return head == null && rightHead == null;
    }

    private ListNode inverseList(ListNode listNode) {
        ListNode left = null;
        while (listNode != null) {
            ListNode right = listNode.next;
            listNode.next = left;
            left = listNode;
            listNode = right;
        }
        return left;
    }

    private static final class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
