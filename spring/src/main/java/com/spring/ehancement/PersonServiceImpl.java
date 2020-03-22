package com.spring.ehancement;

public class PersonServiceImpl implements PersonService {

    public void sayHello(String hello) {
        System.out.println(hello);
    }

    public void sayGoodBye(String bye) {
        System.out.println(bye);
    }
}
