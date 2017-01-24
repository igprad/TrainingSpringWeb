/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.controller;

import com.training.model.User;
import com.training.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ALz
 */
@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(Model model){
//        List<User> data = userRepository.findAll();
//        model.addAttribute("dataUsers",data);
        return "users/add";
    }        
    @RequestMapping(value = "/users/view", method = RequestMethod.GET)
    public String getUsers(Model model){
        List<User> data = userRepository.findAll();
        model.addAttribute("dataUsers",data);
        return "users/ViewUsers";
    }        
}
