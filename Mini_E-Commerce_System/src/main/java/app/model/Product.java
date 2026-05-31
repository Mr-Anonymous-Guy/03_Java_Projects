package app.model;

public class Product {
    private int id;
    private int categoryId;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Product(int id, int categoryId, String name, String description, double price, int stock) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public int getCategoryId() { return categoryId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}
