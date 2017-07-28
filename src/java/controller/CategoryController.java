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
import model.CategoryModel;
import persistence.Category;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "categoryController")
@ViewScoped
public class CategoryController implements Serializable {

    /**
     * Creates a new instance of CategoryController
     */
    private ArrayList<Category> categories;

    public CategoryController() {
        try {
            //        categories = new ArrayList<>();
            categories = CategoryModel.getInstance().findAll();
        } catch (Exception ex) {
            categories = new ArrayList<>();
        }
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

}
