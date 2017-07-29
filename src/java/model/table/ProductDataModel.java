/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.table;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.ProductModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import persistence.Product;

/**
 *
 * @author Administrator
 */
public class ProductDataModel extends LazyDataModel<Product> {

    private List<Product> books;

    public ProductDataModel(List<Product> books) {
        this.books = books;
    }

    @Override
    public Object getRowKey(Product p) {
        return p.getId();
    }

    @Override
    public Product getRowData(String key) {
        try {
            return ProductModel.getInstance().find(Integer.parseInt(key));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Product();
        }
    }

    @Override
    public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Product> data = new ArrayList<>();
        for (Product item : books) {
            boolean match = true;
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        Field f = item.getClass().getDeclaredField(filterProperty);
                        f.setAccessible(true);
                        String fieldValue = String.valueOf(f.get(item));
                        if (filterValue == null || fieldValue.contains(filterValue.toString())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
                        match = false;
                    }
                }
            }
            if (match) {
                data.add(item);
            }
        }
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}
