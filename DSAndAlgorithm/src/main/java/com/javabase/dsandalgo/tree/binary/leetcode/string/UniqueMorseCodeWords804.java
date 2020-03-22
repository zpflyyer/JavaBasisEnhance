package com.javabase.dsandalgo.tree.binary.leetcode.string;

import java.util.HashSet;
import java.util.Set;

public class UniqueMorseCodeWords804 {

    public static void main(String[] args) {

    }

    public int uniqueMorseRepresentations(String[] words) {
        String dict[] = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        Set<String> morseSet = new HashSet<>();
        for (String s : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                sb.append(dict[c - 'a']);
            }
            morseSet.add(sb.toString());
        }
        return morseSet.size();
    }
}
