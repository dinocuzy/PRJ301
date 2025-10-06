/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package orderDAO;

import java.sql.SQLException;
import java.util.List;
import model.Order;


/**
 *
 * @author ASUS
 */
public interface IOrderDao {
    public void insertOrder(Order orderObj) throws SQLException;
    public Order getOrderById(int id);
    public List<Order> selectAllOrders();
    public boolean deleteOrder(int id) throws SQLException;
    public boolean updateOrder(Order orderObj) throws SQLException;
    public int createOrder(Order order);
    public void addOrderDetail(int orderId, int productId, int quantity, double price);
}
