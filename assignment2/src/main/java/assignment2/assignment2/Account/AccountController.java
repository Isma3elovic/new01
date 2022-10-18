package assignment2.assignment2.Account;

import assignment2.assignment2.Dto;
import assignment2.assignment2.Request;
import assignment2.assignment2.Response;
import assignment2.assignment2.exception.ApiRequestException;
import assignment2.assignment2.statement.StatementEntity;
import assignment2.assignment2.statement.StatmentRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeString.valueOf;


@RestController
@RequestMapping("/api/v1/statment")
public class AccountController {
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    StatmentRepo statmentRepo;
    Dto dto = new Dto();

    @PostMapping("/all")
    public ResponseEntity<Object> findById(@RequestBody Request req) {
        Response res = new Response();
        BeanUtils.copyProperties(req, res);
        System.out.println(res.getId());
        System.out.println("two "+ res.getAmountTo());
        // return ResponseEntity.ok(res);

        //  Optional<AccountEntity> accountEntity = accountRepo.findById(res.getId());

        //System.out.println(res.getFrom());

        Optional<AccountEntity> accountEntity = accountRepo.findAccountEntityById(res.getId());
        if (accountEntity.isPresent()) {
            System.out.println();

            if (res.getFrom() != null && res.getTo() != null) {
                //     System.out.println("hello");
                try {
                    Date fromTime = new SimpleDateFormat("dd.MM.yyyy").parse(res.getFrom().replaceAll("[-+^*!@#$%^&]*", "").strip());

                    System.out.println(fromTime);
                    Date toTime = new SimpleDateFormat("dd.MM.yyyy").parse(res.getTo().replaceAll("[-+^!@#$%^&]*", "").strip());
                    System.out.println(toTime);
                    if (fromTime.before(toTime)) {
                        List<StatementEntity> statementEntities = accountRepo.findAccountEntityById(res.getId()).get().getStatementEntity();

                        List<StatementEntity> statementEntitiesFilterd = statementEntities.stream().filter(new Predicate<StatementEntity>() {
                            @Override
                            public boolean test(StatementEntity statementEntity) {
                                try {
                                    Date statementEntityDate = new SimpleDateFormat("dd.MM.yyyy").parse(statementEntity.getDate());
//
//                                    return statementEntityDate.after(fromTime) && statementEntityDate.before(toTime);
//                                    return Date.from(LocalDateTime.of(2021, Month.DECEMBER,3,6,30,40,50000).minusMonths(3).toInstant(ZoneOffset.UTC),
//                                            Date.from(LocalDateTime.of(2021,Month.SEPTEMBER,1,12,6,3,4),res.getId());

                                    if ((statementEntityDate.equals(fromTime) || statementEntityDate.equals(toTime)))
                                        return statementEntityDate.equals(fromTime) || statementEntityDate.equals(toTime);

                                    else if (statementEntityDate.after(fromTime) && statementEntityDate.before(toTime))
                                        return (statementEntityDate.after(fromTime) && statementEntityDate.before(toTime));

                                    else
                                        return false;

                                } catch (Exception e) {
                                    throw new ApiRequestException("invalid date");
                                }
                            }
                        }).collect(Collectors.toList());


                        // System.out.println(String.valueOf(statementEntitiesFilterd.get(0).getId()));
                        if (res.getAmountFrom() != null && res.getAmountTo() != null) {
                            double From = Double.parseDouble(res.getAmountFrom().replaceAll("[-*+^!@#$%^&]*", "").strip());
                            System.out.println("one "+ From);
                            double To = Double.parseDouble(res.getAmountTo().replaceAll("[-+^!*@#$%^&]*", "").strip());
                            System.out.println("two "+ To);


                            if (From < To) {
                                try {
                                    statementEntitiesFilterd = statementEntities.stream().filter(new Predicate<StatementEntity>() {
                                        @Override
                                        public boolean test(StatementEntity statementEntity) {
                                            double statementEntityAmount = Double.parseDouble(statementEntity.getAmount());
                                            System.out.println("hello");
                                            if (statementEntityAmount >= From && statementEntityAmount <= To)
                                                return (statementEntityAmount >= From && statementEntityAmount <= To);

                                            return false;
                                        }

                                    }).collect(Collectors.toList());

                                } catch (Exception e) {
                                    throw new ApiRequestException("error amount is not valid");
                                }

                            } else if(From >= To) {
//
//
                                        throw new ApiRequestException("error amount is not valid");

                            }

                        }


                        //printing statements filterd by date
                        return ResponseHandler.generateResponse(accountEntity, statementEntitiesFilterd, HttpStatus.OK, false, "Success", null);


                        //  return new ResponseEntity<>(statementEntitiesFilterd, HttpStatus.OK);

                    } else {
                        throw new ApiRequestException("check entery ");
                    }

                } catch (Exception exception) {

                    throw new ApiRequestException("enteries are not valid");
                }
            } else {


                //printing every statement if there is no date or amount
                List<StatementEntity> statementEntities = accountRepo.findAccountEntityById(res.getId()).get().getStatementEntity();


                //List<StatementEntity> statementEntitiesInThreeMonth = statmentRepo.findStatementEntitiesByCreatedAtBetweenAndAccountEntity_Id(Date.from(LocalDateTime.now().minusMonths(3).toInstant(ZoneOffset.UTC)), Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), id);
                //return new ResponseEntity<>(statementEntitiesInThreeMonth, HttpStatus.OK);

                return ResponseHandler.generateResponse(accountEntity, statementEntities, HttpStatus.OK, false, "Success", null);

                //    return new ResponseEntity<>(statementEntities, HttpStatus.OK);
            }
        } else {
            throw new ApiRequestException("account not found");

        }
    }
}


