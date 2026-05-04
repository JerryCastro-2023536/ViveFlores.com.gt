package com.viveflores.blogturistico.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilUsuarioController {

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model){
        return "perfilUsuario";
    }

}
