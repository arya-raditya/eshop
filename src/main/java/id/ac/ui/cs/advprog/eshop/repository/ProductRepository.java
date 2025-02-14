package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product save(Product product) { // Use save for both create and update
        if (product.getProductId() == null) {
            product.setProductId(UUID.randomUUID().toString());
            productData.add(product);
        } else {
            // Find the existing product and update it
            for (int i = 0; i < productData.size(); i++) {
                if (productData.get(i).getProductId().equals(product.getProductId())) {
                    productData.set(i, product);
                    return product;
                }
            }
            // If the product ID is not found, it's an error.  Handle it as appropriate
            return null; // Or throw an exception
        }
        return product;
    }

    public List<Product> findAll() {
        return productData;
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public Product update(String productId, Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(productId)) {
                product.setProductName(updatedProduct.getProductName());
                product.setProductQuantity(updatedProduct.getProductQuantity());
                return product;
            }
        }
        return null; // Product not found
    }
}