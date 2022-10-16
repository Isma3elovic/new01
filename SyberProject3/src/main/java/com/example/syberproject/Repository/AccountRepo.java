package com.example.syberproject.Repository;

import com.example.syberproject.Entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepo extends CrudRepository<AccountEntity,Long> {


//    Optional<AccountEntity> findAccountEntityById(long id);

   Optional<AccountEntity>  findAccountEntityById(long id);
   Optional<AccountEntity> findAccountEntityByAmountBetween(String from,String to);


}
