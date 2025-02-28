package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Item;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

public interface ItemControllerInterface<T extends Item> {
    String createPage(Model model);
    String createPost(@ModelAttribute T item, Model model);
    String listPage(Model model);
    String editPage(@PathVariable String itemId, Model model);
    String editPost(@ModelAttribute T item, Model model);
    String deletePage(@PathVariable String itemId, Model model);
    String deletePost(@ModelAttribute T item);
}