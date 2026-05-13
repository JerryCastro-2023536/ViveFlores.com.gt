package com.viveflores.blogturistico.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubirPublicacionController {

    @GetMapping("/subir")
    public String subirPublicacion(){
        return "subirPublicacion";
    }
}
