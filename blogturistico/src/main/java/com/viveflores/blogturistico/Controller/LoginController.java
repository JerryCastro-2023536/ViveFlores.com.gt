package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Service.UsuariosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UsuariosService usuariosService;

    public LoginController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/acceder")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestParam("username") String username,
                                @RequestParam("contrasena") String contrasena,
                                Model model,
                                HttpSession session){

        Usuarios u = usuariosService.login(username, contrasena);

        if (u != null) {
            session.setAttribute("usuarioLogueado", u);
            return "redirect:/index";
        } else {
            model.addAttribute("errorMessage", "Credenciales incorrectas");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/index";
    }
}
