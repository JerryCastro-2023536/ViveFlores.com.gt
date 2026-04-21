package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.viveflores.blogturistico.Service.UsuariosService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsuariosController {
    @Autowired
    private UsuariosService service;
    
    @GetMapping("/")
    public String inicio() {return "redirect:/usuarios";}

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<Usuarios> lista = service.listar();
        model.addAttribute("usuarios", lista);
        return "usuarios";
    }
    
}