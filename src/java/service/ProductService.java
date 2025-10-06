/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Product;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ProductService {

    void insertProduct(Product product) throws SQLException;

    Product selectProduct(int id);

    List<Product> selectAllProducts();

    boolean deleteProduct(int id) throws SQLException;

    boolean updateProduct(Product product) throws SQLException;

    List<Product> searchProducts(String keyword);
}
