//package assignment2.assignment2.statement;
//
//import assignment2.assignment2.Account.AccountEntity;
//import assignment2.assignment2.Account.AccountRepo;
//import assignment2.assignment2.Dto;
//import assignment2.assignment2.Request;
//import assignment2.assignment2.ResponseTransfer;
//import assignment2.assignment2.exception.ApiRequestException;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.http.converter.json.GsonBuilderUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/v1/statment")
//public class StatementController {
//    @Autowired
//    AccountRepo accountRepo;
//
//    @Autowired
//    StatmentRepo statmentRepo;
//    Dto dto = new Dto();
//
//
////    @PostMapping("/search")
////    public ResponseEntity<List<StatementEntity>> search(@RequestBody ObjectNode body) {
////        long id = body.at("/statement/id").asLong();
////        String dateFrom = body.at("/criteria/dateFrom").textValue();
////        String dateTo = body.at("/criteria/dateTo").textValue();
//////        String amountFrom = body.at("/criteria/amountFrom").textValue();
//////        String amountTo = body.at("/criteria/amountTo").textValue();
////        return ResponseEntity.ok(List.of());
////    }
//
//
////    @PostMapping(value = "/content", produces = MediaType.APPLICATION_JSON_VALUE)
////    @ResponseBody
////    public ResponseTransfer postResponseJsonContent(
////            @RequestBody LoginForm loginForm) {
////        return new ResponseTransfer();
////    }
//
//
//    @GetMapping("/all")
//    public ResponseEntity<List<StatementEntity>> allStatmentsForAccountId( @RequestParam Long id, @RequestParam Optional<String> from, @RequestParam Optional<String> to, @RequestParam Optional<String> amountFrom, @RequestParam Optional<String> amountTo) {
//
//
//        Optional<AccountEntity> accountEntity = accountRepo.findAccountEntityById(id);
//        if (accountEntity.isPresent()) {
//
//            if (amountFrom.isPresent() && amountTo.isPresent()) {
//                try {
//                    Long amountFrom1 = Long.parseLong(String.valueOf(amountFrom));
//                    Long amountTo1 = Long.parseLong((String.valueOf(amountTo)));
//                    if (amountFrom1 < amountTo1) {
//                        List<StatementEntity> statementEntities = accountRepo.findAccountEntityById(id).get().getStatementEntity();
//                        System.out.println(statementEntities);
//                        List<StatementEntity> statementEntitiesFilterd = statementEntities.stream().filter(new Predicate<StatementEntity>() {
//                            @Override
//                            public boolean test(StatementEntity statementEntity) {
//                                try {
//                                    long statementEntityAmount = Long.parseLong(statementEntity.getAmount());
//                                    if ((statementEntityAmount == amountFrom1) && (statementEntityAmount == amountTo1)) {
//                                        return ((statementEntityAmount == amountFrom1)) || (statementEntityAmount == amountTo1);
//
//                                    } else if (statementEntityAmount >= amountFrom1 || statementEntityAmount <= amountTo1)
//                                        return (statementEntityAmount >= amountFrom1) && (statementEntityAmount <= amountTo1);
//
//                                    else {
//                                        return false;
//                                    }
//                                } catch (Exception e) {
//                                    throw new ApiRequestException("null");
//                                }
//                            }
//                        }).collect(Collectors.toList());
//                        return new ResponseEntity<>(statementEntitiesFilterd, HttpStatus.OK);
//
//                    } else {
//                        throw new ApiRequestException("from date should be before ");
//                    }
//
//                } catch (Exception exception) {
//
//                    throw new ApiRequestException("one of dates are not valid");
//                }
//            } else {
//                List<StatementEntity> statementEntities = accountRepo.findAccountEntityById(id).get().getStatementEntity();
//
//                return new ResponseEntity<>(statementEntities, HttpStatus.OK);
//            }
//        } else {
//            throw new ApiRequestException("account not found");
//
//        }
//    }
//}
//
