package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        if (product == null) {
            return "redirect:/product/list"; 
        }
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product, Model model) {
        service.update(product.getProductId(), product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProductPage(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        if (product == null) {
            return "redirect:/product/list"; // Corrected redirection
        }
        model.addAttribute("product", product);
        return "deleteProduct";
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute Product product) {
        service.delete(product.getProductId());
        return "redirect:/product/list";
    }
}