/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.util.DateTimeUtil;
import persistence.Order;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.User;

/**
 *
 * @author Administrator
 */
public class OrderModel extends AbstractModel<Order> {

    public void add(Order order) throws SQLException {
        try {
            connection = getConnection();
            String sql = "INSERT INTO orders (user_id) VALUES(?)";
            stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            UserModel.getInstance().add(order.getUser());
            stmt.setInt(1, order.getUser().getId());
//            stmt.setDate(2, DateTimeUtil.getCurrentSqlDay());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                closeConnection();
                throw new SQLException("Creating failed, no rows affected.");
            } else {
                order.setId(getGeneratedId());
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
    }

    public void delete(Order e) throws SQLException {
        try {
            String sql = "DELETE FROM orders WHERE id = ?";
            stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, e.getId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
    }

    public ArrayList<Order> findAll() throws SQLException {
        try {
            String sql = "SELECT * FROM orders";
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
            return arr;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
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
