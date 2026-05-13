package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.servicio;
import com.viveflores.blogturistico.Service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ServicioController {

//    @Autowired
//    private ServicioService service;
//
//    @GetMapping("/servicioUsuario")
//    public String formularioServicio(Model model) {
//        model.addAttribute("servicio", new servicio());
//        return "agregarServicio";
//    }
//
//    @PostMapping("/servicio/agregar")
//    public String agregar(@ModelAttribute("servicio") servicio servicio) {
//        servicio.setId_usuario(9);
//        servicio.setFecha_creacion(java.time.LocalDate.now());
//        service.agregarServicio(servicio);
//        return "redirect:/servicioUsuario?exito";
//    }
//
//    @GetMapping("/")
//    public String inicio() {
//        return "redirect:/servicioUsuario";
//    }
}