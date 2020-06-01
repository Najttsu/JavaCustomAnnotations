/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.customannotations.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.tyaa.customannotations.annotations.ControlledObject;
import org.tyaa.customannotations.annotations.StartObject;
import org.tyaa.customannotations.annotations.StopObject;

/**
 *
 * @author yurii
 */
public class Tester {
    // Метод, получающий в качестве параметра объект отражения некоторого класса
    // (рапечатывает информацию об ожидаемых аннотациях из исследуемого типа в консоль)
    public void test(Class cl) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // Присутствует ли в описании класса аннотация ControlledObject?
        if(!cl.isAnnotationPresent(ControlledObject.class)){
            System.err.println("no annotation");
        } else {
            // Выводим информацию о найденной аннотации
            System.out.println("class annotated ; name  -  " + cl.getAnnotation(ControlledObject.class));
            // получаем объект отражения самой аннотации ControlledObject
            Class<?> coAClass = ControlledObject.class;
            /* for (Field f : coAClass.getDeclaredFields()) {
                System.out.println(f.getName());
            } */
            /* for (Method m : coAClass.getDeclaredMethods()) {
                m.setAccessible(true);
                System.out.println(m.getName());
                String nameValue =
                        (String) m.invoke(cl.getAnnotation(coAClass), null);
                System.out.println(nameValue);
            } */
            // из отражения аннотации получаем отражение метода (свойство) name
            Method m = coAClass.getDeclaredMethod("name");
            // на объекте отражения метода name вызываем метод вызова (invoke) метода name
            // на объекте аннотации ControlledObject, не передавая ни одного аргумента,
            // и получаем в ответ значение свойства name
            String nameValue =
                    (String) m.invoke(cl.getAnnotation(coAClass), null);
            System.out.println("name = " + nameValue);
        }
        // Проверка 2:
        // есть ли в описании исследуемого типа (класса) две аннотации,
        // записанные над методами: StartObject и StopObject.
        // Предполагаем, что нет
        boolean hasStart=false;
        boolean hasStop=false;
        // Получаем из отражения исследуемого типа отражение всех его открытых методов
        Method[] method = cl.getMethods();
        // Проходим по отражениям всех методов циклом
        for(Method md: method){
            // Присутствует ли над текущим методом аннотация StartObject
            if(md.isAnnotationPresent(StartObject.class)) {hasStart=true;}
            // Присутствует ли над текущим методом аннотация StopObject
            if(md.isAnnotationPresent(StopObject.class)) {hasStop=true;}
            // Как только оказалось, что обе аннотации уже найдены -
            // выходим из цикла
            if(hasStart && hasStop){break;}
        }
        // Выводим в консоль отчет о найденных или не найденных аннотациях методов
        System.out.println("Start annotaton  - " + hasStart + ";  Stop annotation  - " + hasStop );
    }
}
