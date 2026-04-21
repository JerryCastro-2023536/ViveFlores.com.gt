package com.viveflores.blogturistico.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/acceder")
    public String login(){
        return "login";
    }
}
