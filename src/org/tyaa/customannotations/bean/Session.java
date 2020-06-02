package org.tyaa.customannotations.bean;

import org.tyaa.customannotations.annotations.ControlledObject;
import org.tyaa.customannotations.annotations.StartObject;
import org.tyaa.customannotations.annotations.StopObject;

@ControlledObject(name = "name")
public class Session {

    @StartObject
    public void start (){
        System.out.println("start");
    }

    @StopObject
    public void finish (){
        System.out.println("finish");
    }
}
