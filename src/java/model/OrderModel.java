/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import persistence.Order;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class OrderModel extends AbstractModel<Order> {

    public void add(Order order) throws SQLException {
        connection = getConnection();
        String sql = "INSERT INTO orders (user_id) VALUES(?)";
        stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        UserModel.getInstance().add(order.getUser());
        stmt.setInt(1, order.getUser().getId());
        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            closeConnection();
            throw new SQLException("Creating failed, no rows affected.");
        } else {
            order.setId(getGeneratedId());
        }
        closeConnection();
    }

    public void delete(Order e) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, e.getId());
        int affectedRows = stmt.executeUpdate();
        closeConnection();
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
    }

    public ArrayList<Order> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        stmt = getConnection().prepareStatement(sql);
        rs = stmt.executeQuery();
        ArrayList<Order> arr = new ArrayList<>();
        Order o;
        while (rs.next()) {
            o = new Order();
            o.setId(rs.getInt(rs.findColumn("id")));
            o.setUser(UserModel.getInstance().find(rs.getInt(rs.findColumn("user_id"))));
            o.setOrderProducts(OrderProductModel.getInstance().findByOrderId(o.getId()));
            arr.add(o);
        }
        closeConnection();
        return arr;
    }

    private static class OrdeModelHolder {

        private static final OrderModel INSTANCE = new OrderModel();
    }

    private OrderModel() {

    }

    public static OrderModel getInstance() {
        return OrdeModelHolder.INSTANCE;
    }
}
