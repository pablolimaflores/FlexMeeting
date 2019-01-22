package com.projeto.flexmeeting.domain.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
//	@GetMapping("/")
//    public String indice() {        
//        return "login";
//    }
    
//    @GetMapping("/home")
//    public String home() {        
//        return "home";
//    }
    
    @GetMapping("/login")
    public String login() {        
        return "login";
    }
}
