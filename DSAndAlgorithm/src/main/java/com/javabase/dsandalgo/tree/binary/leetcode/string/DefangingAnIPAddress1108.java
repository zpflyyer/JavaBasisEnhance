package com.javabase.dsandalgo.tree.binary.leetcode.string;

public class DefangingAnIPAddress1108 {

    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (Character c : address.toCharArray()) {
            if (c == '.') {
                sb.append("[.]");
            } else {
                sb.append(c.toString());
            }
        }
        return sb.toString();
    }
}
