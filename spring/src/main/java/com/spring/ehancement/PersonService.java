package com.spring.ehancement;

public interface PersonService {

    @PreHandler
    void sayHello(String hello);

    void sayGoodBye(String bye);
}
