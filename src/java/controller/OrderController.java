/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.OrderModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import persistence.Author;
import persistence.Order;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class OrderController {

    private ArrayList<Order> orders;
    private final LazyDataModel model;

    public OrderController() {
        try {
            orders = OrderModel.getInstance().findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            orders = new ArrayList<>();
        }
        model = new LazyDataModel<Order>() {
            @Override
            public Object getRowKey(Order p) {
                return p.getId();
            }

            @Override
            public Order getRowData(String key) {
                for (Order item : orders) {
                    if (item.getId().equals(Integer.parseInt(key))) {
                        return item;
                    }
                }
                return new Order();
            }

            @Override
            public List<Order> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                this.setRowCount(orders.size());
                return orders;
            }
        };
    }

    public LazyDataModel getModel() {
        return model;
    }
}
