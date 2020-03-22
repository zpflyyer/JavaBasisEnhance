package com.javabase.dsandalgo.tree.binary.leetcode.string;

public class ReverseWordsInAStringIII557 {

    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        for (int j = 0; j < strings.length; j++) {
            char[] chars = strings[j].toCharArray();
            for (int i = 0; i < chars.length / 2; i++) {
                char tmp = chars[i];
                chars[i] = chars[chars.length - 1 - i];
                chars[chars.length - 1 - i] = tmp;
            }
            strings[j] = new String(chars);
        }
        return String.join(" ", strings);
    }

}
