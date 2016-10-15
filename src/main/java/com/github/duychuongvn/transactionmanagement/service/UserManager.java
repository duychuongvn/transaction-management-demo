package com.github.duychuongvn.transactionmanagement.service;

import java.math.BigDecimal;
import java.util.List;

import com.github.duychuongvn.transactionmanagement.entity.User;
import com.github.duychuongvn.transactionmanagement.exception.UserNotFoundException;

/**
 * Created by huynhduychuong on Oct 15, 2016.
 *
 */
public interface UserManager {

   User createUser(String username, BigDecimal amount);
   User getUser(String userId) throws UserNotFoundException;
   User update(User user);
   void removeAllUser();
   List<User> findAll();
}
