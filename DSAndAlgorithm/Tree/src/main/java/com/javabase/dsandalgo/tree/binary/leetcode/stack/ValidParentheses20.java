package com.javabase.dsandalgo.tree.binary.leetcode.stack;

import java.util.Stack;

public class ValidParentheses20 {
    public static void main(String[] args) {

        ValidParentheses20 validParentheses20 = new ValidParentheses20();
        validParentheses20.isValid("[]{}()");
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else if (c == '}') {
                    if (stack.pop() != '{') {
                        return false;
                    }
                } else if (c == ')') {
                    if (stack.pop() != '(') {
                        return false;
                    }
                } else {
                    if (stack.pop() != '[') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
