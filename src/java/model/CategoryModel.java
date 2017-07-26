/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import persistence.Categories;

/**
 *
 * @author Administrator
 */
public class CategoryModel {
    
    public Categories find(int id){
        return null;
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
