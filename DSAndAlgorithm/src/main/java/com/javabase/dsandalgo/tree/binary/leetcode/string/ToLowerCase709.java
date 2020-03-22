package com.javabase.dsandalgo.tree.binary.leetcode.string;

public class ToLowerCase709 {

    public static void main(String[] args) {
        System.out.println(new ToLowerCase709().toLowerCase("Hello"));
    }

    public String toLowerCase(String str) {
        char[] results = new char[str.length()];
        int i = 0;
        int j = 'a' - 'A';
        for (Character c : str.toCharArray()) {
            results[i++] = 'A' <= c && c <= 'Z' ? (char) (c + j) : c;
        }

        return new String(results);
    }
}
