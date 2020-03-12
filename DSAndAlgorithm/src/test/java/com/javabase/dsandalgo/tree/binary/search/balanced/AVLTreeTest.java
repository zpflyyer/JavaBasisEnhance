package com.javabase.dsandalgo.tree.binary.search.balanced;

import com.javabase.dsandalgo.tree.binary.clrs.bst.AVLTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AVLTreeTest {
    private AVLTree<Integer> integerAVLTree = new AVLTree<>();

    //mock data
    private Integer[] integers = {3, 5, 1, 4, 3, 9, 7, 6, 8, 5};
    private List<Integer> integerList = new ArrayList<>();
    {
        for (Integer integer:
                integers) {
            integerList.add(integer);
            integerAVLTree.insert(integer);
        }
    }

    //借助Java visualizer将树可视化
    @Test
    public void insertAndSearchAndRemoveTest(){
        for (Integer integer:
                integerList) {
            assertTrue(integerAVLTree.contains(integer));
        }
        assertEquals(integerAVLTree.findMax().getElement(), Integer.valueOf(9));
        assertEquals(integerAVLTree.findMin().getElement(), Integer.valueOf(1));
        assertEquals(integerAVLTree.getRoot().getElement(), Integer.valueOf(5));
        assertEquals(integerAVLTree.remove(6).getRight().getElement(), Integer.valueOf(8));
        assertTrue(true);
    }
}
