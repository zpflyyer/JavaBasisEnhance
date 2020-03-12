package com.javabase.dsandalgo.tree.binary.search.balanced;

import com.javabase.dsandalgo.tree.binary.clrs.bst.EasyRedBlackTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.javabase.dsandalgo.tree.binary.clrs.bst.EasyRedBlackTree.NIL;
import static org.junit.Assert.*;

public class EasyRedBlackTreeTest {
    private EasyRedBlackTree<Integer> rbTree = new EasyRedBlackTree<>();

    //借助Java visualizer将树可视化
    @Test
    public void insertAndSearchAndRemoveTest() {
        Integer[] integers = {3, 5, 1, 4, 3, 9, 7, 6, 8, 5};
        List<Integer> integerList = new ArrayList<>();
        {
            for (Integer integer :
                    integers) {
                integerList.add(integer);
                rbTree.insert(integer);
            }
        }
        for (Integer integer :
                integerList) {
            assertTrue(rbTree.contains(integer));
        }
        assertEquals(rbTree.findMax().getElement(), Integer.valueOf(9));
        assertEquals(rbTree.findMin().getElement(), Integer.valueOf(1));

        assertEquals(NIL, rbTree.findPredecessor(rbTree.search(1)));
        assertEquals(1, (int) rbTree.findPredecessor(rbTree.search(3)).getElement());
        assertEquals(3, (int) rbTree.findPredecessor(rbTree.search(4)).getElement());

        assertEquals(rbTree.findSuccessor(rbTree.search(9)), NIL);
        assertEquals((int) rbTree.findSuccessor(rbTree.search(8)).getElement(), 9);
        assertEquals((int) rbTree.findSuccessor(rbTree.search(5)).getElement(), 6);

        Set<Integer> removedInts = new HashSet<>();
        integerList.forEach(i -> {
            EasyRedBlackTree.RedBlackTreeNode<Integer> node = rbTree.search(i);
            if (removedInts.contains(i)){
                assertNull(node.getElement());
            } else {
                assertEquals(node.getElement(), i);
                rbTree.remove(node);
                removedInts.add(i);
            }
        });
    }
}
