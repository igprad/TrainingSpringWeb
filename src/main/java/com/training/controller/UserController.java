/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.controller;

import com.training.model.User;
import com.training.services.UserServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ALz
 */
@Controller
public class UserController {
    
    @Autowired
    UserServices userService;

    @RequestMapping(value = "/add/input", method = RequestMethod.POST)
    public ModelAndView addUser(
            //            @RequestParam(value="username") String username,
            //            @RequestParam(value="password") String password,
            //            @RequestParam(value="email") String email,
            @ModelAttribute User user,
            Model model) {
//        userRepository.save(new User(username,password,email));
        userService.insert(user);
        return new ModelAndView("redirect:/users/view");
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public String login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            Model model) {
        Boolean auth = userService.authLogin(username, password);
        if (auth) {
            model.addAttribute("Message", "Login Berhasil");
        } else {
            model.addAttribute("Message", "Login Gagal");
        }
        return "users/LoginAuth";
    }

    @RequestMapping(value = "/users/view", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<User> data = userService.seeAllUsers();
        model.addAttribute("dataUsers", data);
        return "users/ViewUsers";
    }
}
