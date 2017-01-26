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
    
    public void update(Integer id, String username, String password,String email){
        userRepository.updateUserByID(id, username, password, email);
    }
    public void delete(Integer id){
        userRepository.delete(id);
    }
    
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
    
    public List<User>seeUsersByKeyword(String keyword,String keyword2,String keyword3){
//        List<User> result = userRepository.searchByKeyword(keyword);
        List<User> result = userRepository.findByUsernameOrPasswordOrEmailContaining(keyword, keyword2, keyword3);
        return result;
    }
}
