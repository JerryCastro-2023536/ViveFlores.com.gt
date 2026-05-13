package com.viveflores.blogturistico.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MensajesVendedorController {

    @GetMapping("mensajes")
    public String Mensajes(){
        return "mensajesVendedor";
    }
}
