package com.example.syberproject.Controller;


import com.example.syberproject.DTO.Dto;
import com.example.syberproject.Entity.AccountEntity;
import com.example.syberproject.Repository.AccountRepo;
import com.example.syberproject.Service.Service;
import com.example.syberproject.exception.ApiRequestException;
import com.example.syberproject.Entity.StatementEntity;
import com.example.syberproject.Repository.StatmentRepo;
import com.example.syberproject.modelRequest.Request;
import com.example.syberproject.modelResponse.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.apache.tomcat.util.http.parser.Host.parse;

@RestController
@RequestMapping("/api/v1/statment")
public class StatmentController {

    //        @GetMapping("/amount")
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    StatmentRepo statmentRepo;
    @Autowired
    Service service;


    @PostMapping("/search")
    public Response search(@RequestBody Request request) {
        Response returnValue = new Response();


        Dto userDto = new Dto();
        BeanUtils.copyProperties(request, userDto);
        Dto createdUser = service.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);
        return returnValue;


    }


    @GetMapping("/all")
    public ResponseEntity<List<StatementEntity>> allStatmentsForAccountId(@RequestParam Long id, @RequestParam Optional<String> from, @RequestParam Optional<String> to, @RequestParam Optional<String> amountFrom, @RequestParam Optional<String> amountTo) {


        Optional<AccountEntity> accountEntity = accountRepo.findAccountEntityById(id);

        if (accountEntity.isPresent()) {
            System.out.println(amountFrom);

            if (amountFrom.isPresent() && amountTo.isPresent()) {
                System.out.println(amountFrom);
                System.out.println(amountTo);
                try {
                    Long amountFrom1 = Long.parseLong(String.valueOf(amountFrom));

                    Long amountTo1 = Long.parseLong(String.valueOf(amountTo));


                    System.out.println(amountFrom1);


                    if (amountFrom1 <= amountTo1) {
                        System.out.println(amountFrom1);
                        List<StatementEntity> statementEntities = accountRepo.findAccountEntityById(id).get().getStatementEntity();
                        System.out.println(statementEntities.get(1).getId());
                        System.out.println(statementEntities.get(0).getId());

                        List<StatementEntity> statementEntitiesFilterd = statementEntities.stream().filter(new Predicate<StatementEntity>() {
                            @Override
                            public boolean test(StatementEntity statementEntity) {
                                try {
                                    Long statementEntityAmount = Long.parseLong(statementEntity.getAmount());

                                    if ((statementEntityAmount == amountFrom1) || (statementEntityAmount == amountTo1)) {
                                        return ((statementEntityAmount == amountFrom1)) || (statementEntityAmount == amountTo1);
                                    } else if (statementEntityAmount < amountFrom1 && statementEntityAmount > amountTo1)
                                        return (statementEntityAmount < amountFrom1 && statementEntityAmount > amountTo1);

                                    else {
                                        return false;
                                    }
                                } catch (Exception e) {
                                    throw new ApiRequestException("null");
                                }
                            }
                        }).collect(Collectors.toList());

                        return new ResponseEntity<>(statementEntitiesFilterd, HttpStatus.OK);
                    } else {
                        throw new ApiRequestException("from date should be before to time");
                    }
                } catch (Exception exception) {

                    throw new ApiRequestException("one of dates are not valid sorry");
                }
            } else {
                List<StatementEntity> statementEntities = accountRepo.findAccountEntityById(id).get().getStatementEntity();

                return new ResponseEntity<>(statementEntities, HttpStatus.OK);
            }
        } else {
            throw new ApiRequestException("account not found");

        }

        //amouunt filteration

//        if (amountFrom.isPresent() && amountTo.isPresent()) {
//            try {
//                long amountFromA = Long.parseLong(String.valueOf(amountFrom));
//                long amountFromB = Long.parseLong(String.valueOf(amountTo));
//
//               if (amountFromA<amountFromB) {
//                   List<StatementEntity> statementEntities = accountRepo.findAccountEntityById(id).get().getStatementEntity();
//
//               }
//                    List<StatementEntity> statementEntitiesFiltered = statementEntities.stream().filter(new Predicate<StatementEntity>() {
//                        @Override
//                        public boolean test(StatementEntity statementEntity) {
//                            try {
//                                long statementEntityAmount = long.parse(statementEntity.getAmount());
//                                    return statementEntityDate.after(fromTime) && statementEntityDate.before(toTime);
//                                return statementEntityAmount.before(fromTime) && statementEntityAmount.after(toTime);
//                            } catch (ParseException e) {
//                                throw new ApiRequestException("Invalid date");
//
//
//                } else {
//                    throw new ApiRequestException("from should be before to amount");
//                }
//            } catch (Exception exception) {
//                throw new ApiRequestException("one of the amounts is not valid");
//            }
//        } else {
//            List<StatementEntity> statementEntities = accountRepo.findAccountEntityById(id).get().getStatementEntity();
//            return new ResponseEntity<>(statementEntities, HttpStatus.OK);
//        }
//    } else
//
//    {
//        throw new ApiRequestException("account not found");
//
//   }

    }
}







