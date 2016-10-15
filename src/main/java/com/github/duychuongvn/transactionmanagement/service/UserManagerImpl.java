package com.github.duychuongvn.transactionmanagement.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.duychuongvn.transactionmanagement.entity.User;
import com.github.duychuongvn.transactionmanagement.exception.UserNotFoundException;
import com.github.duychuongvn.transactionmanagement.repository.UserRepository;

/**
 * Created by huynhduychuong on Oct 15, 2016.
 *
 */
@Service
public class UserManagerImpl implements UserManager{

    @Autowired
    private UserRepository userRepository;
    
    public User createUser(String username, BigDecimal amount) {
        User user = new User();
        user.setUsername(username);
        user.setAmount(amount);
        return userRepository.save(user);
    }

   
    public User getUser(String userId) throws UserNotFoundException {
        User user = userRepository.findOne(userId);
        if(user == null) {
            throw new UserNotFoundException(userId);
        }
        return user;
        
    }
    
    public void removeAllUser() {
        userRepository.deleteAll();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User update(User user) {
        return userRepository.save(user);
    }
}
