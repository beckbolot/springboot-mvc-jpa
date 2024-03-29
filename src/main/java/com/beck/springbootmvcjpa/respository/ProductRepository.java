package com.beck.springbootmvcjpa.respository;

import com.beck.springbootmvcjpa.entity.Product;

import java.util.List;

public interface ProductRepository {

    void createProduct(Product product);

    List<Product> getAllProducts();

    void removeProduct(long id);

    Product getProductById(long id);

    List<Product> findProducts(String string);

    void updateProduct(Product product);
}
