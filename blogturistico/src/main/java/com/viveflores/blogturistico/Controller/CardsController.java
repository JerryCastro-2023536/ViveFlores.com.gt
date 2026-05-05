package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Evento;
import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Entity.Resena;
import com.viveflores.blogturistico.Entity.Servicio;
import com.viveflores.blogturistico.Repository.EventoRepository;
import com.viveflores.blogturistico.Service.EventoService;
import com.viveflores.blogturistico.Service.PublicacionesService;
import com.viveflores.blogturistico.Service.ResenaService;
import com.viveflores.blogturistico.Service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CardsController {
    private final EventoService eventoService;
    private final PublicacionesService publicacionesService;
    private final ServicioService servicioService;
    private final ResenaService resenaService;

    public CardsController(EventoService eventoService, EventoRepository eventoRepository, PublicacionesService publicacionesService, ServicioService servicioService, ResenaService resenaService) {
        this.eventoService = eventoService;
        this.publicacionesService = publicacionesService;
        this.servicioService = servicioService;
        this.resenaService = resenaService;
    }

    @GetMapping("/eventosAll")
    public String mostrarEventos(Model model){
        model.addAttribute("eventos", eventoService.getAllEventos());
        return "Eventos";
    }

    @GetMapping("/evento/{id}")
    public String verEvento(@PathVariable Integer id, Model model){
        Evento evento = eventoService.getEventoById(id);
        model.addAttribute("evento", evento);
        return "evento";
    }

    @GetMapping("/publicacionesAll")
    public String mostrarPublicaciones(Model model){
        model.addAttribute("publicaciones", publicacionesService.getAllPublicaciones());
        return "Publicaciones";
    }

    @GetMapping("/post/{id}")
    public String verPost(@PathVariable Integer id, Model model) {
        Publicaciones post = publicacionesService.getPublicacionesById(id);
        model.addAttribute("post", post);
        model.addAttribute("resenas", resenaService.getResenasByPublicacionId(id));
        return "post";
    }

    @PostMapping("/post/{id}/comentar")
    public String comentarPost(
            @PathVariable Integer id,
            @RequestParam("comentario") String comentario,
            @RequestParam("tituloResena") String tituloResena,
            @RequestParam("calificacion") Integer calificacion) {

        Resena resena = new Resena();
        resena.setId_publicacion(id);
        resena.setId_usuario(1);
        resena.setFecha_creacion(java.time.LocalDate.now());
        resena.setComentario(comentario);
        resena.setTitulo_resena(tituloResena);
        resena.setCalificacion(calificacion);

        resenaService.saveResena(resena);

        return "redirect:/post/" + id;
    }

    @GetMapping("/serviciosAll")
    public String mostrarServicios(Model model){
        model.addAttribute("servicios", servicioService.getAllServicios());
        return "Servicios";
    }

    @GetMapping("/servicio/{id}")
    public String verServico(@PathVariable Integer id, Model model){
        Servicio servicio = servicioService.getServicioById(id);
        model.addAttribute("servicio", servicio);
        return "servicio";
    }

}
