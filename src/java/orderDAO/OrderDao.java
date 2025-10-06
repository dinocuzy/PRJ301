/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orderDAO;

import dao.DBConnection;
import model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {

    private static final String INSERT_ORDER =
        "INSERT INTO Orders (user_id, total_price, status) VALUES (?, ?, ?)";
    private static final String SELECT_ORDER_BY_ID =
        "SELECT * FROM Orders WHERE id = ?";
    private static final String SELECT_ALL_ORDERS =
        "SELECT * FROM Orders ORDER BY order_date DESC";
    private static final String UPDATE_ORDER =
        "UPDATE Orders SET total_price = ?, status = ? WHERE id = ?";
    private static final String DELETE_ORDER =
        "DELETE FROM Orders WHERE id = ?";
    private static final String INSERT_ORDER_DETAIL =
        "INSERT INTO OrderDetails (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

    @Override
    public void insertOrder(Order orderObj) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ORDER)) {
            ps.setInt(1, orderObj.getUserId());
            ps.setDouble(2, orderObj.getTotalPrice());
            ps.setString(3, orderObj.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Order getOrderById(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ORDER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Order.fromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> selectAllOrders() {
        List<Order> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_ORDERS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(Order.fromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteOrder(int id) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ORDER)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateOrder(Order orderObj) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_ORDER)) {
            ps.setDouble(1, orderObj.getTotalPrice());
            ps.setString(2, orderObj.getStatus());
            ps.setInt(3, orderObj.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int createOrder(Order order) {
        int newOrderId = -1;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setString(3, order.getStatus());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    newOrderId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOrderId;
    }

    @Override
    public void addOrderDetail(int orderId, int productId, int quantity, double price) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ORDER_DETAIL)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
