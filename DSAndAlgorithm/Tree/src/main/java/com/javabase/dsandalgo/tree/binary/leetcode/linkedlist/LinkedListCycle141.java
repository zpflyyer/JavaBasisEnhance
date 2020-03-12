package com.javabase.dsandalgo.tree.binary.leetcode.linkedlist;

public class LinkedListCycle141 {
    public static void main(String[] args) {
        ListNode list = new ListNode(3,
                new ListNode(2,
                        new ListNode(0,
                                new ListNode(4, null))));
        list.next.next.next.next = list;
        System.out.println(new LinkedListCycle141().hasCycle(list));
    }

    public boolean hasCycle(ListNode head) {
        ListNode runner = head;
        ListNode walker = head;

        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) {
                return true;
            }
        }
        // 链表为空
        // 链表无环
        return false;
    }

    private static final class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
