/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.util.StringUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.ProductModel;
import model.table.ProductDataModel;
import org.primefaces.model.LazyDataModel;
import persistence.Product;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "bookController")
//@ViewScoped
@SessionScoped
public class BookController implements Serializable {

    private Product book;
    private ArrayList<Product> arr;
    private LazyDataModel<Product> books;

    public BookController() {
        try {
            arr = ProductModel.getInstance().findAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Lỗi kết nối tới cơ sở dữ liệu"));
            arr = new ArrayList<>();
        }
        book = new Product();
        books = new ProductDataModel(arr);
    }
    //Dịnh dạng money
   // public

    public String ProductDetail( Product book) {
        this.book = book;
        return "detailProduct.jsf?faces-redirect=true";
    }

    public String chuyenTrangTheoLoai(int loaisach){
        try {
            this.arr = ProductModel.getInstance().findAllCategory(loaisach);
            this.book = arr.get(0);
        } catch (SQLException ex) {
            this.arr = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Không có sách thuộc thể loại này"));
        }
        return "/page/home.jsf?faces-redirect=true";
    }
    public String chuyenTrangTuGioHang(){
        return "home.jsf?faces-redirect=true";
    }
    

    public ArrayList<Product> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Product> arr) {
        this.arr = arr;
    }

    
    public Product getBook() {
        return book;
    }

    public LazyDataModel<Product> getBooks() {
        return books;
    }


    private boolean validate() {
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
            return true;
        }
        return false;
    }

    public void newBook() {
        book = new Product();
    }

    public void addBook() {
        if (validate()) {
            try {
                ProductModel.getInstance().add(book);
                ((ProductDataModel) books).addBook(book);
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Không thể thêm sách, vui lòng thử lại sau"));
            }
        }
    }

    public void editBook() {
        if (validate()) {
            try {
                ProductModel.getInstance().edit(book);
                ((ProductDataModel) books).editBook(book);
            } catch (SQLException ex) {
                ex.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Không thể thêm sách, vui lòng thử lại sau"));
            }
        }
    }

    public void viewBook() {
        book = books.getRowData();
    }

    public void deleteBook() {
        try {
            book = books.getRowData();
            ProductModel.getInstance().delete(book);
            ((ProductDataModel) books).removeBook(book);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Xóa thành công"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Không thể xóa được"));
        }
    }
}
