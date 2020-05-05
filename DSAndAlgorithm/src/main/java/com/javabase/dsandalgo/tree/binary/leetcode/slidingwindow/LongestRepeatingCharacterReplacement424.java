package com.javabase.dsandalgo.tree.binary.leetcode.slidingwindow;

public class LongestRepeatingCharacterReplacement424 {

    public static void main(String[] args) {
        String s = "AABABBA";
        LongestRepeatingCharacterReplacement424 replacement424 = new LongestRepeatingCharacterReplacement424();
        System.out.println(replacement424.characterReplacement(s, 1));
    }

    public int characterReplacement(String s, int k) {
        int j = 0;
        int maxCount = 0;
        int[] count = new int[128];
        for (int i = 0; i < s.length(); i++) {
            // 截至目前为止的所有合法窗口里单一字符的最高频率
            maxCount = Math.max(maxCount, ++count[s.charAt(i)]);
            // 如果新出现的字符扩张了窗口，则maxCount增大了，j原地不动，i向右移动；否则如果出现的字符不能扩张窗口，则i,j一起右移动
            if (i - j + 1 - maxCount > k) {
                count[s.charAt(j++)]--;
            }
        }
        return s.length() - j;
    }

    public int search(String txt) {
        String pat = "";
        int[] right = new int[12];
        // 在txt中查找模式字符串
        int N = txt.length();
        int M = pat.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            // 模式字符串和文本在位置i匹配吗？
            skip = 0;
            for (int j = M - 1; j >= 0; j--)
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            if (skip == 0)
                return i; // 找到匹配
        }
        // 未找到匹配
        return N;
    }
}
