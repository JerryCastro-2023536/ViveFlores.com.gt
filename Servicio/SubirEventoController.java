package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Evento;
import com.viveflores.blogturistico.Service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/subirevento")
public class SubirEventoController {

    private final EventoService eventoService;

    public SubirEventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public String mostrarVista(Model model) {

        model.addAttribute("evento", new Evento());

        return "subirEvento";
    }

    @PostMapping("/guardar")
    public String guardarEvento(
            @ModelAttribute Evento evento,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            Model model
    ) {

        try {

            if (archivo != null && !archivo.isEmpty()) {
                evento.setFoto(archivo.getBytes());
            }

            eventoService.saveEvento(evento);

            model.addAttribute("success", "Evento publicado correctamente");

        } catch (Exception e) {

            e.printStackTrace();

            model.addAttribute("error", "Ocurrió un error al publicar el evento");
        }

        model.addAttribute("evento", new Evento());

        return "subirEvento";
    }
}