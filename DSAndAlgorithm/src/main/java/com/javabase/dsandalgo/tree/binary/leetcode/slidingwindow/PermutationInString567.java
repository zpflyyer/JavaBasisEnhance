package com.javabase.dsandalgo.tree.binary.leetcode.slidingwindow;

public class PermutationInString567 {

    public static void main(String[] args) {
        PermutationInString567 per = new PermutationInString567();
        System.out.println(per.checkInclusion("aaab", "bdbaaaadf"));
    }

    public boolean checkInclusion(String s1, String s2) {
        int[] count = new int[128];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i)]--;
        }

        for (int l = 0, r = 0; r < s2.length(); r++) {
            // 出现了s1不含有的字符：窗口左边界移动到当前节点右边
            if (++count[s2.charAt(r)] > 0) {
                // 循环条件不再为真时，l依然需要自增一次
                while (--count[s2.charAt(l++)] != 0) ;
            }
            if (r - l + 1 == s1.length()) {
                return true;
            }
        }
        return s1.length() == 0;
    }
}
