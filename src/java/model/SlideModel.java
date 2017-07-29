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
import persistence.Slide;

/**
 *
 * @author M4700
 */
public class SlideModel extends AbstractModel<Slide> {

    public SlideModel() {
    }
  
    public ArrayList<Slide> getAll() {

        ArrayList<Slide> arr = new ArrayList<>();

        try {
            String sql = "select * from slide";
            stmt = getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();
            Slide sl;
            while(rs.next())
            {
                sl = new Slide();
                sl.setId(rs.getInt(rs.findColumn("id")));
                sl.setName(rs.getString(rs.findColumn("name")));
                
                arr.add(sl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SlideModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return arr;
    }

}
