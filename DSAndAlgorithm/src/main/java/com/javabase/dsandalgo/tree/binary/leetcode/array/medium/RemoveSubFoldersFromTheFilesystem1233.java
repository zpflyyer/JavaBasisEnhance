package com.javabase.dsandalgo.tree.binary.leetcode.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveSubFoldersFromTheFilesystem1233 {

    public static void main(String[] args) {
        String[] folders = {"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"};
        RemoveSubFoldersFromTheFilesystem1233 removeSubFoldersFromTheFilesystem1233 = new RemoveSubFoldersFromTheFilesystem1233();
        removeSubFoldersFromTheFilesystem1233.removeSubFolders(folders);
    }

    public List<String> removeSubFolders(String[] folders) {
        // sort的结果是：呈现父子关系的文件夹必然按父子关系从左到右连续排列
        Arrays.sort(folders);
        List<String> results = new ArrayList<>();
        results.add(folders[0]);
        for (int i = 1; i < folders.length; i++) {
            if (!folders[i].startsWith(results.get(results.size() - 1))) {
                results.add(folders[i]);
            }
        }
        return results;
    }
}
