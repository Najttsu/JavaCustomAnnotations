/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.customannotations.controller;

import org.tyaa.customannotations.annotations.Inject;
import org.tyaa.customannotations.bean.Cookies;
import org.tyaa.customannotations.bean.Session;

/**
 *
 * @author student
 */

public class SessionController {
    
    @Inject
    public Cookies mCookies;

    @Inject
    public Session mSession;
    
    // @Inject
    public String mString = "str";
    
    public void doWork(){
    
        mCookies.createCookie();
        mCookies.stopCookie();
        //mString.getBytes();
        mSession.start();
        mSession.finish();
    }
}
