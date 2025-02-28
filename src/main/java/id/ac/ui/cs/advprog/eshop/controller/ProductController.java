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
public class ProductController implements ItemControllerInterface<Product> {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String listPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{itemId}")
    public String editPage(@PathVariable String itemId, Model model) {
        Product product = service.findById(itemId);
        if (product == null) {
            return "redirect:/product/list";
        }
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute Product product, Model model) {
        service.update(product.getItemId(), product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{itemId}")
    public String deletePage(@PathVariable String itemId, Model model) {
        Product product = service.findById(itemId);
        if (product == null) {
            return "redirect:/product/list";
        }
        model.addAttribute("product", product);
        return "deleteProduct";
    }

    @PostMapping("/delete")
    public String deletePost(@ModelAttribute Product product) {
        service.delete(product.getItemId());
        return "redirect:/product/list";
    }
}