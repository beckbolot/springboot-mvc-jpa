package com.beck.springbootmvcjpa.service;

import com.beck.springbootmvcjpa.entity.Product;
import com.beck.springbootmvcjpa.respository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void createProduct(Product product){
        productRepository.createProduct(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }

    @Transactional
    public void removeProduct(long id){
        productRepository.removeProduct(id);
    }

    public Product getProductById(long id){
        return productRepository.getProductById(id);
    }

    public List<Product> findProducts(String string) {
        return productRepository.findProducts(string);
    }

    @Transactional
    public void updateProduct(Product product){
        productRepository.updateProduct(product);
    }

    public void orderById(List<Product> productList) {
        productList.sort((Product p1,Product p2)-> (int) (p1.getId()- p2.getId()));

    }

    public void orderByName(List<Product> productList) {
        productList.sort((Product p1,Product p2)->{
            if(p1.getName().compareTo(p2.getName()) !=0){
                return p1.getName().compareTo(p2.getName());
            }else {
                return (int) (p1.getId()- p2.getId());
            }
        });
    }

    public void orderByPrice(List<Product> productList) {
        productList.sort((Product p1,Product p2)->{
            if ((p1.getPrice() - p2.getPrice()) !=0){
                return p1.getPrice() - p2.getPrice();
            }else {
                return (int) (p1.getId() - p2.getId());
            }
        });

    }

    public void orderByType(List<Product> productList) {
        productList.sort((Product p1,Product p2)->{
            if (p1.getType().getName().compareTo(p2.getType().getName()) !=0){
                return p1.getType().getName().compareTo(p2.getType().getName());
            }else {
                return (int) (p1.getId() - p2.getId());
            }
        });
    }












}
