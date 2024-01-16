package com.beck.springbootmvcjpa.respository;

import com.beck.springbootmvcjpa.entity.Product;
import com.beck.springbootmvcjpa.entity.User;

import java.util.List;

public interface BasketOrderRepository {

    boolean addProduct(long userId, long productId);

    void removeProduct(long userId, long productId);

    List<Product> getProductsByUserId(long userId);

    boolean doOrder(User user);


}
