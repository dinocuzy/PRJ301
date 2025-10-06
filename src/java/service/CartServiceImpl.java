/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.servlet.http.HttpSession;
import java.util.Map;
import model.Cart;
import model.CartItem;
import model.Product;

public class CartServiceImpl implements CartService {

    @Override
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @Override
    public void addToCart(HttpSession session, Product product, int quantity) {
        Cart cart = getCart(session);
        Map<Integer, CartItem> items = cart.getItems();

        if (items.containsKey(product.getId())) {
            CartItem existing = items.get(product.getId());
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(product, quantity);
            items.put(product.getId(), newItem);
        }

        session.setAttribute("cart", cart);
    }

    @Override
    public void updateQuantity(HttpSession session, int productId, int quantity) {
        Cart cart = getCart(session);
        if (cart.getItems().containsKey(productId)) {
            CartItem item = cart.getItems().get(productId);
            if (quantity <= 0) {
                cart.getItems().remove(productId);
            } else {
                item.setQuantity(quantity);
            }
        }
        session.setAttribute("cart", cart);
    }

    @Override
    public void removeFromCart(HttpSession session, int productId) {
        Cart cart = getCart(session);
        cart.getItems().remove(productId);
        session.setAttribute("cart", cart);
    }

    @Override
    public void clearCart(HttpSession session) {
        Cart cart = getCart(session);
        cart.getItems().clear();
        session.setAttribute("cart", cart);
    }

    @Override
    public double getTotal(HttpSession session) {
        Cart cart = getCart(session);
        return cart.getTotal();
    }
}
