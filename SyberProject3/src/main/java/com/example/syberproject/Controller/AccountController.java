package com.example.syberproject.Controller;

import com.example.syberproject.Entity.AccountEntity;
import com.example.syberproject.Entity.StatementEntity;
import com.example.syberproject.Repository.AccountRepo;
import com.example.syberproject.ResponseHandler;
import com.example.syberproject.exception.ApiRequestException;
import com.example.syberproject.Repository.StatmentRepo;
import com.example.syberproject.modelRequest.Request;
import com.example.syberproject.modelResponse.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeString.valueOf;

@Controller
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    StatmentRepo statmentRepo;
    @Autowired
    AccountRepo accountRepo;


    @PostMapping("/all")
    public ResponseEntity<Object> findById(@RequestBody Request request) {

        Response res = new Response();
        BeanUtils.copyProperties(request, res);

        Optional<AccountEntity> accountEntity = accountRepo.findAccountEntityById(res.getId());
        if (accountEntity.isPresent()) {
            if (res.getFrom() != null && res.getTo() != null) {
                double From = Double.valueOf(res.getFrom());
                System.out.println(From);
                double To = Double.parseDouble(valueOf(res.getTo()));
                System.out.println(To);
                if (From < To || From > To) {
                    List<StatementEntity> statementEntities = accountRepo.findAccountEntityById(res.getId()).get().getStatementEntity();

                    List<StatementEntity> statementEntitiesFilterd = statementEntities.stream().filter(new Predicate<StatementEntity>() {
                        @Override
                        public boolean test(StatementEntity statementEntity) {

                            double statementEntityAmount = Double.valueOf(statementEntity.getAmount());
//                                    return statementEntityDate.after(fromTime) && statementEntityDate.before(toTime);

                            if ((statementEntityAmount == From || statementEntityAmount == To))
                                return ((statementEntityAmount == From || statementEntityAmount == To));

                            else if (statementEntityAmount > From && statementEntityAmount < To)
                                return (statementEntityAmount > From && statementEntityAmount < To);
//                                ( (statementEntityDate.after(fromTime) && statementEntityDate.before(toTime)));
                            else
                                return false;


                        }
                    }).collect(Collectors.toList());

                    return ResponseHandler.generateResponse(accountEntity, statementEntitiesFilterd, HttpStatus.OK, false, "Success", null);
                }

            }

        } else {
            List<StatementEntity> statementEntities = accountRepo.findAccountEntityById(res.getId()).get().getStatementEntity();

            //List<StatementEntity> statementEntitiesInThreeMonth = statmentRepo.findStatementEntitiesByCreatedAtBetweenAndAccountEntity_Id(Date.from(LocalDateTime.now().minusMonths(3).toInstant(ZoneOffset.UTC)), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), id);
            //return new ResponseEntity<>(statementEntitiesInThreeMonth, HttpStatus.OK);

            return ResponseHandler.generateResponse(accountEntity, statementEntities, HttpStatus.OK, false, "Success", null);

            //    return new ResponseEntity<>(statementEntities, HttpStatus.OK);

        }
        return null;
    }
}
