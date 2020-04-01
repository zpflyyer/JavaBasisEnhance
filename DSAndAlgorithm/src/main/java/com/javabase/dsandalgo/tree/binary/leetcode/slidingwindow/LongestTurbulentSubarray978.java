package com.javabase.dsandalgo.tree.binary.leetcode.slidingwindow;

public class LongestTurbulentSubarray978 {

    public static void main(String[] args) {
        LongestTurbulentSubarray978 longestTurbulentSubarray978 = new LongestTurbulentSubarray978();
        int[] input = {0, 1, 1, 0, 1, 0, 1, 1, 0, 0};
        System.out.println(longestTurbulentSubarray978.maxTurbulenceSize(input));
    }

    public int maxTurbulenceSize(int[] A) {
        // 当prev并不存在（数组不存在人-1 idx元素）或者pre本来是0时，pre都应该置为1：因为这两种情形都意味着一个新的窗口的开始位置，而此时，不论是pre是1还是-1，都不影响第2步结果总是2的情形
        int pre = 1, cur, len = 1, max = 1;
        for (int i = 1; i < A.length; i++) {
            cur = Integer.compare(A[i], A[i - 1]);
            if (cur * pre == -1) {
                len++;
                pre = cur;
            } else if (cur * pre == 1) {
                len = 2;
                pre = cur;
            } else {
                len = 1;
                pre = 1;
            }
            max = Math.max(max, len);
        }

        return max;
    }
}
