package com.beck.springbootmvcjpa.service;

import com.beck.springbootmvcjpa.entity.Product;
import com.beck.springbootmvcjpa.entity.User;
import com.beck.springbootmvcjpa.respository.BasketOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasketOrderService {

    private final BasketOrderRepository basketOrderRepository;

    @Transactional
    public boolean addProduct(long userId, long productId){
        return basketOrderRepository.addProduct(userId,productId);
    }

    @Transactional
    public void removeProduct(long userId, long productId){
        basketOrderRepository.removeProduct(userId, productId);

    }

    public List<Product> getProductsByUserId(long userId){
        return basketOrderRepository.getProductsByUserId(userId);
    }

    @Transactional
    public boolean doOrder(User user){
        return basketOrderRepository.doOrder(user);
    }


}
