package com.github.duychuongvn.transactionmanagement.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.duychuongvn.transactionmanagement.entity.User;
import com.github.duychuongvn.transactionmanagement.exception.UserNotFoundException;

/**
 * Created by huynhduychuong on Oct 15, 2016.
 *
 */
@Service(value = "noTransactionConcurrentBankService")
public class NoTransactionConcurrentBankService  extends AbstractBankService {

    @Autowired
    private UserManager userManager;

    public void transfer(String fromUserId, String toUserId, BigDecimal amount) {
        try {
            User fromUser = userManager.getUser(fromUserId);
            User toUser = userManager.getUser(toUserId);
            fromUser.setAmount(fromUser.getAmount().subtract(amount));
            userManager.update(fromUser);
            
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
               // no thing 
            }
            toUser.setAmount(toUser.getAmount().add(amount));
            userManager.update(toUser);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

    }

}
