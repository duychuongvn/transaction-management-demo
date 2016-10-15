package com.github.duychuongvn.transactionmanagement.service;

import java.math.BigDecimal;

public interface BankService {
    void transfer(String fromUserId, String toUserId, BigDecimal amount);
    void transfer10Percent(String fromUserId, String toUserId);
}
