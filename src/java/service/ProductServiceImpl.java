/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import productDAO.IProductDAO;
import productDAO.ProductDAO;
import model.Product;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ProductServiceImpl implements ProductService {

    private IProductDAO productDAO;

    public ProductServiceImpl() {
        this.productDAO = new ProductDAO();
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        productDAO.insertProduct(product);
    }

    @Override
    public Product selectProduct(int id) {
        return productDAO.selectProduct(id);
    }

    @Override
    public List<Product> selectAllProducts() {
        return productDAO.selectAllProducts();
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        return productDAO.deleteProduct(id);
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        return productDAO.updateProduct(product);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productDAO.searchProducts(keyword);
    }
}
