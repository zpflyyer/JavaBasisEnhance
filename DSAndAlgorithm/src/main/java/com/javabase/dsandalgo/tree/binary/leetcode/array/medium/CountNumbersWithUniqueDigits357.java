package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class CountNumbersWithUniqueDigits357 {

    public static void main(String[] args) {
        CountNumbersWithUniqueDigits357 countNumbersWithUniqueDigits357 = new CountNumbersWithUniqueDigits357();
        System.out.println(countNumbersWithUniqueDigits357.countNumbersWithUniqueDigits(3));
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }

        int maxLen = Math.min(10, n);
        Map<Integer, List<Integer>> lenToList = new HashMap<>();

        // 0 is not in the map
        int count = 1;
        //init
        for (int len = 1; len <= maxLen; len++) {
            lenToList.put(len, new ArrayList<>());
        }
        IntStream.range(1, 10).forEach(lenToList.get(1)::add);

        // 对于一个整数num,如果其digits unique, 那么即使将某些digit(s)从num里拿走，剩下的digits依然组成一个更小的digits unique的num1
        for (int len = 2; len <= maxLen; len++) {
            List<Integer> listOfPrevLen = lenToList.get(len - 1);
            List<Integer> listOfThisLen = lenToList.get(len);
            for (Integer num : listOfPrevLen) {
                List<Integer> digitList = new ArrayList<>();
                for (char digit : String.valueOf(num).toCharArray()) {
                    digitList.add(digit - '0');
                }
                IntStream.range(0, 10).filter(digit -> !digitList.contains(digit)).forEach(digit -> listOfThisLen.add(num * 10 + digit));
            }
        }

        for (Map.Entry<Integer, List<Integer>> entry : lenToList.entrySet()) {
            count += entry.getValue().size();
        }
        return count;
    }
}
