package assignment2.assignment2.statement;

import assignment2.assignment2.Account.AccountEntity;
import assignment2.assignment2.statement.StatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface StatmentRepo extends JpaRepository<StatementEntity,Long> {
//    List<StatementEntity> findStatementEntitiesByCreatedAtBetween(String start, String end);
//    List<StatementEntity> findStatementEntitiesByAmountBetween(String begin, String finish);
Optional<AccountEntity> findStatementEntitiesById(long id);
}
