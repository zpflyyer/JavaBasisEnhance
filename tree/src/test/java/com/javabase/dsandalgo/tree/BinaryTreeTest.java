package com.javabase.dsandalgo.tree;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryTreeTest {
    private BinarySearchTree<Person> personTree = new BinarySearchTree<>();

    //mock data
    private Integer[] ageArray = {3, 5, 1, 4, 3, 9, 7, 6, 8, 5};
    private List<Person> personList = new ArrayList<>();
    {
        for (Integer age:
                ageArray) {
            Person person = new Person(age);
            personList.add(person);
            personTree.insert(person);
        }
    }

    @Test
    public void testInsert(){
        for (Person person:
             personList) {
            assertTrue(personTree.contains(person));
        }
        assertEquals(true, personTree.findMax().getElement().getAge().equals(9));
        assertEquals(true, personTree.findMin().getElement().getAge().equals(1));
        assertEquals(true, personTree.remove(personList.get(0)).getElement().getAge().equals(4));
    }
}
