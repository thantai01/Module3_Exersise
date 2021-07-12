package model;

public class Product {
    private String p_id;
    private String p_name;
    private String price;
    private int stock_quantity;
    private String color;
    private String category;

    public Product(String p_id, String p_name, String price, int stock_quantity, String color, String category) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.color = color;
        this.category = category;
    }

    public Product() {
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
