package com.github.duychuongvn.transactionmanagement.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by huynhduychuong on Oct 15, 2016.
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable{

    private static final long serialVersionUID = -2838472752801778875L;
    @Id
    private String id = UUID.randomUUID().toString();
    
    private String username;
    private BigDecimal amount = BigDecimal.ZERO;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", amount=" + amount + "]";
    }
    
    
    

}
