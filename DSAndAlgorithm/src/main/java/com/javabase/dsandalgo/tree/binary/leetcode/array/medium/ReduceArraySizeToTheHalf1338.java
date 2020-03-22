package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.*;

public class ReduceArraySizeToTheHalf1338 {

    public static void main(String[] args) {

    }

    public int minSetSize(int[] arr) {
        Queue<Occurrence> queue = new PriorityQueue<>(Comparator.comparingInt(Occurrence::getOccurrence).reversed());
        Map<Integer, Integer> value2Occurrence = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            value2Occurrence.compute(arr[i], (k, v) -> v == null ? 1 : v + 1);
        }
        for (Map.Entry<Integer, Integer> entry : value2Occurrence.entrySet()) {
            queue.add(new Occurrence(entry.getKey(), entry.getValue()));
        }

        int count = 0;
        int size = 0;
        while (count < arr.length / 2 && !queue.isEmpty()) {
            count += queue.remove().getOccurrence();
            size++;
        }

        return size;
    }

    private static final class Occurrence {
        private int value;
        private int occurrence;

        private Occurrence(int value, int occurrence) {
            this.value = value;
            this.occurrence = occurrence;
        }

        private int getOccurrence() {
            return occurrence;
        }
    }
}
