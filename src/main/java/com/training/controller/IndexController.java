/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ALz
 */
@Controller
public class IndexController {
//    @Autowired
//    DataRepository datarepo;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Model model) {
        return "index";
    }

    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public String inputNamePost(
            @RequestParam(value = "data") String data, Model model
    ) {
        model.addAttribute("message", data);
        return "users/index";
    }
    
    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String inputNameGet(
            @RequestParam(value = "data") String data, Model model
    ) {
        model.addAttribute("message", data
                +" lihat url, data dipasringkan dan terlihat karena method GET");
        return "users/index";
    }
    

      
    
}
