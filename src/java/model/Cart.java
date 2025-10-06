/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Integer, CartItem> items;

    public Cart() {
        items = new HashMap<>();
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public void addItem(Product product, int quantity) {
        int productId = product.getId();

        if (items.containsKey(productId)) {
            CartItem existingItem = items.get(productId);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(product, quantity);
            items.put(productId, newItem);
        }
    }

    public void removeItem(int productId) {
        items.remove(productId);
    }

    public void clear() {
        items.clear();
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getTotalQuantity() {
        int sum = 0;
        for (CartItem item : items.values()) {
            sum += item.getQuantity();
        }
        return sum;
    }
}
