package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Favoritos;
import com.viveflores.blogturistico.Service.FavoritosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PerfilUsuarioController {

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model){
        return "perfilUsuario";
    }

}
