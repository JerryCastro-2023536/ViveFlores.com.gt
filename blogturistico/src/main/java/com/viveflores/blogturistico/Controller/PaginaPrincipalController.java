package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Repository.EventoRepository;
import com.viveflores.blogturistico.Repository.PublicacionesRepository;
import com.viveflores.blogturistico.Repository.ServicioRepository;
import com.viveflores.blogturistico.Service.EventoService;
import com.viveflores.blogturistico.Service.PublicacionesService;
import com.viveflores.blogturistico.Service.ServicioService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaginaPrincipalController {

    private final PublicacionesService publicacionesService;
    private final PublicacionesRepository publicacionesRepository;
    private final ServicioRepository servicioRepository;
    private final EventoRepository eventoRepository;
    private final ServicioService servicioService;
    private final EventoService eventoService;

    public PaginaPrincipalController(PublicacionesService publicacionesService, PublicacionesRepository publicacionesRepository, ServicioRepository servicioRepository, EventoRepository eventoRepository, ServicioService servicioService, EventoService eventoService) {
        this.publicacionesService = publicacionesService;
        this.publicacionesRepository = publicacionesRepository;
        this.servicioRepository = servicioRepository;
        this.eventoRepository = eventoRepository;
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

    @GetMapping("/publicaciones/foto/{id}")
    public ResponseEntity<byte[]> obtenerFotoPublicacion(@PathVariable("id") Integer id) {
        return publicacionesRepository.findById(id)
                .map(u -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(u.getFoto()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/servicios/foto/{id}")
    public ResponseEntity<byte[]> obtenerFotoServicio(@PathVariable("id") Integer id) {
        return servicioRepository.findById(id)
                .map(u -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(u.getFoto()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/eventos/foto/{id}")
    public ResponseEntity<byte[]> obtenerFotoEvento(@PathVariable("id") Integer id) {
        return eventoRepository.findById(id)
                .map(u -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(u.getFoto()))
                .orElse(ResponseEntity.notFound().build());
    }

}
