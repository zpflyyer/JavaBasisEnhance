package com.javabase.dsandalgo.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * KMP算法基于DFA，匹配失败时不需要回退文本
 * 0：用DFA的视角来看子串查找算法
 * 1：能够保证线性复杂度，但实际中相比不会比暴力法不会有明显优势：很少在重复性高的text里搜索重复性高的pat
 * 3：适用于输入流长度不确定的情况：永远不需要回退
 */
public class KMP {

    private String pat;

    private int[][] dfa;

    public KMP(String pat) {
        // 在文本里查找的过程，实际上是在基于查找内容上构建的DFA上进行模拟计算的过程
        this.pat = pat;
        // 构建DFA
        int patLen = pat.length();
        int charSetSize = 256;

        this.dfa = new int[charSetSize][patLen];
        // 初始化dfa[][0]
        dfa[pat.charAt(0)][0] = 1;

        for (int stateIndex = 0, i = 1; i < patLen; i++) {
            for (int j = 0; j < charSetSize; j++) {
                // 设置非匹配转换
                dfa[j][i] = dfa[j][stateIndex];
            }
            // 唯一一个匹配转换
            dfa[pat.charAt(i)][i] = i + 1;
            // 更新重启状态：即当pat[i+1]处不匹配时，状态机处于什么状态
            stateIndex = dfa[pat.charAt(i)][stateIndex];
        }
    }

    public List<Integer> searching(String text) {
        if (text == null) {
            return Collections.emptyList();
        }

        List<Integer> startIndexes = new ArrayList<>();
        int state = 0;
        int patLen = pat.length();
        for (int i = 0; i < text.length(); i++) {
            // 状态转换运算
            state = dfa[text.charAt(i)][state];
            if (state == patLen) {
                startIndexes.add(i - patLen + 1);
                state = 0;
            }
        }
        return startIndexes;
    }
}
