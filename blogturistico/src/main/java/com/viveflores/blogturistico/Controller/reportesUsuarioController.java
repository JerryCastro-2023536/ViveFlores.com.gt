package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Reportes;
import com.viveflores.blogturistico.Service.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class reportesUsuarioController {

    @Autowired
    private ReportesService service;

    @GetMapping("/reportesUsuario")
    public String formularioUsuario(Model model) {
        model.addAttribute("reporte", new Reportes());
        return "reportesUsuario";
    }

    @PostMapping("/usuarios/reportes/agregar")
    public String agregar(@ModelAttribute("reporte") Reportes reportes) {
        reportes.setId_usuario(9);
        reportes.setFecha_envio(java.time.LocalDateTime.now());
        service.agregarReportes(reportes);
        return "redirect:/reportesUsuario?exito";
    }
}
