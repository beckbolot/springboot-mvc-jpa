package com.beck.springbootmvcjpa.respository;

import com.beck.springbootmvcjpa.entity.BasketOrder;
import com.beck.springbootmvcjpa.entity.FinalOrder;
import com.beck.springbootmvcjpa.entity.Product;
import com.beck.springbootmvcjpa.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BasketOrderRepositoryImpl implements BasketOrderRepository {


    private final EntityManager entityManager;

    @Override
    public boolean addProduct(long userId, long productId) {

        User user = entityManager.find(User.class, userId);
        Product product = entityManager.find(Product.class, productId);

        TypedQuery<BasketOrder> query = entityManager
                .createQuery("from BasketOrder where user =:user and product=:product", BasketOrder.class);
        query.setParameter("user", user);
        query.setParameter("product", product);

        if (query.getResultList().isEmpty()) {
            entityManager.persist(new BasketOrder(user, product));
            log.info("BasketOrder was added: userId(" + userId + "), product_id(" + productId + ")");
            return true;
        }
        return false;

    }

    @Override
    public void removeProduct(long userId, long productId) {
        User user = entityManager.find(User.class, userId);
        Product product = entityManager.find(Product.class, productId);

        TypedQuery<BasketOrder> query = entityManager
                .createQuery("from BasketOrder where user=:user and product=:product", BasketOrder.class);
        query.setParameter("user", user);
        query.setParameter("product", product);
        List<BasketOrder> resultList = query.getResultList();
        entityManager.remove(resultList.get(0));

    }

    @Override
    public List<Product> getProductsByUserId(long userId) {
        User user =entityManager.find(User.class, userId);
        TypedQuery<BasketOrder> query = entityManager.createQuery("from BasketOrder where user =:user", BasketOrder.class);
        query.setParameter("user", user);

        List<BasketOrder> resultList = query.getResultList();
        List<Product> products = new ArrayList<>();

        for (BasketOrder currentObject : resultList) {
            products.add(currentObject.getProduct());
        }

        return products;
    }

    @Override
    public boolean doOrder(User user) {
        TypedQuery<FinalOrder> checkOrder = entityManager
                .createQuery("from FinalOrder where user =:user", FinalOrder.class).setParameter("user", user);

        if (!checkOrder.getResultList().isEmpty()) {
            return false;
        }

        TypedQuery<BasketOrder> query = entityManager
                .createQuery("from BasketOrder where user=:user", BasketOrder.class).setParameter("user", user);
        List<BasketOrder> resultList = query.getResultList();
        Date date = new Date();

        List<Product> products = new ArrayList<>();

        for (BasketOrder currentObject : resultList) {
            products.add(currentObject.getProduct());
        }

        entityManager.persist(new FinalOrder(date, user, products));
        entityManager.createQuery("delete from BasketOrder where user=:user", BasketOrder.class).setParameter("user", user).executeUpdate();

        return true;
    }
}
