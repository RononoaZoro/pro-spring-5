package com.apress.prospring5.ch5;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> cls) {
        return ("sing".equals(method.getName()))&&cls == GoodGuitarist.class;
    }

//    @Override
//    public ClassFilter getClassFilter() {
//        ClassFilter classFilter = cls -> (cls == GoodGuitarist.class);
//        return classFilter;
//    }
}
