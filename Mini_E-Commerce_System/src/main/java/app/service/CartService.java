package app.service;

import app.dao.CartDAO;
import app.dao.OrderDAO;
import app.model.CartItem;

import java.util.List;

public class CartService {
    private CartDAO cartDAO;
    private OrderDAO orderDAO;

    public CartService() {
        this.cartDAO = new CartDAO();
        this.orderDAO = new OrderDAO();
    }

    public int getCartId(int userId) {
        return cartDAO.getCartIdForUser(userId);
    }

    public List<CartItem> getCartItems(int cartId) {
        return cartDAO.getCartItems(cartId);
    }

    public void addProductToCart(int cartId, int productId, int quantity) {
        cartDAO.addToCart(cartId, productId, quantity);
    }

    public boolean checkout(int userId, int cartId, String paymentMethod) {
        List<CartItem> items = getCartItems(cartId);
        if (items.isEmpty()) return false;
        
        double total = items.stream().mapToDouble(i -> i.getUnitPrice() * i.getQuantity()).sum();
        
        boolean success = orderDAO.placeOrder(userId, total, items, paymentMethod);
        if (success) {
            cartDAO.clearCart(cartId);
        }
        return success;
    }
}
