package com.viveflores.blogturistico.Controller;

import ch.qos.logback.core.model.Model;
import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Service.UsuariosService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class RegisterController {

    private final UsuariosService usuariosService;

    public RegisterController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
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
                              Model model){

        Usuarios u = new Usuarios();

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
