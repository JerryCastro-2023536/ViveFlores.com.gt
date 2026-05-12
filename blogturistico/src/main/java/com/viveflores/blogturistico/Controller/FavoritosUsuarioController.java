package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Repository.FavoritosRepository;
import jakarta.servlet.http.HttpSession;
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
    public String misFavoritos(Model model, HttpSession session) {

        Usuarios usuarioLogueado = (Usuarios) session.getAttribute("usuarioLogueado");
        
        if (usuarioLogueado == null) {
            return "redirect:/acceder";
        }

        Integer idUsuario = usuarioLogueado.getId_usuario();

        model.addAttribute("publicaciones",
                favoritosRepository.findFavoritosByUsuario(idUsuario));

        return "favoritosUsuario";
    }
}
