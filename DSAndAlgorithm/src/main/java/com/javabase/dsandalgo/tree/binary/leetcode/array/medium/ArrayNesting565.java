package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayNesting565 {
    public int arrayNesting(int[] nums) {
        List<Integer> toHandle = new ArrayList<>();
        for (int num : nums) {
            toHandle.add(num);
        }
        Set<Integer> curSet = new HashSet<>();
        int maxCount = 0;

        while (!toHandle.isEmpty()) {
            int nextIdx = toHandle.get(0);
            while (!curSet.contains(nums[nextIdx])) {
                curSet.add(nums[nextIdx]);
                nextIdx = nums[nextIdx];
            }
            maxCount = Math.max(maxCount, curSet.size());
            toHandle.removeAll(curSet);
            curSet.clear();
        }

        return maxCount;
    }
}
