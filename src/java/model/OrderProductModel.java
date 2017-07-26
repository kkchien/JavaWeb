/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import persistence.OrderProducts;
import persistence.Orders;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class OrderProductModel extends AbstractModel<OrderProducts> {

    private OrderProductModel() {
    }

    public static OrderProductModel getInstance() {
        return OrderProductModelHolder.INSTANCE;
    }

    public void add(OrderProducts e) throws SQLException {
        connection = getConnection();
        String sql = "INSERT INTO order_products (order_id, product_id, quantity) VALUES(?, ?, ?)";
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, e.getOrderId());
        stmt.setInt(2, e.getProduct().getId());
        stmt.setInt(3, e.getQuantity());
        int affectedRows = stmt.executeUpdate();
        closeConnection();
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
    }

    public void deleteByOrderId(int orderId) throws SQLException {
        String sql = "DELETE order_products WHERE order_id = ?";
        stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, orderId);
        int affectedRows = stmt.executeUpdate();
        closeConnection();
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
    }

    public ArrayList<OrderProducts> findByOrderId(int id) throws SQLException {
        String sql = "SELECT * FROM order_products WHERE order_id = ?";
        stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        ArrayList<OrderProducts> arr = new ArrayList<>();
        OrderProducts o;
        while (rs.next()) {
            o = new OrderProducts();
            o.setOrderId(id);
            o.setProduct(ProductModel.getInstance().find(rs.getInt(rs.findColumn("product_id"))));
            o.setQuantity(rs.getInt(rs.findColumn("quantity")));
            arr.add(o);
        }
        closeConnection();
        return arr;
    }

    private static class OrderProductModelHolder {

        private static final OrderProductModel INSTANCE = new OrderProductModel();
    }
}
