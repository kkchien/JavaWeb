/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import model.AbstractModel;
import persistence.Products;

/**
 *
 * @author Administrator
 */
public class ProductSearch extends Products {

    int page = 0;
    int itemPerPage = AbstractModel.ITEM_PER_PAGE;

    public ProductSearch() {
        name = "";
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

}
