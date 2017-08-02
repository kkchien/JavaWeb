/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    private final Order order;

    public CartController() {
        order = new Order();
        order.setStatus(Constant.ORDER_STATUS.DAT_HANG);
    }

    public Order getOrder() {
        return order;
    }

    public void addTocart(Product book, int quantity) {
        boolean old = false;
        for (OrderProduct item : order.getOrderProducts()) {
            if (item.getProduct().getId().equals(book.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                old = true;
                break;
            }
        }
        if (!old) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(book);
            orderProduct.setQuantity(1);
            order.getOrderProducts().add(orderProduct);
        }
        messageAddProduct("Đã thêm sản phẩm vào giỏ hàng");
        
    }
    
    public  void messageAddProduct(String text)
    {
        FacesMessage mes = new FacesMessage(FacesMessage.SEVERITY_INFO, text, null);
        FacesContext.getCurrentInstance().addMessage(null, mes);
        
    }

//    public void update(OrderProduct orderProduct) {
//        for (OrderProduct item : order.getOrderProducts()) {
//            if (item.getProduct().getId().equals(orderProduct.getProduct().getId())) {
//                item.setQuantity(orderProduct.getQuantity());
//                break;
//            }
//        }
//    }

    public String buy() {
        for (OrderProduct item : order.getOrderProducts()) {
            System.out.println(item);
        }
        return "/page/home.jsf?faces-redirect=true";
    }
}
