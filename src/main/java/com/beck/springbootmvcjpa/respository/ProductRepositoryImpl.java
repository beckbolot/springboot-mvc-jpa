package com.beck.springbootmvcjpa.respository;

import com.beck.springbootmvcjpa.entity.FinalOrder;
import com.beck.springbootmvcjpa.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository{

    private final EntityManager entityManager;


    @Override
    public void createProduct(Product product) {
        entityManager.persist(product);
        log.info("Product was created: " + product);

    }

    @Override
    public List<Product> getAllProducts() {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public void removeProduct(long id) {
        Product product = entityManager.find(Product.class, id);

        Query query = entityManager.
                createNativeQuery("delete from final_order_products where products_id =:id", FinalOrder.class);
        query.setParameter("id", product.getId());
        query.executeUpdate();

        entityManager.remove(product);
        log.info("Product was deleted: " + product);

    }

    @Override
    public Product getProductById(long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> findProducts(String string) {
        String sql = "FROM Product WHERE name LIKE '"+ string + "%'";
        List<Product> products = entityManager.createQuery(sql, Product.class).getResultList();

        if (products != null) {
            for (Product currentProduct : products) {
                log.info("Product was found: " + currentProduct);
            }
        }

        return products;
    }

    @Override
    public void updateProduct(Product product) {
        entityManager.detach(product);
        entityManager.merge(product);

        log.info("Product was updated: " + product);

    }
}
