/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author ALz
 */
@Controller
public class IndexController {
//    @Autowired
//    DataRepository datarepo;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Model model){
        return "index";
    }
    
    @RequestMapping(value="/input",method = RequestMethod.GET)
    public String inputName(
            @RequestParam(value="data") String data,Model model
    ){
//        DataModel inputan = datarepo.getData();
        model.addAttribute("message",data);
        return "users/index";
    }
    

}
