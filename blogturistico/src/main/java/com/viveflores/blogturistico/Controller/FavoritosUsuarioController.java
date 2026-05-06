package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Repository.FavoritosRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FavoritosUsuarioController {

    private final FavoritosRepository favoritosRepository;

    public FavoritosUsuarioController(FavoritosRepository favoritosRepository) {
        this.favoritosRepository = favoritosRepository;
    }

    @GetMapping("/favoritas")
    public String misFavoritos(Model model) {

        Integer idUsuario = 1;

        model.addAttribute("publicaciones",
                favoritosRepository.findFavoritosByUsuario(idUsuario));

        return "favoritosUsuario";
    }
}
