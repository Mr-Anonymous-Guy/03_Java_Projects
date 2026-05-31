package app.model;

public class OrderItem {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private double priceAtTime;

    public OrderItem(int id, int orderId, int productId, int quantity, double priceAtTime) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
    }

    public int getId() { return id; }
    public int getOrderId() { return orderId; }
    public int getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPriceAtTime() { return priceAtTime; }
}
