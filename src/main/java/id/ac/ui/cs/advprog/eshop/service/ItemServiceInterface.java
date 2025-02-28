package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Item;

import java.util.List;

public interface ItemServiceInterface<T extends Item> {
    T create(T item);
    List<T> findAll();
    T findById(String itemId);
    T update(String itemId, T updatedItem);
    void delete(String itemId);
}