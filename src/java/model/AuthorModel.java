/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import persistence.Authors;

/**
 *
 * @author Administrator
 */
public class AuthorModel extends AbstractModel<Authors> {

    private AuthorModel() {
    }

    public Authors find(int id) throws SQLException {
        String sql = "SELECT * FROM authors WHERE id = ?";
        stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        Authors p = new Authors();
        while (rs.next()) {
            p.setId(rs.getInt(rs.findColumn("id")));
            p.setName(rs.getString(rs.findColumn("name")));
        }
        closeConnection();
        return p;
    }

    public ArrayList<Authors> findAll() throws SQLException {
        String sql = "SELECT * FROM categories";
        stmt = getConnection().prepareStatement(sql);
        rs = stmt.executeQuery();
        ArrayList<Authors> arr = new ArrayList<>();
        Authors p;
        while (rs.next()) {
            p = new Authors();
            p.setId(rs.getInt(rs.findColumn("id")));
            p.setName(rs.getString(rs.findColumn("name")));
            arr.add(p);
        }
        closeConnection();
        return arr;
    }

    public static AuthorModel getInstance() {
        return AuthorModelHolder.INSTANCE;
    }

    private static class AuthorModelHolder {

        private static final AuthorModel INSTANCE = new AuthorModel();
    }
}
