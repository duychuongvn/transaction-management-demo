package com.github.duychuongvn.transactionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.duychuongvn.transactionmanagement.entity.User;

/**
 * Created by huynhduychuong on Oct 15, 2016.
 *
 */
public interface UserRepository extends JpaRepository<User, String>{

}
