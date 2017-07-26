/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Users user;
    private ArrayList<OrderProducts> orderProducts;
    private String date;

    public Orders() {
    }

    public Orders(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public ArrayList<OrderProducts> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(ArrayList<OrderProducts> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
