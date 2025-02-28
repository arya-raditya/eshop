package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController controller;

    private MockMvc mockMvc;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testHomePage() throws Exception {
        mockMvc.perform(get("/product/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product product = new Product();
        product.setItemId("123");
        product.setItemName("Test Product");
        product.setItemQuantity(10);

        mockMvc.perform(post("/product/create")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).create(product);
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setItemId("1");
        product1.setItemName("Product 1");
        product1.setItemQuantity(10);
        productList.add(product1);

        when(service.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attribute("products", productList));
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        product.setItemId("123");
        product.setItemName("Test Product");
        product.setItemQuantity(10);

        when(service.findById("123")).thenReturn(product);

        mockMvc.perform(get("/product/edit/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attribute("product", product));
    }

    @Test
    void testEditProductPage_ProductNotFound() throws Exception {
        when(service.findById("nonexistent")).thenReturn(null);

        mockMvc.perform(get("/product/edit/nonexistent"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testEditProductPost() throws Exception {
        Product product = new Product();
        product.setItemId("123");
        product.setItemName("Updated Product");
        product.setItemQuantity(20);

        mockMvc.perform(post("/product/edit")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).update("123", product);
    }

    @Test
    void testDeleteProductPage() throws Exception {
         Product product = new Product();
        product.setItemId("123");
        product.setItemName("Test Product");
        product.setItemQuantity(10);

        when(service.findById("123")).thenReturn(product);

        mockMvc.perform(get("/product/delete/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("deleteProduct"))
                .andExpect(model().attribute("product", product));
    }

    @Test
    void testDeleteProductPage_ProductNotFound() throws Exception {
        when(service.findById("nonexistent")).thenReturn(null);

        mockMvc.perform(get("/product/delete/nonexistent"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testDeleteProductPost() throws Exception {
        Product product = new Product();
        product.setItemId("123");

        mockMvc.perform(post("/product/delete")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).delete("123");
    }
}