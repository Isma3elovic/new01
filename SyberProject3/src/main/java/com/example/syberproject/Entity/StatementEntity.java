package com.example.syberproject.Entity;

import com.example.syberproject.Entity.AccountEntity;

import javax.persistence.*;

@Entity
@Table(name = "statement")
public class StatementEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private long id;


    @Column(
            name = "date",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String createdAt;


    @Column(
            name = "amount",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String amount;


    @ManyToOne
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "account_statment_fk"
            )
    )
    private AccountEntity accountEntity;



    public StatementEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

//    public AccountEntity getAccountEntity() {
//        return accountEntity;
//    }
//
//    public void setAccountEntity(AccountEntity accountEntity) {
//        this.accountEntity = accountEntity;
//    }
}
