package com.javabase.dsandalgo.tree;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryTreeTest {
    BinarySearchTree<Person> personTree = new BinarySearchTree<Person>();
    Integer[] ageArray = {3, 5, 1, 4, 3, 9, 7, 6, 8, 5};
    List<Person> personList = new ArrayList<Person>();
    {
        for (Integer age:
                Arrays.asList(ageArray)) {
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
    }
}
