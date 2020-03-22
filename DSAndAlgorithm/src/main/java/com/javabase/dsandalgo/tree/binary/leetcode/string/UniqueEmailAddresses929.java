package com.javabase.dsandalgo.tree.binary.leetcode.string;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddresses929 {

    public static void main(String[] args) {
        UniqueEmailAddresses929 uniqueEmailAddresses929 = new UniqueEmailAddresses929();
        String[] emails = {"testemail@leetcode.com", "testemail1@leetcode.com", "testemail+david@lee.tcode.com"};
        uniqueEmailAddresses929.numUniqueEmails(emails);
    }

    public int numUniqueEmails(String[] emails) {
        Set<String> emailSet = new HashSet<>();
        for (String email : emails) {
            String[] localAndDomain = email.split("@");

            int idx = localAndDomain[0].indexOf('+');
            if (idx > 0) {
                localAndDomain[0] = localAndDomain[0].substring(0, idx);
            }

            localAndDomain[0] = localAndDomain[0].replaceAll("\\.", "");
            emailSet.add(String.join("@", localAndDomain));
        }
        return emailSet.size();
    }
}
