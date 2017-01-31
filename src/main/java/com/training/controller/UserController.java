/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.training.controller;

import com.training.model.User;
import com.training.services.UserServices;
import com.training.validator.UserFormValidator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author ALz
 */
@Controller
public class UserController extends WebMvcConfigurerAdapter {

  @Autowired
  UserServices userService;

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/users/view").setViewName("users/ViewUsers");
  }

  @RequestMapping(value = "/users/add", method = RequestMethod.GET)
  public String goToAddUser() {
    return "users/AddUser";
  }

  @PostMapping("/add/input")
  public String addUser(@Valid @ModelAttribute("userForm") UserFormValidator userForm,
      BindingResult bindingResult,
      // @RequestParam(value="username") String username,
      // @RequestParam(value="password") String password,
      // @RequestParam(value="email") String email,
      HttpServletRequest request, @ModelAttribute User user, Model model) {
    // userRepository.save(new User(username,password,email));

    if (bindingResult.hasErrors()) {
      return ("users/AddUser");
    }
    userService.insert(user);
    return ("redirect:/users/view");
  }

  @PostMapping("/users/login")
  public String login(@RequestParam(value = "username") String username,
      @RequestParam(value = "password") String password, Model model) {
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
  public String getUsersByKeywords(@RequestParam(value = "search") String keyword,
      @RequestParam(value = "search") String keyword2,
      @RequestParam(value = "search") String keyword3, Model model) {
    List<User> data = userService.seeUsersByKeyword(keyword, keyword2, keyword3);
    model.addAttribute("dataUsers", data);
    return ("users/ViewUsers");
  }

  @RequestMapping(value = "/users/updateUser", method = RequestMethod.POST)
  public String getUpdatePage(@RequestParam(value = "id") Integer id,
      @RequestParam(value = "") String username, @RequestParam(value = "") String email,
      Model model) {
    model.addAttribute("id", id);
    model.addAttribute("username", username);
    model.addAttribute("email", email);
    return "users/UpdateUser";
  }

  @RequestMapping(value = "/users/update", method = RequestMethod.POST)
  public ModelAndView updateUser(@RequestParam(value = "id") Integer id,
      @RequestParam(value = "username") String newUsername,
      @RequestParam(value = "password") String newPassword,
      @RequestParam(value = "email") String newEmail, Model model) {
    userService.update(id, newUsername, newPassword, newEmail);
    return new ModelAndView("redirect:/users/view");
  }

  @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
  public ModelAndView deleteUser(@RequestParam(value = "id") Integer id, Model model) {
    userService.delete(id);
    return new ModelAndView("redirect:/users/view");
  }

}
