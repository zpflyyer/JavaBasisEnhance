package com.javabase.dsandalgo.tree.binary;

public class ReadableMinCost {
    private final int start;
    private final int end;
    private final int splitIdx;
    private final int cost;
    private final int len;

    public ReadableMinCost(int start, int end, int splitIdx, int cost) {
        this.start = start;
        this.end = end;
        this.splitIdx = splitIdx;
        this.cost = cost;
        this.len = this.end - this.start + 1;
    }

    @Override
    public String toString() {
        return "m[" + start + ", " + end + "] = " + cost + ", s[" + start + ", " + end + "] = " + splitIdx;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getSplitIdx() {
        return splitIdx;
    }

    public int getCost() {
        return cost;
    }

    public int getLen() {
        return len;
    }
}
