//package com.javabase.dsandalgo.tree.binary.search.balanced;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class RedBlackTreeTest {
//    private RedBlackTree<Integer> integerRBTree = new RedBlackTree<>();
//
//    //mock data
//    private Integer[] integers = {10, 85, 15, 70, 20, 60, 30, 50, 65, 80, 90, 40, 5, 55, 45};
//    private List<Integer> integerList = new ArrayList<>();
//    {
//        for (Integer integer:
//                integers) {
//            integerList.add(integer);
//            integerRBTree.insert(integer);
//        }
//    }
//
//    //借助Java visualizer将树可视化
//    @Test
//    public void insertAndSearchAndRemoveTest(){
//        for (Integer integer:
//                integerList) {
//            assertTrue(integerRBTree.contains0(integer));
//        }
//        assertEquals(integerRBTree.findMax().getColor(), 0);
//        assertEquals(integerRBTree.findMin().getColor(), 0);
//        assertEquals(integerRBTree.getRoot().getColor(), 1);
//    }
//}
