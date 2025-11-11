package org.example;

import java.util.Objects;
import java.util.UUID;

/**
 * @author aswankib
 * @since 11/10/25
 */
public class CartItem {
    private final String productId;
    private final String name;
    private final double unitPrice;
    private int quantity;

    public CartItem(String name, double unitPrice) {
        assert unitPrice > 0;

        this.productId = UUID.randomUUID().toString();
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = 0;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity ++;
    }

    public boolean canDecrement() {
        return quantity > 1;
    }

    public void decrementQuantity() {
        assert quantity > 1;

        quantity --;
    }

    public double getTotalAmount() {
        return unitPrice * quantity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CartItem cartItem = (CartItem) object;
        return Objects.equals(productId, cartItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }

    public String getNameAndQuantity() {
        return "Name : " + name +
                ",  Quantity: " + quantity +
                ",  Unit price: " + unitPrice +
                ",  Total Amount: " + getTotalAmount();
    }
}
