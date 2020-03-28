package com.javabase.dsandalgo.tree.binary.leetcode.slidingwindow;

import java.util.LinkedList;
import java.util.Queue;

public class MaxConsecutiveOnesIII1004 {

    public static void main(String[] args) {
        int[] A = {0, 0, 1, 1, 1, 0, 0};
        int K = 0;
        MaxConsecutiveOnesIII1004 maxConsecutiveOnesIII1004 = new MaxConsecutiveOnesIII1004();
        System.out.println(maxConsecutiveOnesIII1004.longestOnes(A, K));
    }

    public int longestOnes(int[] A, int K) {
        int maxLen = 0;
        int remainOnes = K;
        int curLen = 0;
        Queue<Integer> replaceQueue = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                curLen++;
                maxLen = Math.max(maxLen, curLen);
            } else {
                if (remainOnes > 0) {
                    replaceQueue.add(i);
                    remainOnes--;
                    curLen++;
                    maxLen = Math.max(maxLen, curLen);
                }
                // 开启新窗口
                else {
                    // 队列不空，说明K > 0，新窗口从上个窗口里最左的0元素右边开始
                    if (!replaceQueue.isEmpty()) {
                        curLen = i - replaceQueue.remove();
                        replaceQueue.add(i);
                    }
                    // 队列为空
                    else {
                        curLen = 0;
                    }
                }
            }
        }
        return maxLen;
    }
}
