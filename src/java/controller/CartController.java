/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import common.util.StringUtil;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.OrderModel;
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
    private Order order;

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

//    public void update(int id) {
//        for (OrderProduct item : order.getOrderProducts()) {
//            if (item.getProduct().getId().equals(id)) {
//                item.setQuantity(quantity);
//                break;
//            }
//        }
//        FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage("Cập nhật thành công"));
//    }
    public String buy() {
        if (order.getOrderProducts().size() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Bạn không có sản phẩm nào trong giỏ hàng"));
        } else {
            if (StringUtil.isBlank(order.getUser().getName())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Phải nhập họ tên"));
            } else if (StringUtil.isBlank(order.getUser().getPhone())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Phải nhập số điện thoại"));
            } else {
                try {
                    OrderModel.getInstance().add(order);
                    order = new Order();
                    order.setStatus(Constant.ORDER_STATUS.DAT_HANG);
                    return "/page/home.jsf?faces-redirect=true";
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Lỗi kết nối, vui lòng thử lại sau"));
                }
            }
        }
        return "";
    }
}
