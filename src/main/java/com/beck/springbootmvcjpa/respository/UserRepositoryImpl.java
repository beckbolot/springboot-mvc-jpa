package com.beck.springbootmvcjpa.respository;

import com.beck.springbootmvcjpa.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Log4j2
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Override
    public boolean createUser(User user) {

        jakarta.persistence.Query query = entityManager.createQuery("select count(email) from User where email =:email");
        query.setParameter("email", user.getEmail());

        long count = (long) query.getSingleResult();

        if (count == 0) {
            entityManager.persist(user);
            log.info("User was created " + user);
            return true;
        }
        log.info("User is existed " + user);
        return false;


    }

    @Override
    public User getUser(String email, String password) {
        TypedQuery<User> query = entityManager.createQuery("from User where email =:email and password =:password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        User user = null;

        try {
            user = query.getSingleResult();
            log.info("User by email and password was found " + user);
        } catch (NoResultException e) {
            log.info("User was not found");
        }
        return user;
    }

    @Override
    @Modifying
    @org.springframework.data.jpa.repository.Query("delete from BasketOrder where user =:user")
    public void logout(@Param("user") User user) {
//        MutationQuery query = sessionFactory.getCurrentSession().createQuery("delete from BasketOrder where user =: user", BasketOrder.class);
//        query.setParameter("user", user);
//        query.executeUpdate();
        log.info("User was logout: " + user);

    }
}
