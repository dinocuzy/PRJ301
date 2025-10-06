/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.Order;
import orderDAO.IOrderDao;
import orderDAO.OrderDao;

/**
 *
 * @author ASUS
 */
public class OrderServiceImpl implements OrderService {

    private final IOrderDao orderDao;

    public OrderServiceImpl() {
        this.orderDao = new OrderDao();
    }

    @Override
    public void insertOrder(Order order) throws SQLException {
        orderDao.insertOrder(order);
    }

    @Override
    public Order getOrderById(int id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.selectAllOrders();
    }

    @Override
    public boolean deleteOrder(int id) throws SQLException {
        return orderDao.deleteOrder(id);
    }

    @Override
    public boolean updateOrder(Order order) throws SQLException {
        return orderDao.updateOrder(order);
    }

    @Override
    public int createOrder(Order order) {
        return orderDao.createOrder(order);
    }

    @Override
    public void addOrderDetail(int orderId, int productId, int quantity, double price) {
        orderDao.addOrderDetail(orderId, productId, quantity, price);
    }
}

