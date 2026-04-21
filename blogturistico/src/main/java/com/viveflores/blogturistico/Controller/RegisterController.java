package com.viveflores.blogturistico.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping("/registro")
    public String register(){
        return "register";
    }
}
