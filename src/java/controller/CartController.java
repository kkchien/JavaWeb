/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import persistence.Order;
import persistence.OrderProduct;
import persistence.Product;

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
        o.setStatus(Constant.ORDER_STATUS.DAT_HANG);
    }

    public Order getO() {
        return o;
    }

    public void setO(Order o) {
        this.o = o;
    }
    
    public void addTocart(Product book){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(book);
        orderProduct.setQuantity(1);
        o.getOrderProducts().add(orderProduct);
    }
    
}
