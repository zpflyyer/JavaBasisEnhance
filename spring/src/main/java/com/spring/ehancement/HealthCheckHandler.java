package com.spring.ehancement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HealthCheckHandler implements InvocationHandler {

    private PersonService target;

    public Object getInstance(PersonService personService) {
        this.target = personService;
        Class clazz = this.target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(PreHandler.class)) {
            System.out.println("are u health?");
        }
        return method.invoke(this.target, args);
    }
}
