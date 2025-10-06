/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productDAO;

import model.Product;
import dao.DBConnection;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class ProductDAO implements IProductDAO {

    private static final String INSERT_SQL
            = "INSERT INTO Product (name, price, description, stock) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID
            = "SELECT id, name, price, description, stock, import_date FROM Product WHERE id = ?";
    private static final String SELECT_ALL
            = "SELECT id, name, price, description, stock, import_date FROM Product";
    private static final String DELETE_SQL
            = "DELETE FROM Product WHERE id = ?";
    private static final String UPDATE_SQL
            = "UPDATE Product SET name = ?, price = ?, description = ?, stock = ? WHERE id = ?";
    private static final String SEARCH_SQL
            = "SELECT id, name, price, description, stock, import_date FROM Product WHERE name LIKE ?";

    @Override
    public void insertProduct(Product pro) throws SQLException {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, pro.getName());
            ps.setDouble(2, pro.getPrice());
            ps.setString(3, pro.getDescription());
            ps.setInt(4, pro.getStock());
            ps.executeUpdate();
        }
    }

    @Override
    public Product selectProduct(int id) {
        Product pro = null;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int stock = rs.getInt("stock");
                Timestamp ts = rs.getTimestamp("import_date");
                pro = new Product(id, name, price, description, stock, ts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pro;
    }

    @Override
    public List<Product> selectAllProducts() {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int stock = rs.getInt("stock");
                Timestamp ts = rs.getTimestamp("import_date");
                list.add(new Product(id, name, price, description, stock, ts));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product pro) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, pro.getName());
            ps.setDouble(2, pro.getPrice());
            ps.setString(3, pro.getDescription());
            ps.setInt(4, pro.getStock());
            ps.setInt(5, pro.getId());
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SEARCH_SQL)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int stock = rs.getInt("stock");
                Timestamp ts = rs.getTimestamp("import_date");
                list.add(new Product(id, name, price, description, stock, ts));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
