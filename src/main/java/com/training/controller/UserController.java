/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.controller;

import com.training.model.User;
import com.training.services.UserServices;
import com.training.validator.UserFormValidator;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ALz
 */
@Controller
public class UserController {
    
    @Autowired
    UserServices userService;

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String goToAddUser(
    ) {
        return "users/AddUser";
    }
    
    @RequestMapping(value = "/add/input", method = RequestMethod.POST)
    public ModelAndView addUser(
            @Valid UserFormValidator userForm,
            BindingResult bindingResult,
            //            @RequestParam(value="username") String username,
            //            @RequestParam(value="password") String password,
            //            @RequestParam(value="email") String email,
            @ModelAttribute User user,
            Model model) {
//        userRepository.save(new User(username,password,email));
        if(bindingResult.hasErrors()){
            return new ModelAndView("users/AddUser");
        }
        userService.insert(user);
        return new ModelAndView("redirect:/users/view");
    }

    @PostMapping("/users/login")
    public String login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            Model model) {
        Boolean auth = userService.authLogin(username, password);
        if (auth) {
            model.addAttribute("Message", "Login Berhasil");
            model.addAttribute("Color", "green");
        } else {
            model.addAttribute("Message", "Login Gagal");
            model.addAttribute("Color", "red");
        }
        return "users/LoginAuth";
    }

    @RequestMapping(value = "/users/view", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<User> data = userService.seeAllUsers();
        model.addAttribute("dataUsers", data);
        return ("users/ViewUsers");
    }
    @RequestMapping(value = "/users/search", method = RequestMethod.GET)
    public String getUsersByKeywords(
            @RequestParam(value="search")String keyword,
            @RequestParam(value="search")String keyword2,
            @RequestParam(value="search")String keyword3,
            Model model) {
        List<User> data = userService.seeUsersByKeyword(keyword, keyword2, keyword3);
        model.addAttribute("dataUsers", data);
        return ("users/ViewUsers");
    }
    
    @RequestMapping(value="/users/updateUser" ,method=RequestMethod.POST)
    public String getUpdatePage( 
            @RequestParam(value="id")Integer id,
            Model model){
        model.addAttribute("id",id);
        return "users/UpdateUser";
    }
    
    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public ModelAndView updateUser(
            @RequestParam(value="id")Integer id,
            @RequestParam(value="username")String newUsername,
            @RequestParam(value="password")String newPassword,
            @RequestParam(value="email")String newEmail,
            Model model) {
        userService.update(id, newUsername, newPassword, newEmail);
        return new ModelAndView("redirect:/users/view");
    }
    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    public ModelAndView deleteUser(
            @RequestParam(value="id")Integer id,
            Model model) {
        userService.delete(id);
        return  new ModelAndView("redirect:/users/view");
    }
    
    
}
