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
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId());

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();

        assertEquals(createdProduct.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductName("Product 1");
        product1.setProductQuantity(100);
        product1 = productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);
        product2 = productRepository.create(product2);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = products.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(products.hasNext());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        product = productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Product 1 Updated");
        updatedProduct.setProductQuantity(200);
        Product result = productRepository.update(product.getProductId(), updatedProduct);

        assertNotNull(result);
        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct);
        assertEquals("Product 1 Updated", foundProduct.getProductName());
        assertEquals(200, foundProduct.getProductQuantity());
    }

    @Test
    void testUpdateProduct_NotFound() {
        // Create a product first
        Product product = new Product();
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Product 1 Updated");
        updatedProduct.setProductQuantity(200);
        Product result = productRepository.update(UUID.randomUUID().toString(), updatedProduct);

        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        product = productRepository.create(product);

        productRepository.delete(product.getProductId());

        Product foundProduct = productRepository.findById(product.getProductId());
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
        product.setProductName(null);
        product.setProductQuantity(0);
        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId());
        assertNull(createdProduct.getProductName());
        assertEquals(0, createdProduct.getProductQuantity());
    }

    @Test
    void testUpdateProductWithNullValues() {
        Product product = new Product();
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        product = productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName(null);
        updatedProduct.setProductQuantity(0);
        Product result = productRepository.update(product.getProductId(), updatedProduct);

        assertNotNull(result);
        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct);
        assertNull(foundProduct.getProductName());
        assertEquals(0, foundProduct.getProductQuantity());
    }

    @Test
    void testDeleteNonExistentProduct() {
        String nonExistentProductId = UUID.randomUUID().toString();
        productRepository.delete(nonExistentProductId);

        Product foundProduct = productRepository.findById(nonExistentProductId);
        assertNull(foundProduct);
    }

    @Test
    void testUpdateProductWithSameValues() {
        Product product = new Product();
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        product = productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Product 1");
        updatedProduct.setProductQuantity(100);
        Product result = productRepository.update(product.getProductId(), updatedProduct);

        assertNotNull(result);
        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct);
        assertEquals("Product 1", foundProduct.getProductName());
        assertEquals(100, foundProduct.getProductQuantity());
    }

    @Test
    void testCreateMultipleProductsAndDeleteOne() {
        Product product1 = new Product();
        product1.setProductName("Product 1");
        product1.setProductQuantity(100);
        product1 = productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);
        product2 = productRepository.create(product2);

        productRepository.delete(product1.getProductId());

        Product foundProduct1 = productRepository.findById(product1.getProductId());
        assertNull(foundProduct1);

        Product foundProduct2 = productRepository.findById(product2.getProductId());
        assertNotNull(foundProduct2);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());
        assertEquals(product2.getProductId(), products.next().getProductId());
        assertFalse(products.hasNext());
    }

    @Test
    void testUpdateWhenProductListIsEmpty() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(50);

        Product result = productRepository.update(UUID.randomUUID().toString(), updatedProduct);
        assertNull(result);
    }
}