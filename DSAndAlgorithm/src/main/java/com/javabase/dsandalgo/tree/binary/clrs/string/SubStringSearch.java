package com.javabase.dsandalgo.tree.binary.clrs.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SubStringSearch {
    public static void main(String[] args) throws IOException {
        SubStringSearch searcher = new SubStringSearch();
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input text:");
            String text = reader.readLine();
            if (text.contains("exit")) {
                System.out.println(RED + "bye!");
                break;
            }
            System.out.println("input key word:");
            String keyWord = reader.readLine();

            System.out.println("------------simple search result-------------");
            searcher.simpleSearch(text, keyWord);

            System.out.println("------------simple search result with text index rollback-------------");
            searcher.rollBackTextPointSearch(text, keyWord);

            System.out.println("------------KMP searching-------------");
            KMP kmp = new KMP(keyWord);
            highLightOutput(text, kmp.searching(text), keyWord.length());

            System.out.println("------------Boyer Moore searching-------------");
            BoyerMoore boyerMoore = new BoyerMoore(keyWord);
            highLightOutput(text, boyerMoore.search(text), keyWord.length());

            System.out.println("=====================done======================");

        }
    }

    /**
     * 暴力法搜索
     */
    public void simpleSearch(String text, String key) {
        int txtLen = text.length();
        int keyLen = key.length();
        if (txtLen < keyLen) {
            return;
        }
        List<Integer> startIndexes = new ArrayList<>();
        for (int i = 0; i <= txtLen - keyLen; i++) {
            int j = 0;
            while (j < keyLen && text.charAt(i + j) == key.charAt(j)) {
                j++;
            }
            if (j == keyLen) {
                startIndexes.add(i);
            }
        }
        highLightOutput(text, startIndexes, key.length());
    }

    /**
     * 与暴力法 {@link #simpleSearch(String, String)}的区别在于：文本指针跟随者关键字指针一起移动，如果当前text index查找失败，则需要回退到开始的位置
     */
    public void rollBackTextPointSearch(String text, String key) {
        int textLen = text.length();
        int keyLen = key.length();
        if (textLen < keyLen) {
            return;
        }
        List<Integer> startIndexes = new ArrayList<>();
        int i = 0, j = 0;
        while (i < textLen) {
            if (text.charAt(i) == key.charAt(j)) {
                j++;
                if (j == keyLen) {
                    startIndexes.add(i - j + 1);
                    j = 0;
                }
            } else {
                i -= j;
                j = 0;
            }
            // 不论查找是否成功，都继续下一个文本字符
            i++;
        }
        highLightOutput(text, startIndexes, key.length());
    }

    /**
     * @param text         text to output
     * @param startIndexes start index of key in text
     */
    public static void highLightOutput(String text, List<Integer> startIndexes, int keyLength) {
        StringBuilder result = new StringBuilder();
        int from = 0;
        for (Integer index : startIndexes) {
            String left = text.substring(from, index);
            String highLight = text.substring(index, index + keyLength);
            result.append(BLACK).append(left).append(RED).append(highLight).append(BLACK);
            from = index + keyLength;
        }
        result.append(text.substring(from));
        System.out.println(result);
    }

    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
}
