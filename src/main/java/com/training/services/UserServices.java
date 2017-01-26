/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.services;

import com.training.model.User;
import com.training.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ALz
 */
@Service
public class UserServices{
    
    @Autowired
    UserRepository userRepository;
    
    public void insert(User model){
        userRepository.save(model);
    }
    
    public void update(String id, String username, String password,String email){
        userRepository.updateUserByID(id, username, password, email);
    }
    public void delete(){}
    
    public boolean authLogin(String username,String password){
        User result = userRepository.authLogin(username, password);
        Boolean checkResult=true;
        if(result==null)
            checkResult=false;
        return checkResult;
    }
    
    public List<User>seeAllUsers(){
        List<User> result;
        result = userRepository.findAll();
        return result;
    }
    
}
