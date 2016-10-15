package com.github.duychuongvn.transactionmanagement.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.duychuongvn.transactionmanagement.entity.User;
import com.github.duychuongvn.transactionmanagement.exception.UserNotFoundException;

/**
 * Created by huynhduychuong on Oct 15, 2016.
 *
 */
@Service(value = "transactionConcurrentBankService")
public class TransactionConcurrentBankService extends AbstractBankService {

    @Autowired
    private UserManager userManager;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void transfer10Percent(String fromUserId, String toUserId) {
        super.transfer10Percent(fromUserId, toUserId);
    }

}
