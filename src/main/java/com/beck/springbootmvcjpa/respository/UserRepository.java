package com.beck.springbootmvcjpa.respository;

import com.beck.springbootmvcjpa.entity.User;
import org.springframework.data.jpa.repository.Modifying;

public interface UserRepository {

    boolean createUser(User user);

    User getUser(String email, String password);
    @Modifying
    void logout(User user);




}
