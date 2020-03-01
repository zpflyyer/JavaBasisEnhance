package com.huadongmedia.infrastructure;

import java.util.Scanner;

public class LCS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        LCS lcs = new LCS();
        System.out.println(lcs.lcs(s1, s2).lcs);
    }

    public LcsRecord lcs(String s1, String s2) {
        int rowNum = s1.length() + 1;
        int colNum = s2.length() + 1;
        LcsRecord[][] lcsMatrix = new LcsRecord[rowNum][colNum];
        for (int i = 0; i < colNum; i++) {
            lcsMatrix[0][i] = new LcsRecord(0, "");
        }
        for (int i = 1; i < rowNum; i++) {
            lcsMatrix[i][0] = new LcsRecord(0, "");
        }
        for (int i = 1; i < rowNum; i++) {
            for (int j = 1; j < colNum; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    LcsRecord lcsRecord = lcsMatrix[i - 1][j - 1];
                    lcsMatrix[i][j] = new LcsRecord(lcsRecord.len + 1, lcsRecord.lcs + s1.charAt(i - 1));
                } else {
                    LcsRecord lcsRecordTop = lcsMatrix[i - 1][j];
                    LcsRecord lcsRecordLeft = lcsMatrix[i][j - 1];
                    if (lcsRecordLeft.len > lcsRecordTop.len) {
                        lcsMatrix[i][j] = lcsRecordLeft;
                    } else {
                        lcsMatrix[i][j] = lcsRecordTop;
                    }
                }
            }
        }
        return lcsMatrix[rowNum - 1][colNum - 1];
    }

    private static final class LcsRecord {
        private int len;
        private String lcs;

        private LcsRecord(int len, String lcs) {
            this.len = len;
            this.lcs = lcs;
        }
    }
}
