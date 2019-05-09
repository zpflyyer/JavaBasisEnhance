package com.javabase.dsandalgo.tree;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Person implements Comparable<Person> {
    @NonNull
    private Integer age;
    private String firstName = "";
    private String lastName = "";

    public int compareTo(Person o) {
        return this.age.compareTo(o.getAge()) != 0 ? this.age.compareTo(o.getAge())
                : this.firstName.compareTo(o.getFirstName()) != 0 ? this.firstName.compareTo(o.getFirstName())
                : this.lastName.compareTo(o.getLastName());
    }
}