/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.AuthorModel;
import persistence.Author;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class AuthorController implements Serializable  {

    private ArrayList<Author> authors;
    private Author author;

    public AuthorController() {
        try {
            authors = AuthorModel.getInstance().findAll();
        } catch (Exception ex) {
            authors = new ArrayList();
        }
        author = new Author();
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }
    
    public void delete(){
//        AuthorModel.getInstance().
    }

}
