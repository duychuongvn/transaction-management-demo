package com.github.duychuongvn.transactionmanagement.exception;

/**
 * Created by huynhduychuong on Oct 15, 2016.
 *
 */
public class UserNotFoundException extends Exception {

    /**
     * @param userId
     */
    public UserNotFoundException(String userId) {
       super("Cannot find user by id: " + userId);
    }

}
