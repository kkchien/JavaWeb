/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.util.StringUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.ProductModel;
import persistence.Author;
import persistence.Category;
import persistence.Product;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class BookController implements Serializable {

    private Product book;

    public BookController() {
        book = new Product();
        book.setCategory(new Category());
        book.setAuthor(new Author());
    }

    public Product getBook() {
        return book;
    }

    public void setBook(Product book) {
        this.book = book;
    }

    public String addBook() {
        if (book.getAuthor() == null && book.getAuthor().getId() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Phải chọn tác giả"));
        } else if (book.getCategory() == null && book.getCategory().getId() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Phải chọn thể loại"));
        } else if (StringUtil.isBlank(book.getInfo())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Phải nhập thông tin chi tiết"));
        } else if (StringUtil.isBlank(book.getName())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Phải nhập tên sách"));
        } else if (book.getPrice() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Phải nhập giá sách"));
        } else {
            try {
                ProductModel.getInstance().add(book);
                return "/admin/admin-home-page.jsf?faces-redirect=true";
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Không thể thêm sách, vui lòng thử lại sau"));
            }
        }
        return "";
    }
}
