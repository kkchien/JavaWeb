/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.Category;

/**
 *
 * @author Administrator
 */
public class CategoryModel extends AbstractModel<Category> {

    public Category find(int id) throws SQLException {
        try {
            String sql = "SELECT * FROM categories WHERE id = ?";
            stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Category p = new Category();
            while (rs.next()) {
                p.setId(rs.getInt(rs.findColumn("id")));
                p.setName(rs.getString(rs.findColumn("name")));
            }
            return p;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
    }

    public ArrayList<Category> findAll() throws SQLException {
        try {
            String sql = "SELECT * FROM categories";
            connection = getConnection();
            stmt = getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<Category> arr = new ArrayList<>();
            Category p;
            while (rs.next()) {
                p = new Category();
                p.setId(rs.getInt(rs.findColumn("id")));
                p.setName(rs.getString(rs.findColumn("name")));
                arr.add(p);
            }
            return arr;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
    }

    private CategoryModel() {
    }

    public static CategoryModel getInstance() {
        return CategoryModelHolder.INSTANCE;
    }

    private static class CategoryModelHolder {

        private static final CategoryModel INSTANCE = new CategoryModel();
    }

}
