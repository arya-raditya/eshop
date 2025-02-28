package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setItemId(UUID.randomUUID().toString());
        product1.setItemName("Product 1");
        product1.setItemQuantity(10);

        product2 = new Product();
        product2.setItemId(UUID.randomUUID().toString());
        product2.setItemName("Product 2");
        product2.setItemQuantity(20);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product1)).thenReturn(product1);

        Product createdProduct = productService.create(product1);

        assertEquals(product1, createdProduct);
        verify(productRepository, times(1)).create(product1);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> allProducts = productService.findAll();

        assertEquals(2, allProducts.size());
        assertTrue(allProducts.contains(product1));
        assertTrue(allProducts.contains(product2));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        String itemId = product1.getItemId();
        when(productRepository.findById(itemId)).thenReturn(product1);

        Product foundProduct = productService.findById(itemId);

        assertEquals(product1, foundProduct);
        verify(productRepository, times(1)).findById(itemId);
    }

    @Test
    void testUpdateProduct() {
        String itemId = product1.getItemId();
        Product updatedProduct = new Product();
        updatedProduct.setItemId(itemId);
        updatedProduct.setItemName("Updated Product Name");
        updatedProduct.setItemQuantity(30);

        when(productRepository.update(itemId, updatedProduct)).thenReturn(updatedProduct);

        Product resultProduct = productService.update(itemId, updatedProduct);

        assertEquals(updatedProduct, resultProduct);
        verify(productRepository, times(1)).update(itemId, updatedProduct);
    }

    @Test
    void testDeleteProduct() {
        String itemId = product1.getItemId();

        productService.delete(itemId);

        verify(productRepository, times(1)).delete(itemId);
    }
}