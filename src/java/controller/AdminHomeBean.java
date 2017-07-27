/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;


/**
 *
 * @author Administrator
 */
public class AdminHomeBean implements Serializable{

    /**
     * Creates a new instance of AdminHomeBean
     */
    public AdminHomeBean() {
        
    }
    
    public String logout(){
        return "admin/login";
    }
}
