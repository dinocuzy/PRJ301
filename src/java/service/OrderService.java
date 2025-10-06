/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.Order;

/**
 *
 * @author ASUS
 */
public interface OrderService {

    void insertOrder(Order order) throws SQLException;

    Order getOrderById(int id);

    List<Order> getAllOrders();

    boolean deleteOrder(int id) throws SQLException;

    boolean updateOrder(Order order) throws SQLException;

    int createOrder(Order order) throws SQLException;

    void addOrderDetail(int orderId, int productId, int quantity, double price);
}
