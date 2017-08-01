/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import persistence.Author;

/**
 *
 * @author Administrator
 */
public class AuthorModel extends AbstractModel<Author> {

    private AuthorModel() {
    }

    public Author find(int id) throws SQLException {
        try {
            String sql = "SELECT * FROM authors WHERE id = ?";
            stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Author p = new Author();
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

    public ArrayList<Author> findAll() throws Exception {
        try {
            String sql = "SELECT * FROM authors";
            stmt = getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<Author> arr = new ArrayList<>();
            Author p;
            while (rs.next()) {
                p = new Author();
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
    public void add(Author e) throws SQLException {
        try {
            String sql = "INSERT INTO authors (name) VALUES (?)";
            stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, e.getName());
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
    
    public void delete(Author e) throws SQLException {
        try {
            String sql = "DELETE FROM authors WHERE id = ?";
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

    public static AuthorModel getInstance() {
        return AuthorModelHolder.INSTANCE;
    }

    private static class AuthorModelHolder {

        private static final AuthorModel INSTANCE = new AuthorModel();
    }
}
