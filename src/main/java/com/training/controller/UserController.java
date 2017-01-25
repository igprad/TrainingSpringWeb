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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ALz
 */
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/add/input", method = RequestMethod.POST)
    public String addUser(
            //            @RequestParam(value="username") String username,
            //            @RequestParam(value="password") String password,
            //            @RequestParam(value="email") String email,
            @ModelAttribute User user,
            Model model) {
//        userRepository.save(new User(username,password,email));
        userRepository.save(user);
        return "users/ViewUsers";
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public String login(
                        @RequestParam(value="username") String username,
                        @RequestParam(value="password") String password,
            Model model) {
        User result = userRepository.authLogin(username,password);
        if(result==null)
            model.addAttribute("Message","Login failed");
        else
            model.addAttribute("Message","Loggin success");
        return "users/LoginAuth";
    }

    @RequestMapping(value = "/users/view", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<User> data = userRepository.findAll();
        model.addAttribute("dataUsers", data);
        return "users/ViewUsers";
    }
}
