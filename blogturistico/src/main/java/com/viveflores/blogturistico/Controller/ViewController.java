package com.viveflores.blogturistico.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/quienessomos")
    public String quienesSomos(){
        return "QuienesSomos";
    }

    @GetMapping("/contacto")
    public String contacto(){
        return "Contacto";
    }

    @GetMapping("/paneladmin")
    public String panelAdmin(){
        return "paneladmin";
    }
}
