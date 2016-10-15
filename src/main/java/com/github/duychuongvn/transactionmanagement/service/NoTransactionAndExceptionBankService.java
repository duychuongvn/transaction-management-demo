package com.github.duychuongvn.transactionmanagement.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.github.duychuongvn.transactionmanagement.entity.User;
import com.github.duychuongvn.transactionmanagement.exception.UserNotFoundException;

/**
 * Created by huynhduychuong on Oct 15, 2016.
 *
 */
@Service(value = "noTransactionAndExceptionBankService")
public class NoTransactionAndExceptionBankService extends AbstractBankService{

    public void transfer(String fromUserId, String toUserId, BigDecimal amount) {
        try {
            User fromUser = userManager.getUser(fromUserId);
            User toUser = userManager.getUser(toUserId);
            fromUser.setAmount(fromUser.getAmount().subtract(amount));
            userManager.update(fromUser);
            if(new BigDecimal(30).compareTo(amount)>=0) {
                throw new IllegalArgumentException("Amount is not valid");
            }
            toUser.setAmount(toUser.getAmount().add(amount));
            userManager.update(toUser);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

    }

   
}
