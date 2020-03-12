package com.javabase.dsandalgo.tree.binary.leetcode.linkedlist;

public class LinkedListIntersection160 {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(2,
                new ListNode(6,
                        new ListNode(4, null)));
        ListNode list2 = new ListNode(1,
                new ListNode(5, null));
        System.out.println(new LinkedListIntersection160().getIntersectionNode(list1, list2));
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
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
