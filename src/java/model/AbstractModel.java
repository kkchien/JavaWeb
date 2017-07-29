/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public abstract class AbstractModel<E> {

    protected Connection connection;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    public static final int ITEM_PER_PAGE = 5;

    protected Connection getConnection() {
        return DatabaseUtil.connectSQL("//localhost\\SQLEXPRESS", "java_web", "sa", "1234$");
    }

    protected void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected int getGeneratedId() throws SQLException {
        rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException("Creating failed, no ID obtained.");
        }
    }
    
//    public static void main(String[] args) {
//        try {
//            DatabaseUtil.connectSqlite("db\\", "database", null, null).prepareStatement("SELECT * FROM products");
//        } catch (SQLException ex) {
//            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
}
