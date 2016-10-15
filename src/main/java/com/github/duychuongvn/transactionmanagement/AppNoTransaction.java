package com.github.duychuongvn.transactionmanagement;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.duychuongvn.transactionmanagement.entity.User;
import com.github.duychuongvn.transactionmanagement.service.BankService;
import com.github.duychuongvn.transactionmanagement.service.UserManager;

/**
 * Hello world!
 *
 */
public class AppNoTransaction {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-data.xml");
        UserManager userManager = context.getBean(UserManager.class);
        final BankService noTransactionAndExceptionBankService = (BankService) context.getBean("noTransactionAndExceptionBankService");
        final BankService noTransactionConcurrentBankService = (BankService) context.getBean("noTransactionConcurrentBankService");
        transferAToBAndExceptionOccur(userManager, noTransactionAndExceptionBankService);
        transferAToBAndConcurrentOccur(userManager, noTransactionConcurrentBankService);

    }

    private static void transferAToBAndExceptionOccur(final UserManager userManager, final BankService bankService) {
        try {
            userManager.removeAllUser();
            Thread.sleep(1000);
            final User userA = userManager.createUser("User A", new BigDecimal("100"));
            final User userB = userManager.createUser("User B", new BigDecimal("5"));

            System.out.println("Transfer 20 from Use A to User B and get exception");

            bankService.transfer(userA.getId(), userB.getId(), new BigDecimal(20));
        } catch (Exception exception) {
            System.out.println("Get exception when transfer user A to user B");
        } finally {
            System.out.println("List user after transfered: ");
            System.out.println(userManager.findAll());
        }

    }

    private static void transferAToBAndConcurrentOccur(final UserManager userManager, final BankService bankService) {
        try {
            userManager.removeAllUser();
            final User userA = userManager.createUser("User A", new BigDecimal("100"));
            final User userB = userManager.createUser("User B", new BigDecimal("5"));

            System.out.println("Transfer 20 from Use A to User B and transfer 10%");

            Thread transferAmountFromAToBThread = new Thread(new Runnable() {
                public void run() {
                    bankService.transfer(userA.getId(), userB.getId(), new BigDecimal(20));
                }
            });
            Thread transfer10PercentFromAToBThread = new Thread(new Runnable() {
                public void run() {
                    bankService.transfer10Percent(userA.getId(), userB.getId());
                }
            });

            transferAmountFromAToBThread.start();

            Thread.sleep(1000);
            transfer10PercentFromAToBThread.start();

        } catch (Exception exception) {
            System.out.println("Get exception when transfer user A to user B");
        } finally {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
            System.out.println("List user after transfered: ");
            System.out.println(userManager.findAll());
        }
    }
}
