package com.javabase.dsandalgo.tree.binary.search;

import com.javabase.dsandalgo.tree.binary.clrs.bst.BinarySearchTree;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

    //mock data
    private Integer[] integers = {3, 5, 1, 4, 3, 9, 7, 6, 8, 5};
    private List<Integer> integerList = new ArrayList<>();
    {
        for (Integer integer:
                integers) {
            integerList.add(integer);
            binarySearchTree.insert(integer);
        }
    }

    //借助Java visualizer将树可视化
    @Test
    public void insertAndSearchAndRemoveTest(){
        for (Integer integer:
                integerList) {
            assertTrue(binarySearchTree.contains(integer));
        }
        assertEquals(binarySearchTree.findMax().getElement(), Integer.valueOf(9));
        assertEquals(binarySearchTree.findMin().getElement(), Integer.valueOf(1));
        assertEquals(binarySearchTree.getRoot().getElement(), Integer.valueOf(3));
        assertEquals(binarySearchTree.remove(integerList.get(0)).getElement(), Integer.valueOf(4));
    }
}
