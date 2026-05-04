package com.viveflores.blogturistico.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FavoritosUsuarioController {
    @GetMapping("/favoritas")
    public String mostrarFavoritos(Model model){
        return "favoritosUsuario";
    }
}
