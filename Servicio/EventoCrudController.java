package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Evento;
import com.viveflores.blogturistico.Service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/eventoscrud")
public class EventoCrudController {

    private final EventoService eventoService;

    public EventoCrudController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("eventos", eventoService.getAllEventos());
        model.addAttribute("evento", new Evento());

        return "eventosCrud";
    }

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Evento evento,
            @RequestParam("archivo") MultipartFile archivo
    ) {
        try {

            if (!archivo.isEmpty()) {
                evento.setFoto(archivo.getBytes());
            }

            eventoService.saveEvento(evento);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/eventoscrud";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        Evento eventoEdit = eventoService.getEventoById(id);

        model.addAttribute("evento", eventoEdit);
        model.addAttribute("eventos", eventoService.getAllEventos());

        return "eventosCrud";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        eventoService.deleteEvento(id);

        return "redirect:/eventoscrud";
    }
}