package com.ecommerce.user_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.user_service.model.User;
import com.ecommerce.user_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> allUsers(){
        List<User>  users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

}
