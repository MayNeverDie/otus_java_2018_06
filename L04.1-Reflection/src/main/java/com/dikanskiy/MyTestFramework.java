package com.dikanskiy;

import com.dikanskiy.annotations.After;
import com.dikanskiy.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class MyTestFramework {
    ArrayList BeforeAnnotations = new ArrayList();
    ArrayList TestAnnotations = new ArrayList();
    ArrayList AfterAnnotations = new ArrayList();

    public MyTestFramework() {
    }

    public void run(Class givenClass){
        Method[] objMethods = ReflectionHelper.getMethods(givenClass);
        parseMethods(objMethods);
        executeMethods(givenClass);
    }

    private void parseMethods(Method[] tempMethods){
        for(Method meth:tempMethods){
            Annotation[] methAnnotations = ReflectionHelper.getAnnotations(meth);
            for(Annotation annot: methAnnotations){
                if (annot.annotationType().equals(Test.class) ){
                    TestAnnotations.add(meth);
                } else if (annot.annotationType().equals(After.class)){
                    AfterAnnotations.add(meth);
                }else{
                    BeforeAnnotations.add(meth);
                }
            }
        }
    }

    private void executeMethods(Class givenClass){
        Iterator testIterator = TestAnnotations.iterator();
        while (testIterator.hasNext()){
            Object instantiatedObject = ReflectionHelper.instantiate(givenClass);
            callAllMethods(BeforeAnnotations,instantiatedObject);
            ReflectionHelper.callMethod(instantiatedObject,(Method)testIterator.next());
            callAllMethods(AfterAnnotations,instantiatedObject);
       }
    }

    private void callAllMethods(ArrayList givenSet, Object givenObject ){
        Iterator myIterator = givenSet.iterator();
        while (myIterator.hasNext()){
            ReflectionHelper.callMethod(givenObject,(Method)myIterator.next());
        }
    }
}
