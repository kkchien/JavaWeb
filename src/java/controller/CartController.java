/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import persistence.Order;

/**
 *
 * @author M4700
 */
@ManagedBean
@SessionScoped
public class CartController {

    /**
     * Creates a new instance of CartController
     */
    private Order o; 
    
    
    public CartController() {
        o = new Order();
        o.getOrderProducts();
    }

    public Order getO() {
        return o;
    }

    public void setO(Order o) {
        this.o = o;
    }
    
}
