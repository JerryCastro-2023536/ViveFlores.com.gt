package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Repository.EventoRepository;
import com.viveflores.blogturistico.Service.EventoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EventosAllController {
    private final EventoService eventoService;
    private final EventoRepository eventoRepository;

    public EventosAllController(EventoService eventoService, EventoRepository eventoRepository) {
        this.eventoService = eventoService;
        this.eventoRepository = eventoRepository;
    }

    @GetMapping("/eventosAll")
    public String mostrarEventos(Model model){
        model.addAttribute("eventos", eventoService.getAllEventos());
        return "Eventos";
    }

}
