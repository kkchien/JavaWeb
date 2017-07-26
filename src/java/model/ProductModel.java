/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.ProductSearch;
import persistence.Products;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.Categories;

/**
 *
 * @author Administrator
 */
public class ProductModel extends AbstractModel<Products> {

    private ProductModel() {
    }

    public static ProductModel getInstance() {
        return ProductModelHolder.INSTANCE;
    }

    public void add(Products e) throws SQLException {
        String sql = "INSERT INTO products (name, price, category_id, info) VALUES (?, ?, ?, ?)";
        stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, e.getName());
        stmt.setInt(2, e.getPrice());
        stmt.setInt(3, e.getCategorie().getId());
        stmt.setString(4, e.getInfo());
        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            closeConnection();
            throw new SQLException("Creating failed, no rows affected.");
        } else {
            e.setId(getGeneratedId());
        }
        closeConnection();
    }

    public void edit(Products e) throws SQLException {
        String sql = "UPDATE products  SET name = ?, price = ?, category_id = ?, info = ? WHERE id = ?";
        stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, e.getName());
        stmt.setInt(2, e.getPrice());
        stmt.setInt(3, e.getCategorie().getId());
        stmt.setString(4, e.getInfo());
        stmt.setInt(5, e.getId());
        int affectedRows = stmt.executeUpdate();
        closeConnection();
        if (affectedRows == 0) {
            throw new SQLException("Editing failed, no rows affected.");
        }
    }

    public void delete(Products e) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, e.getId());
        int affectedRows = stmt.executeUpdate();
        closeConnection();
        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        }
    }

    public ArrayList<Products> findAll() throws SQLException {
        String sql = "SELECT * FROM products";
        stmt = getConnection().prepareStatement(sql);
        rs = stmt.executeQuery();
        ArrayList<Products> arr = new ArrayList<>();
        Products p;
        while (rs.next()) {
            p = new Products();
            p.setId(rs.getInt(rs.findColumn("id")));
            p.setInfo(rs.getString(rs.findColumn("info")));
            p.setName(rs.getString(rs.findColumn("name")));
            p.setPrice(rs.getInt(rs.findColumn("price")));
            p.setCategorie(CategoryModel.getInstance().find(rs.getInt(rs.findColumn("category_id"))));
            arr.add(p);
        }
        closeConnection();
        return arr;
    }

    public Products find(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        Products p = new Products();
        while (rs.next()) {
            p.setId(rs.getInt(rs.findColumn("id")));
            p.setInfo(rs.getString(rs.findColumn("info")));
            p.setName(rs.getString(rs.findColumn("name")));
            p.setPrice(rs.getInt(rs.findColumn("price")));
            p.setCategorie(CategoryModel.getInstance().find(rs.getInt(rs.findColumn("category_id"))));
        }
        closeConnection();
        return p;
    }

    public ArrayList<Products> search(ProductSearch productSearch) throws SQLException {
        String sql = "SELECT * FROM products WHERE name LIKE ? AND (category_id = ? OR 0 = ?) LIMIT ? OFFSET ?";
        stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, "%" + productSearch.getName() + "%");
        if (productSearch.getCategorie() != null) {
            stmt.setInt(2, productSearch.getCategorie().getId());
            stmt.setInt(3, productSearch.getCategorie().getId());
        } else {
            stmt.setInt(2, 0);
            stmt.setInt(3, 0);
        }
        stmt.setInt(4, productSearch.getItemPerPage());
        stmt.setInt(5, productSearch.getPage());
        rs = stmt.executeQuery();
        ArrayList<Products> arr = new ArrayList<>();
        Products p;
        while (rs.next()) {
            p = new Products();
            p.setId(rs.getInt(rs.findColumn("id")));
            p.setInfo(rs.getString(rs.findColumn("info")));
            p.setName(rs.getString(rs.findColumn("name")));
            p.setPrice(rs.getInt(rs.findColumn("price")));
            p.setCategorie(CategoryModel.getInstance().find(rs.getInt(rs.findColumn("category_id"))));
            arr.add(p);
        }
        closeConnection();
        return arr;
    }

    private static class ProductModelHolder {

        private static final ProductModel INSTANCE = new ProductModel();
    }
}
