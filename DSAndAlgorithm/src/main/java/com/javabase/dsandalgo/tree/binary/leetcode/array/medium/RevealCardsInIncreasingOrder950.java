package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RevealCardsInIncreasingOrder950 {

    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        Queue<Integer> visitQueue = new LinkedList<>();
        for (int i = 0; i < deck.length; i++) {
            visitQueue.add(i);
        }

        int[] result = new int[deck.length];
        for (int value : deck) {
            result[visitQueue.remove()] = value;
            if (!visitQueue.isEmpty()) {
                visitQueue.add(visitQueue.remove());
            }
        }

        return result;
    }
}
