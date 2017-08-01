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

    public static AuthorModel getInstance() {
        return AuthorModelHolder.INSTANCE;
    }

    private static class AuthorModelHolder {

        private static final AuthorModel INSTANCE = new AuthorModel();
    }
}
