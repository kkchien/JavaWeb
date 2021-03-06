/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import persistence.User;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserModel extends AbstractModel<User> {

    private UserModel() {
    }

    public static UserModel getInstance() {
        return UserModelHolder.INSTANCE;
    }

    public void add(User e) throws SQLException {
        try {
            String sql = "INSERT INTO users (name, email, phone, address) VALUES (?, ?, ?, ?)";
            stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, e.getName());
            stmt.setString(2, e.getEmail());
            stmt.setString(3, e.getPhone());
            stmt.setString(4, e.getAddress());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating failed, no rows affected.");
            } else {
                e.setId(getGeneratedId());
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
    }

    public void edit(User e) throws SQLException {
        try {
            String sql = "UPDATE users  SET name = ?, email = ?, phone = ?, address = ? WHERE id = ?";
            stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, e.getName());
            stmt.setString(2, e.getEmail());
            stmt.setString(3, e.getPhone());
            stmt.setString(4, e.getAddress());
            stmt.setInt(5, e.getId());
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

    public void delete(User e) throws SQLException {
        try {
            String sql = "DELETE FROM users WHERE id = ?";
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

//    public ArrayList<User> findAll() throws SQLException {
//        String sql = "SELECT * FROM users";
//        stmt = getConnection().prepareStatement(sql);
//        rs = stmt.executeQuery();
//        ArrayList<User> arr = new ArrayList<>();
//        User u;
//        while (rs.next()) {
//            u = new User();
//            u.setId(rs.getInt(rs.findColumn("id")));
//            u.setAddress(rs.getString(rs.findColumn("address")));
//            u.setEmail(rs.getString(rs.findColumn("email")));
//            u.setName(rs.getString(rs.findColumn("name")));
//            u.setPhone(rs.getString(rs.findColumn("phone")));
//            arr.add(u);
//        }
//        closeConnection();
//        return arr;
//    }

    public User find(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        User u = new User();
        while (rs.next()) {
            u.setId(rs.getInt(rs.findColumn("id")));
            u.setAddress(rs.getString(rs.findColumn("address")));
            u.setEmail(rs.getString(rs.findColumn("email")));
            u.setName(rs.getString(rs.findColumn("name")));
            u.setPhone(rs.getString(rs.findColumn("phone")));
        }
        closeConnection();
        return u;
    }

    private static class UserModelHolder {

        private static final UserModel INSTANCE = new UserModel();
    }
}
