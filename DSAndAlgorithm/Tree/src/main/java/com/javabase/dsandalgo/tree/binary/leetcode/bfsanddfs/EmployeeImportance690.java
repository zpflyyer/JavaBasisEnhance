package com.javabase.dsandalgo.tree.binary.leetcode.bfsanddfs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeImportance690 {

    public static void main(String[] args) {

    }

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> id2Employee = employees.stream().collect(Collectors.toMap(employee -> employee.id, Function.identity()));
        int importance = 0;

        Employee employee = id2Employee.get(id);
        if (employee != null) {
            Queue<Employee> queue = new LinkedList<>();
            queue.add(employee);
            while (!queue.isEmpty()) {
                Employee curEmployee = queue.remove();
                importance += curEmployee.importance;
                curEmployee.subordinates.stream()
                        .map(id2Employee::get)
                        .filter(Objects::nonNull)
                        .forEach(queue::add);
            }
        }
        return importance;
    }


    private static final class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }

}
