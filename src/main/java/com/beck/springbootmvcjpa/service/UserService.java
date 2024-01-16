package com.beck.springbootmvcjpa.service;

import com.beck.springbootmvcjpa.entity.User;
import com.beck.springbootmvcjpa.respository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public boolean registration(User user){
        return userRepository.createUser(user);
    }

    public User authorization(User user){
        return userRepository.getUser(user.getEmail(), user.getPassword());
    }

    @Transactional
    public void logout(HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        session.invalidate();
        userRepository.logout(currentUser);
    }







}
