/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
    private OrderProduct op;

    public CartController() {
        o = new Order();

    }

    public String AddProductInCart(Product book, int quantity) {
        op = new OrderProduct();
        op.setOrderId(o.getId());
        op.setProduct(book);
        op.setQuantity(quantity);
        o.getOrderProducts().add(op);
        
      
        return "/page/cartProduct.jsf?faces-redirect=true";
    }

    public Order getO() {
        return o;
    }

    public void setO(Order o) {
        this.o = o;
    }

}
