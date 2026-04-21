package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Repository.PublicacionesRepository;
import com.viveflores.blogturistico.Service.EventoService;
import com.viveflores.blogturistico.Service.PublicacionesService;
import com.viveflores.blogturistico.Service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaPrincipalController {

    private final PublicacionesService publicacionesService;
    private final ServicioService servicioService;
    private final EventoService eventoService;

    public PaginaPrincipalController(PublicacionesService publicacionesService, ServicioService servicioService, EventoService eventoService) {
        this.publicacionesService = publicacionesService;
        this.servicioService = servicioService;
        this.eventoService = eventoService;
    }

    @GetMapping("/")
    public String redirigir(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String paginaPrincipal(Model model){
        model.addAttribute("publi", publicacionesService.getAllPublicaciones());
        model.addAttribute("servi", servicioService.getAllServicios());
        model.addAttribute("event", eventoService.getAllEventos());
        return "index";
    }

}
