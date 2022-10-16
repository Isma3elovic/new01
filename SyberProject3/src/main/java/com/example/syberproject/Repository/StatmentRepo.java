package com.example.syberproject.Repository;

import com.example.syberproject.Entity.AccountEntity;
import com.example.syberproject.Entity.StatementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatmentRepo extends CrudRepository<StatementEntity,Long> {
    List<StatementEntity>findById(long id);
    List<AccountEntity> findAccountEntityByAmountBetween(String amountFrom, String amountTo);

}
