package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountNumberOfTeams1395 {

    public int numTeams(int[] rating) {
        int res = 0;
        Map<Integer, List<Integer>> idx2LargerIdxList = new HashMap<>();
        Map<Integer, List<Integer>> idx2LessIdxList = new HashMap<>();
        for (int i = 0; i < rating.length; i++) {
            idx2LargerIdxList.put(i, new ArrayList<>());
            idx2LessIdxList.put(i, new ArrayList<>());
        }
        for (int i = 0; i < rating.length; i++) {
            for (int j = i + 1; j < rating.length; j++) {
                if (rating[j] > rating[i]) {
                    idx2LargerIdxList.get(i).add(j);
                } else {
                    idx2LessIdxList.get(i).add(j);
                }
            }
        }
        for (int i = 0; i < rating.length; i++) {
            for (Integer largerIdx : idx2LargerIdxList.get(i)) {
                res += idx2LargerIdxList.get(largerIdx).size();
            }
            for (Integer lessIdx : idx2LessIdxList.get(i)) {
                res += idx2LessIdxList.get(lessIdx).size();
            }
        }

        return res;
    }
}
