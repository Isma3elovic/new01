package com.example.syberproject;

import com.example.syberproject.Entity.AccountEntity;
import com.example.syberproject.Entity.StatementEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(Optional<AccountEntity> accountEntity, List<StatementEntity> statementEntityFilter, HttpStatus status, boolean error, String message, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            map.put("account_number", accountEntity.get().getAccount_number().hashCode());
            map.put("account_type",accountEntity.get().getAccount_type());
            map.put("Statement",statementEntityFilter);
            map.put("id", accountEntity.get().getId());
//            map.put("isSuccess", error);
//            map.put("message", message);
//            map.put("data", responseObj);

            return new ResponseEntity<Object>(map, status);
        } catch (Exception e) {
            map.clear();
            map.put("timestamp", new Date());
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("isSuccess", false);
            map.put("message", e.getMessage());
            map.put("data", null);
            return new ResponseEntity<Object>(map, status);
        }
    }

}
