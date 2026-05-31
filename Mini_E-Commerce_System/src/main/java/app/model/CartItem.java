package app.model;

public class CartItem {
    private int id;
    private int cartId;
    private int productId;
    private String productName;
    private int quantity;
    private double unitPrice;

    public CartItem(int id, int cartId, int productId, String productName, int quantity, double unitPrice) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getId() { return id; }
    public int getCartId() { return cartId; }
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
}
