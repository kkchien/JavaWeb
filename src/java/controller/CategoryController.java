/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.CategoryModel;
import persistence.Categories;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "categoryController")
@ViewScoped
public class CategoryController {

    /**
     * Creates a new instance of CategoryController
     */
    private Categories category;
    private ArrayList<Categories> categories;

    public CategoryController() {
        category = new Categories();
        try {
            //        categories = new ArrayList<>();
            categories = CategoryModel.getInstance().findAll();
        } catch (Exception ex) {
            categories = new ArrayList<>();
        }

    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

}
