package assignment2.assignment2.Account;

import assignment2.assignment2.statement.StatementEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "account")
public class AccountEntity {
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

    public AccountEntity(String account_type, String account_number, long account_id, String amount, Date date) {
        this.account_type = account_type;
        this.account_number = account_number;
        this.account_id = account_id;
        this.amount = amount;
    }

    @Column(
            name = "account_type",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String account_type;
    @Column(
            name = "account_number",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String account_number;

    @Column(
            name = "account_id",
            nullable = false

    )
    @JsonIgnore
    private long account_id;

    @Column(
            name = "amount",
            nullable = false

    )
    @JsonIgnore
    private String amount;
    @Column(
            name = "date",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @JsonIgnore
    private String date;


    @OneToMany(
            mappedBy = "accountEntity",
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    @JsonIgnore
    private List<StatementEntity> statementEntity = new ArrayList<>();

    public AccountEntity() {

    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }
    public List<StatementEntity> getStatementEntity() {
        return statementEntity;
    }

    public void setStatementEntity(List<StatementEntity> statementEntity) {
        this.statementEntity = statementEntity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }



}