package com.example.syberproject;

import com.example.syberproject.DTO.Dto;
import com.example.syberproject.Entity.StatementEntity;
import com.example.syberproject.Repository.StatmentRepo;
import com.example.syberproject.Service.Service;
import com.example.syberproject.modelResponse.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@org.springframework.stereotype.Service
public class serviceImpl implements Service {
    @Autowired
    StatmentRepo statmentRepo;




    @Override
    public Dto createUser(Dto user) {
        if(statmentRepo.findById(user.getId())!= null) throw new RuntimeException("null");
        StatementEntity statementEntity = new StatementEntity();
        BeanUtils.copyProperties(user,statementEntity);
        Dto res = new Dto();
        BeanUtils.copyProperties(statementEntity,res);
        return res;
    }
}
