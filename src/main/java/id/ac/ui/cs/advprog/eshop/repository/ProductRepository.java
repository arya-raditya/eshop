package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository implements ItemRepositoryInterface<Product> {
    private List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product) {
        product.setItemId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    @Override
    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getItemId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product update(String id, Product updatedProduct) {
        for (Product product : productData) {
            if (product.getItemId().equals(id)) {
                product.setItemName(updatedProduct.getItemName());
                product.setItemQuantity(updatedProduct.getItemQuantity());
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        productData.removeIf(product -> product.getItemId().equals(id));
    }
}