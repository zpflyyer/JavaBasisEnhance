package com.javabase.dsandalgo.tree.binary.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters3 {

    public int lengthOfLongestSubstring(String s) {
        // 最长的不重复子串必然是以某处结尾的，考察以每个元素结尾的最长不重复子串，取最大。
        // 以上一个元素结尾的最长子串和以当前元素结尾的最长子串的关系：
        // 1.当前元素不包含在以上一个元素结尾的子串里：子串继续扩张
        // 2.否则，新的子串从新的位置开始
        Set<Character> longestSubStr = new HashSet<>();
        int maxLen = 0;
        int curLen = 0;
        // idx current window start from
        int curFrom = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!longestSubStr.contains(s.charAt(i))) {
                longestSubStr.add(s.charAt(i));
                // increase current window
                curLen++;
                maxLen = Math.max(maxLen, curLen);
            } else {
                // start a new window
                while (s.charAt(curFrom) != s.charAt(i)) {
                    longestSubStr.remove(s.charAt(curFrom++));
                }
                // new window start after 1st occurrence of s.charAt(i) in longestSubStr
                curFrom++;
                curLen = i - curFrom + 1;
            }
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring0(String s) {
        Map<Character, Integer> char2LastOccur = new HashMap<>();
        int maxLen = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (char2LastOccur.containsKey(s.charAt(i))) {
                // to make sure there is no duplicate between idx j and idx i
                j = Math.max(char2LastOccur.get(s.charAt(i)) + 1, j);
            }
            char2LastOccur.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i - j + 1);
        }
        return maxLen;
    }
}
