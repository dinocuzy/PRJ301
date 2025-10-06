/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Product {

    private int id;                 // ID tự động tăng, khóa chính
    private String name;            // Tên sản phẩm
    private double price;           // Giá sản phẩm
    private String description;     // Mô tả sản phẩm
    private int stock;              // Số lượng tồn kho
    private Date importDate;        // Ngày nhập sản phẩm

    // Constructor mặc định
    public Product() {
    }

    public Product(int id, String name, double price, String description, int stock, Date importDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.importDate = importDate;
    }

    public Product(String name, double price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", price=" + price
                + ", description='" + description + '\''
                + ", stock=" + stock
                + ", importDate=" + importDate
                + '}';
    }
}
