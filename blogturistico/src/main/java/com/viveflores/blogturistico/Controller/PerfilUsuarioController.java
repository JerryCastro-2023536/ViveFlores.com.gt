package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Favoritos;
import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Service.FavoritosService;
import com.viveflores.blogturistico.Service.UsuariosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PerfilUsuarioController {

    private final UsuariosService usuariosService;

    public PerfilUsuarioController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model){
        return "perfilUsuario";
    }

    @PostMapping("/editarPerfil")
    public String editarPerfil(
            @RequestParam("username") String username,
            @RequestParam("nombre_usuario") String nombre_usuario,
            @RequestParam("apellido_usuario") String apellido_usuario,
            @RequestParam("email_usuario") String email_usuario,
            HttpSession session,
            Model model) {
        
        try {
            Usuarios usuarioLogueado = (Usuarios) session.getAttribute("usuarioLogueado");
            
            if (usuarioLogueado != null) {
                Usuarios usuario = new Usuarios();
                usuario.setId_usuario(usuarioLogueado.getId_usuario());
                usuario.setUsername(username);
                usuario.setNombre_usuario(nombre_usuario);
                usuario.setApellido_usuario(apellido_usuario);
                usuario.setEmail_usuario(email_usuario);
                usuario.setContrasena_usuario(usuarioLogueado.getContrasena_usuario());
                usuario.setRol(usuarioLogueado.getRol());
                usuario.setFecha_registro(usuarioLogueado.getFecha_registro());
                
                Usuarios usuarioActualizado = usuariosService.updateUsuarios(usuarioLogueado.getId_usuario(), usuario);
                
                session.setAttribute("usuarioLogueado", usuarioActualizado);
                model.addAttribute("successMessage", "Perfil actualizado correctamente");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al actualizar el perfil: " + e.getMessage());
        }
        
        return "redirect:/perfil";
    }

}
