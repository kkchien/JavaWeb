/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.util.StringUtil;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.AuthorModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import persistence.Author;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class AuthorController implements Serializable {

    private ArrayList<Author> authors;
    private Author author;
    private LazyDataModel<Author> model;

    public AuthorController() {
        try {
            authors = AuthorModel.getInstance().findAll();
        } catch (Exception ex) {
            authors = new ArrayList();
        }
        author = new Author();
        model = new LazyDataModel<Author>() {
            @Override
            public Object getRowKey(Author p) {
                return p.getId();
            }

            @Override
            public Author getRowData(String key) {
                for (Author item : authors) {
                    if (item.getId().equals(Integer.parseInt(key))) {
                        return item;
                    }
                }
                return new Author();
            }

            @Override
            public List<Author> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                this.setRowCount(authors.size());
                return authors;
            }
        };
    }

    public LazyDataModel<Author> getModel() {
        return model;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public Author getAuthor() {
        return author;
    }

    public void delete() {
        try {
            author = model.getRowData();
            AuthorModel.getInstance().delete(author);
            authors.remove(author);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Xóa tác giả thành công"));
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Lỗi kết nối tới cơ sở dữ liệu"));
        }
    }

    public void newAuthor() {
        author = new Author();
    }

    public void addAuthor() {
        if (StringUtil.isBlank(author.getName())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Phải nhập tên tác giả"));
        } else {
            try {
                AuthorModel.getInstance().add(author);
                authors.add(author);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Thêm tác giả thành công"));
                author = new Author();
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Lỗi kết nối tới cơ sở dữ liệu"));
            }
        }
    }

}
