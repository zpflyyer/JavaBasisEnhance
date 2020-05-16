package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters1239 {

    public static void main(String[] args) {
        MaximumLengthOfAConcatenatedStringWithUniqueCharacters1239 maxLen = new MaximumLengthOfAConcatenatedStringWithUniqueCharacters1239();
        System.out.println(maxLen.maxLength(Arrays.asList("cha", "r", "act", "ers")));
    }

    public int maxLength(List<String> arr) {
        int maxLen = 0;
        arr.removeIf(s -> !isUniqueString(s));
        Queue<List<Integer>> queue = new LinkedList<>();
        for (int i = 0; i < arr.size(); i++) {
            List<Integer> idxList = new ArrayList<>();
            idxList.add(i);
            queue.add(idxList);
        }
        while (!queue.isEmpty()) {
            List<Integer> idxList = queue.remove();
            int len = idxList.stream().map(arr::get).collect(Collectors.joining()).length();
            maxLen = Math.max(maxLen, len);
            Integer tailIdx = idxList.get(idxList.size() - 1);
            for (int i = tailIdx + 1; i < arr.size(); i++) {
                String concatStr = idxList.stream().map(arr::get).collect(Collectors.joining()) + arr.get(i);
                if (isUniqueString(concatStr)) {
                    List<Integer> idxList1 = new ArrayList<>(idxList);
                    idxList1.add(i);
                    queue.add(idxList1);
                }
            }
        }

        return maxLen;
    }

    private static boolean isUniqueString(String s) {
        Map<Character, Long> char2Count = s.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<Character, Long> entry : char2Count.entrySet()) {
            if (entry.getValue() > 1) {
                return false;
            }
        }
        return true;
    }
}
