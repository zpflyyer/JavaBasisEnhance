package com.javabase.dsandalgo.string;

import java.util.ArrayList;
import java.util.List;

public class BoyerMoore {

    private String pat;

    private int[] right;

    public BoyerMoore(String pat) {
        this.pat = pat;

        // 初始化right数组
        this.right = new int[256];
        for (int i = 0; i < 256; i++) {
            this.right[i] = -1;
        }
        for (int i = 0; i < this.pat.length(); i++) {
            this.right[this.pat.charAt(i)] = i;
        }
    }

    // 相比于KMP 搜索更加高效：从模式尾部开始匹配，一旦发现不匹配的内容，文本指针右移的偏移量可以更大
    public List<Integer> search(String text) {
        int tLen = text.length();
        int pLen = this.pat.length();
        List<Integer> indexList = new ArrayList<>();
        int skip;
        for (int i = 0; i <= tLen - pLen; i += skip) {
            // 重置skip
            skip = 0;
            for (int j = pLen - 1; j >= 0; j--) {
                char c = text.charAt(i + j);
                // 如果匹配失败，则必然要进行位移：计算出位移量
                if (c != this.pat.charAt(j)) {
                    // 1.text.charAt[i+j]未在pat里出现：skip = j + 1
                    // 2.right[text.charAt[i+j]] < j: skip = j - right[text.charAt[i+j]]
                    skip = j - this.right[c];
                    // 3.right[text.charAt[i+j]] > j: skip = 1
                    skip = Math.max(skip, 1);
                    break;
                }
            }
            // 如果当前匹配区间里的文本和pat匹配：文本指针右移1位，继续查找
            if (skip == 0) {
                indexList.add(i);
                skip++;
            }
        }
        return indexList;
    }
}
