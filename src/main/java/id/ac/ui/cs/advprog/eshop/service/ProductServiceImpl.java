package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.create(product);
    }

    @Override
    public List<Product> findAll() {
        List<Product> allProducts = new ArrayList<>();
        Iterator<Product> productIterator = productRepository.findAll();
        productIterator.forEachRemaining(allProducts::add);
        return allProducts;
    }

    @Override
    public Product findById(String itemId) {
        return productRepository.findById(itemId);
    }

    @Override
    public Product update(String itemId, Product updatedProduct) {
        return productRepository.update(itemId, updatedProduct);
    }

    @Override
    public void delete(String itemId) {
        productRepository.delete(itemId);
    }
}