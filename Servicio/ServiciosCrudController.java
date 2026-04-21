package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Servicio;
import com.viveflores.blogturistico.Service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/servicioscrud")
public class ServiciosCrudController {

    private final ServicioService servicioService;

    public ServiciosCrudController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("servicios", servicioService.getAllServicios());
        model.addAttribute("servicio", new Servicio());

        return "serviciosCrud"; // 👈 tu HTML
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Servicio servicio) {

        if (servicio.getId_servicio() == null) {
            servicio.setFecha_creacion(LocalDate.now());
        }

        servicioService.saveServicio(servicio);

        return "redirect:/servicios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        Servicio servicioEdit = servicioService.getServicioById(id);

        model.addAttribute("servicio", servicioEdit);
        model.addAttribute("servicios", servicioService.getAllServicios());

        return "servicioCrud";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        servicioService.deleteServicio(id);

        return "redirect:/servicios";
    }
}