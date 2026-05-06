package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.*;
import com.viveflores.blogturistico.Repository.EventoRepository;
import com.viveflores.blogturistico.Repository.FavoritosRepository;
import com.viveflores.blogturistico.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CardsController {
    private final EventoService eventoService;
    private final PublicacionesService publicacionesService;
    private final ServicioService servicioService;
    private final ResenaService resenaService;
    private final FavoritosService favoritosService;
    private final FavoritosRepository favoritosRepository;

    public CardsController(EventoService eventoService, EventoRepository eventoRepository, PublicacionesService publicacionesService, ServicioService servicioService, ResenaService resenaService, FavoritosService favoritosService, FavoritosRepository favoritosRepository) {
        this.eventoService = eventoService;
        this.publicacionesService = publicacionesService;
        this.servicioService = servicioService;
        this.resenaService = resenaService;
        this.favoritosService = favoritosService;
        this.favoritosRepository = favoritosRepository;
    }

    @GetMapping("/eventosAll")
    public String mostrarEventos(Model model) {
        model.addAttribute("eventos", eventoService.getAllEventos());
        return "Eventos";
    }

    @GetMapping("/evento/{id}")
    public String verEvento(@PathVariable Integer id, Model model) {
        Evento evento = eventoService.getEventoById(id);
        model.addAttribute("evento", evento);
        return "evento";
    }

    @GetMapping("/publicacionesAll")
    public String mostrarPublicaciones(Model model) {
        model.addAttribute("publicaciones", publicacionesService.getAllPublicaciones());
        return "Publicaciones";
    }

    @GetMapping("/post/{id}")
    public String verPost(@PathVariable Integer id, Model model) {

        Publicaciones post = publicacionesService.getPublicacionesById(id);

        Integer idUsuario = 1;

        boolean esFavorito = favoritosRepository
                .findByUsuarioAndPublicacion(idUsuario, id)
                .isPresent();

        model.addAttribute("post", post);
        model.addAttribute("resenas", resenaService.getResenasByPublicacionId(id));
        model.addAttribute("esFavorito", esFavorito);

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
    public String mostrarServicios(Model model) {
        model.addAttribute("servicios", servicioService.getAllServicios());
        return "Servicios";
    }

    @GetMapping("/servicio/{id}")
    public String verServico(@PathVariable Integer id, Model model) {
        Servicio servicio = servicioService.getServicioById(id);
        model.addAttribute("servicio", servicio);
        return "servicio";
    }

    @PostMapping("/favorito/{id}")
    public String toggleFavorito(@PathVariable Integer id) {

        Integer idUsuario = 1;

        Optional<Favoritos> fav = favoritosRepository
                .findByUsuarioAndPublicacion(idUsuario, id);

        if (fav.isPresent()) {
            favoritosRepository.delete(fav.get());
        } else {
            Favoritos nuevo = new Favoritos();
            nuevo.setId_usuario(idUsuario);
            nuevo.setId_publicacion(id);
            nuevo.setId_categoria(1);
            favoritosService.saveFavoritos(nuevo);
        }

        return "redirect:/post/" + id;
    }

}
