package com.javabase.dsandalgo.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Person implements Comparable<Person> {
    @NonNull
    private Integer age;
    private String name;

    public int compareTo(Person o) {
        return this.age == o.age ? 0
                : this.age > o.age ? 1
                : -1;
    }
}