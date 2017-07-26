/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class OrderProducts implements Serializable {
    private static final long serialVersionUID = 1L;
    private Products product;
    private Integer orderId;
    private int quantity;

    public OrderProducts() {
    }

    public OrderProducts(Integer orderId) {
        this.orderId = orderId;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
