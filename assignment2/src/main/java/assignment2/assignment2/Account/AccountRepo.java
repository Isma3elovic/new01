package assignment2.assignment2.Account;

import assignment2.assignment2.Account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepo extends JpaRepository<AccountEntity,Long> {


    Optional<AccountEntity> findAccountEntityById(long id);
}
