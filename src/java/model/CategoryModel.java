/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import persistence.Categories;

/**
 *
 * @author Administrator
 */
public class CategoryModel extends AbstractModel<Categories> {

    public Categories find(int id) throws SQLException {
        String sql = "SELECT * FROM categories WHERE id = ?";
        stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        Categories p = new Categories();
        while (rs.next()) {
            p.setId(rs.getInt(rs.findColumn("id")));
            p.setName(rs.getString(rs.findColumn("name")));
        }
        closeConnection();
        return p;
    }
    
    public ArrayList<Categories> findAll() throws SQLException {
        String sql = "SELECT * FROM categories";
        connection = getConnection();
        stmt = getConnection().prepareStatement(sql);
        rs = stmt.executeQuery();
        ArrayList<Categories> arr = new ArrayList<>();
        Categories p;
        while (rs.next()) {
            p = new Categories();
            p.setId(rs.getInt(rs.findColumn("id")));
            p.setName(rs.getString(rs.findColumn("name")));
            arr.add(p);
        }
        closeConnection();
        return arr;
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
