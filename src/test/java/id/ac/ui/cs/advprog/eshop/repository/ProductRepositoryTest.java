package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setItemName("Product 1");
        product.setItemQuantity(100);
        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getItemId());

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();

        assertEquals(createdProduct.getItemId(), savedProduct.getItemId());
        assertEquals(product.getItemName(), savedProduct.getItemName());
        assertEquals(product.getItemQuantity(), savedProduct.getItemQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setItemName("Product 1");
        product1.setItemQuantity(100);
        product1 = productRepository.create(product1);

        Product product2 = new Product();
        product2.setItemName("Product 2");
        product2.setItemQuantity(200);
        product2 = productRepository.create(product2);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        assertEquals(product1.getItemId(), savedProduct.getItemId());

        savedProduct = products.next();
        assertEquals(product2.getItemId(), savedProduct.getItemId());

        assertFalse(products.hasNext());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setItemName("Product 1");
        product.setItemQuantity(100);
        product = productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setItemName("Product 1 Updated");
        updatedProduct.setItemQuantity(200);
        Product result = productRepository.update(product.getItemId(), updatedProduct);

        assertNotNull(result);
        Product foundProduct = productRepository.findById(product.getItemId());
        assertNotNull(foundProduct);
        assertEquals("Product 1 Updated", foundProduct.getItemName());
        assertEquals(200, foundProduct.getItemQuantity());
    }

    @Test
    void testUpdateProduct_NotFound() {
        // Create a product first
        Product product = new Product();
        product.setItemName("Product 1");
        product.setItemQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setItemName("Product 1 Updated");
        updatedProduct.setItemQuantity(200);
        Product result = productRepository.update(UUID.randomUUID().toString(), updatedProduct);

        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setItemName("Product 1");
        product.setItemQuantity(100);
        product = productRepository.create(product);

        productRepository.delete(product.getItemId());

        Product foundProduct = productRepository.findById(product.getItemId());
        assertNull(foundProduct);

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testFindById_ProductNotFound() {
        Product foundProduct = productRepository.findById(UUID.randomUUID().toString());
        assertNull(foundProduct);
    }

    @Test
    void testCreateProductWithNullValues() {
        Product product = new Product();
        product.setItemName(null);
        product.setItemQuantity(0);
        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getItemId());
        assertNull(createdProduct.getItemName());
        assertEquals(0, createdProduct.getItemQuantity());
    }

    @Test
    void testUpdateProductWithNullValues() {
        Product product = new Product();
        product.setItemName("Product 1");
        product.setItemQuantity(100);
        product = productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setItemName(null);
        updatedProduct.setItemQuantity(0);
        Product result = productRepository.update(product.getItemId(), updatedProduct);

        assertNotNull(result);
        Product foundProduct = productRepository.findById(product.getItemId());
        assertNotNull(foundProduct);
        assertNull(foundProduct.getItemName());
        assertEquals(0, foundProduct.getItemQuantity());
    }

    @Test
    void testDeleteNonExistentProduct() {
        String nonExistentitemId = UUID.randomUUID().toString();
        productRepository.delete(nonExistentitemId);

        Product foundProduct = productRepository.findById(nonExistentitemId);
        assertNull(foundProduct);
    }

    @Test
    void testUpdateProductWithSameValues() {
        Product product = new Product();
        product.setItemName("Product 1");
        product.setItemQuantity(100);
        product = productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setItemName("Product 1");
        updatedProduct.setItemQuantity(100);
        Product result = productRepository.update(product.getItemId(), updatedProduct);

        assertNotNull(result);
        Product foundProduct = productRepository.findById(product.getItemId());
        assertNotNull(foundProduct);
        assertEquals("Product 1", foundProduct.getItemName());
        assertEquals(100, foundProduct.getItemQuantity());
    }

    @Test
    void testCreateMultipleProductsAndDeleteOne() {
        Product product1 = new Product();
        product1.setItemName("Product 1");
        product1.setItemQuantity(100);
        product1 = productRepository.create(product1);

        Product product2 = new Product();
        product2.setItemName("Product 2");
        product2.setItemQuantity(200);
        product2 = productRepository.create(product2);

        productRepository.delete(product1.getItemId());

        Product foundProduct1 = productRepository.findById(product1.getItemId());
        assertNull(foundProduct1);

        Product foundProduct2 = productRepository.findById(product2.getItemId());
        assertNotNull(foundProduct2);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());
        assertEquals(product2.getItemId(), products.next().getItemId());
        assertFalse(products.hasNext());
    }

    @Test
    void testUpdateWhenProductListIsEmpty() {
        Product updatedProduct = new Product();
        updatedProduct.setItemName("Updated Product");
        updatedProduct.setItemQuantity(50);

        Product result = productRepository.update(UUID.randomUUID().toString(), updatedProduct);
        assertNull(result);
    }
}