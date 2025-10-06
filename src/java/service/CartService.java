/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Product;

/**
 *
 */
public interface CartService {

    Cart getCart(HttpSession session);

    void addToCart(HttpSession session, Product product, int quantity);

    void updateQuantity(HttpSession session, int productId, int quantity);

    void removeFromCart(HttpSession session, int productId);

    void clearCart(HttpSession session);

    double getTotal(HttpSession session);
}
