package com.github.duychuongvn.transactionmanagement.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.duychuongvn.transactionmanagement.entity.User;
import com.github.duychuongvn.transactionmanagement.exception.UserNotFoundException;

/**
 * Created by huynhduychuong on Oct 15, 2016.
 *
 */
public abstract class AbstractBankService implements BankService{

    @Autowired
    protected UserManager userManager;
    public void transfer10Percent(String fromUserId, String toUserId) {
        try {
            User fromUser = userManager.getUser(fromUserId);
            User toUser = userManager.getUser(toUserId);
            BigDecimal transferedAmount = fromUser.getAmount().multiply(new BigDecimal(0.1));
            fromUser.setAmount(fromUser.getAmount().subtract(transferedAmount));
            userManager.update(fromUser);

            toUser.setAmount(toUser.getAmount().add(transferedAmount));
            userManager.update(toUser);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

    }

}
