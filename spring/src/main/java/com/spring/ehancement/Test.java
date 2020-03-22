package com.spring.ehancement;

public class Test {
    public static void main(String[] args) {
        PersonService personServiceImpl = new PersonServiceImpl();
        PersonService bean = (PersonService) new HealthCheckHandler().getInstance(personServiceImpl);
        bean.sayHello("你好");
        bean.sayGoodBye("再见");
    }
}
