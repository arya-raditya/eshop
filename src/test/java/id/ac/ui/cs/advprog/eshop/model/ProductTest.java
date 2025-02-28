package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {
    Product product;

    @BeforeEach
    void setup() {
        this.product = new Product();
        this.product.setItemId("123e4567-e89b-12d3-a456-556642440000");
        this.product.setItemName("Product 1");
        this.product.setItemQuantity(100);
    }

    @Test
    public void testgetItemId() {
        assert(this.product.getItemId().equals("123e4567-e89b-12d3-a456-556642440000"));
    }

    @Test
    public void testgetItemName() {
        assert(this.product.getItemName().equals("Product 1"));
    }

    @Test
    public void testgetItemQuantity() {
        assert(this.product.getItemQuantity() == 100);
    }
}