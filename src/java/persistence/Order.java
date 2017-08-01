/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private User user;
    private ArrayList<OrderProduct> orderProducts;
    private String date;
    private int status;

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(ArrayList<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
