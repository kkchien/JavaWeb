/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.ProductSearch;
import persistence.Product;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ProductModel extends AbstractModel<Product> {

    private ProductModel() {
    }

    public static ProductModel getInstance() {
        return ProductModelHolder.INSTANCE;
    }

    public void add(Product e) throws SQLException {
        try {
            String sql = "INSERT INTO products (name, price, category_id, info, author_id, image) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, e.getName());
            stmt.setInt(2, e.getPrice());
            stmt.setInt(3, e.getCategory().getId());
            stmt.setString(4, e.getInfo());
            stmt.setInt(5, e.getAuthor().getId());
            stmt.setString(6, e.getImage());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                closeConnection();
                throw new SQLException("Creating failed, no rows affected.");
            } else {
                e.setId(getGeneratedId());
            }
            closeConnection();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
    }

    public void edit(Product e) throws SQLException {
        try {
            String sql = "UPDATE products  SET name = ?, price = ?, category_id = ?, info = ?, author_id = ?, image = ? WHERE id = ?";
            stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, e.getName());
            stmt.setInt(2, e.getPrice());
            stmt.setInt(3, e.getCategory().getId());
            stmt.setString(4, e.getInfo());
            stmt.setInt(5, e.getAuthor().getId());
            stmt.setString(6, e.getImage());
            stmt.setInt(7, e.getId());
            int affectedRows = stmt.executeUpdate();
            closeConnection();
            if (affectedRows == 0) {
                throw new SQLException("Editing failed, no rows affected.");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
    }

    public void delete(Product e) throws SQLException {
        try {
            String sql = "DELETE FROM products WHERE id = ?";
            stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, e.getId());
            int affectedRows = stmt.executeUpdate();
            closeConnection();
            if (affectedRows == 0) {
                throw new SQLException("Deleting failed, no rows affected.");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
    }

    public ArrayList<Product> findAll() throws SQLException {
        try {
            String sql = "SELECT * FROM products";
            stmt = getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<Product> arr = new ArrayList<>();
            Product p;
            while (rs.next()) {
                p = new Product();
                p.setId(rs.getInt(rs.findColumn("id")));
                p.setInfo(rs.getString(rs.findColumn("info")));
                p.setName(rs.getString(rs.findColumn("name")));
                p.setPrice(rs.getInt(rs.findColumn("price")));
                p.setImage(rs.getString(rs.findColumn("image")));
                p.setCategory(CategoryModel.getInstance().find(rs.getInt(rs.findColumn("category_id"))));
                p.setAuthor(AuthorModel.getInstance().find(rs.getInt(rs.findColumn("author_id"))));
                arr.add(p);
            }
            return arr;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
    }

     public ArrayList<Product> findAllCategory(int ls) throws SQLException {
        String sql = "SELECT * FROM products where category_id="+ls;
        stmt = getConnection().prepareStatement(sql);
        rs = stmt.executeQuery();
        ArrayList<Product> arr = new ArrayList<>();
        Product p;
        while (rs.next()) {
            p = new Product();
            p.setId(rs.getInt(rs.findColumn("id")));
            p.setInfo(rs.getString(rs.findColumn("info")));
            p.setName(rs.getString(rs.findColumn("name")));
            p.setPrice(rs.getInt(rs.findColumn("price")));
            p.setImage(rs.getString(rs.findColumn("image")));
            p.setCategory(CategoryModel.getInstance().find(rs.getInt(rs.findColumn("category_id"))));
            p.setAuthor(AuthorModel.getInstance().find(rs.getInt(rs.findColumn("author_id"))));
            arr.add(p);
        }
        closeConnection();
        return arr;
    }
    

    public Product find(int id) throws SQLException {
        try {
            String sql = "SELECT * FROM products WHERE id = ?";
            stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Product p = new Product();
            while (rs.next()) {
                p.setId(rs.getInt(rs.findColumn("id")));
                p.setInfo(rs.getString(rs.findColumn("info")));
                p.setName(rs.getString(rs.findColumn("name")));
                p.setPrice(rs.getInt(rs.findColumn("price")));
                p.setImage(rs.getString(rs.findColumn("image")));
                p.setCategory(CategoryModel.getInstance().find(rs.getInt(rs.findColumn("category_id"))));
                p.setAuthor(AuthorModel.getInstance().find(rs.getInt(rs.findColumn("author_id"))));
            }
            return p;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
    }

//    public ArrayList<Product> search(ProductSearch productSearch) throws SQLException {
//        String sql = "SELECT * FROM products WHERE name LIKE ? AND (category_id = ? OR 0 = ?) "
//                + "AND (author_id = ? OR 0 = ?) LIMIT ? OFFSET ?";
//        stmt = getConnection().prepareStatement(sql);
//        stmt.setString(1, "%" + productSearch.getName() + "%");
//        if (productSearch.getCategory() != null) {
//            stmt.setInt(2, productSearch.getCategory().getId());
//            stmt.setInt(3, productSearch.getCategory().getId());
//        } else {
//            stmt.setInt(2, 0);
//            stmt.setInt(3, 0);
//        }
//        if (productSearch.getAuthor()!= null) {
//            stmt.setInt(4, productSearch.getAuthor().getId());
//            stmt.setInt(5, productSearch.getAuthor().getId());
//        } else {
//            stmt.setInt(2, 0);
//            stmt.setInt(3, 0);
//        }
//        stmt.setInt(6, productSearch.getItemPerPage());
//        stmt.setInt(7, productSearch.getPage());
//        rs = stmt.executeQuery();
//        ArrayList<Product> arr = new ArrayList<>();
//        Product p;
//        while (rs.next()) {
//            p = new Product();
//            p.setId(rs.getInt(rs.findColumn("id")));
//            p.setInfo(rs.getString(rs.findColumn("info")));
//            p.setName(rs.getString(rs.findColumn("name")));
//            p.setPrice(rs.getInt(rs.findColumn("price")));
//            p.setImage(rs.getString(rs.findColumn("image")));
//            p.setCategory(CategoryModel.getInstance().find(rs.getInt(rs.findColumn("category_id"))));
//            p.setAuthor(AuthorModel.getInstance().find(rs.getInt(rs.findColumn("author_id"))));
//            arr.add(p);
//        }
//        closeConnection();
//        return arr;
//    }
    private static class ProductModelHolder {

        private static final ProductModel INSTANCE = new ProductModel();
    }
}
