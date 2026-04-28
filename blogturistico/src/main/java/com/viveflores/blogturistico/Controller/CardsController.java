package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Repository.EventoRepository;
import com.viveflores.blogturistico.Repository.PublicacionesRepository;
import com.viveflores.blogturistico.Service.EventoService;
import com.viveflores.blogturistico.Service.PublicacionesService;
import com.viveflores.blogturistico.Service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardsController {
    private final EventoService eventoService;
    private final PublicacionesService publicacionesService;
    private final ServicioService servicioService;

    public CardsController(EventoService eventoService, EventoRepository eventoRepository, PublicacionesService publicacionesService, ServicioService servicioService) {
        this.eventoService = eventoService;
        this.publicacionesService = publicacionesService;
        this.servicioService = servicioService;
    }

    @GetMapping("/eventosAll")
    public String mostrarEventos(Model model){
        model.addAttribute("eventos", eventoService.getAllEventos());
        return "Eventos";
    }

    @GetMapping("/publicacionesAll")
    public String mostrarPublicaciones(Model model){
        model.addAttribute("publicaciones", publicacionesService.getAllPublicaciones());
        return "Publicaciones";
    }

    @GetMapping("/serviciosAll")
    public String mostrarServicios(Model model){
        model.addAttribute("servicios", servicioService.getAllServicios());
        return "Servicios";
    }

}
