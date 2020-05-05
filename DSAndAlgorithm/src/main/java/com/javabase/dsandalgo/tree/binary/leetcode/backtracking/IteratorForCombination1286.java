package com.javabase.dsandalgo.tree.binary.leetcode.backtracking;

public class IteratorForCombination1286 {

    public static void main(String[] args) {
        IteratorForCombination1286 iterator = new IteratorForCombination1286("abc", 2);
        do {
            System.out.println(iterator.next());
        } while (iterator.hasNext());
    }

    private final String start;
    private final String end;
    private final String s;
    private final int n;
    private String previous = "";

    public IteratorForCombination1286(String s, int n) {
        this.s = s;
        this.n = n;
        this.start = s.substring(0, n);
        this.end = s.substring(s.length() - n);
    }

    public boolean hasNext() {
        return !this.end.equals(this.previous);
    }

    public String next() {
        return this.getNext(this.previous);
    }

    private String getNext(String prev) {
        if (this.previous.isEmpty()) {
            return this.previous = this.start;
        }

        String res;
        int idx = this.s.indexOf(prev.charAt(prev.length() - 1));
        if (idx == this.s.length() - 1) {
            int i = 0;
            while (i < prev.length() && this.s.charAt(this.s.length() - 1 - i) == prev.charAt(prev.length() - 1 - i)) {
                i++;
            }
            res = this.getNext(prev.substring(0, prev.length() - i));
            int start = this.s.indexOf(res.charAt(res.length() - 1)) + 1;
            res = res + this.s.substring(start, start + n - res.length());
        } else {
            res = prev.substring(0, prev.length() - 1) + this.s.charAt(idx + 1);
        }

        if (res.length() == this.n) {
            this.previous = res;
        }
        return res;
    }
}
