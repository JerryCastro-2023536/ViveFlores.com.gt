package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Repository.UsuariosRepository;
import com.viveflores.blogturistico.Service.UsuariosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class RegisterController {

    private final UsuariosService usuariosService;
    private final UsuariosRepository usuariosRepository;

    public RegisterController(UsuariosService usuariosService, UsuariosRepository usuariosRepository) {
        this.usuariosService = usuariosService;
        this.usuariosRepository = usuariosRepository;
    }

    @GetMapping("/registro")
    public String register(){
        return "register";
    }

    @PostMapping("/crear")
    public String crearCuenta(@RequestParam("username") String username,
                              @RequestParam("nombre")  String nombre,
                              @RequestParam("apellido") String apellido,
                              @RequestParam("email") String email,
                              @RequestParam("contrasena") String contrasena,
                              @RequestParam("confirmar") String confirmar,
                              Model model){

        Usuarios usuarios = usuariosRepository.findByUsername(username);

        if (usuarios != null) {
            model.addAttribute("errorMessage", "El usuario ya existe");
            return "register";
        }

        if(!contrasena.equals(confirmar)){
            model.addAttribute("errorMessage", "Las constraseñas no coinciden");
            return "register";
        }

        Usuarios u = new Usuarios();

        if (u == null) {
            return "register";
        }

        u.setUsername(username);
        u.setNombre_usuario(nombre);
        u.setApellido_usuario(apellido);
        u.setEmail_usuario(email);
        u.setContrasena_usuario(contrasena);
        u.setRol("usuario");
        u.setFecha_registro(LocalDate.now());

        usuariosService.saveUsuarios(u);

        return "redirect:/acceder";
    }

}
