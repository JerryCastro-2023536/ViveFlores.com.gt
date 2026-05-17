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
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class CardsController {
    private final EventoService eventoService;
    private final PublicacionesService publicacionesService;
    private final ServicioService servicioService;
    private final ResenaService resenaService;
    private final FavoritosService favoritosService;
    private final FavoritosRepository favoritosRepository;
    private final UsuariosService usuariosService;

    public CardsController(EventoService eventoService, EventoRepository eventoRepository, PublicacionesService publicacionesService, ServicioService servicioService, ResenaService resenaService, FavoritosService favoritosService, FavoritosRepository favoritosRepository, UsuariosService usuariosService) {
        this.eventoService = eventoService;
        this.publicacionesService = publicacionesService;
        this.servicioService = servicioService;
        this.resenaService = resenaService;
        this.favoritosService = favoritosService;
        this.favoritosRepository = favoritosRepository;
        this.usuariosService = usuariosService;
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
        List<Publicaciones> activas = publicacionesService.getAllPublicaciones().stream()
                .filter(p -> p.getEstado_publicacion() != null &&
                             (p.getEstado_publicacion().equalsIgnoreCase("activo") ||
                              p.getEstado_publicacion().equalsIgnoreCase("aceptado")))
                .toList();
        model.addAttribute("publicaciones", activas);
        return "Publicaciones";
    }

    @GetMapping("/post/{id}")
    public String verPost(@PathVariable Integer id, Model model, HttpSession session) {

        Publicaciones post = publicacionesService.getPublicacionesById(id);
        Usuarios usuarioLogueado = (Usuarios) session.getAttribute("usuarioLogueado");

        boolean esFavorito = false;
        if (usuarioLogueado != null) {
            esFavorito = favoritosRepository
                    .findByUsuarioAndPublicacion(usuarioLogueado.getId_usuario(), id)
                    .isPresent();
        }

        List<Resena> resenas = resenaService.getResenasByPublicacionId(id);
        
        // Obtener nombres de usuario para las reseñas
        Map<Integer, String> nombresUsuarios = new HashMap<>();
        for (Resena resena : resenas) {
            if (!nombresUsuarios.containsKey(resena.getId_usuario())) {
                try {
                    Usuarios user = usuariosService.getUsuariosById(resena.getId_usuario());
                    if (user != null) {
                        nombresUsuarios.put(user.getId_usuario(), user.getUsername());
                    } else {
                        nombresUsuarios.put(resena.getId_usuario(), "Usuario " + resena.getId_usuario());
                    }
                } catch (Exception e) {
                    nombresUsuarios.put(resena.getId_usuario(), "Usuario " + resena.getId_usuario());
                }
            }
        }

        model.addAttribute("post", post);
        model.addAttribute("resenas", resenas);
        model.addAttribute("nombresUsuarios", nombresUsuarios);
        model.addAttribute("esFavorito", esFavorito);

        return "post";
    }

    @PostMapping("/post/{id}/comentar")
    public String comentarPost(
            @PathVariable Integer id,
            @RequestParam("comentario") String comentario,
            @RequestParam("tituloResena") String tituloResena,
            @RequestParam("calificacion") Integer calificacion,
            HttpSession session) {

        Usuarios usuarioLogueado = (Usuarios) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            return "redirect:/acceder";
        }

        Resena resena = new Resena();
        resena.setId_publicacion(id);
        resena.setId_usuario(usuarioLogueado.getId_usuario());
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
    public String toggleFavorito(@PathVariable Integer id, HttpSession session, jakarta.servlet.http.HttpServletRequest request) {

        Usuarios usuarioLogueado = (Usuarios) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            return "redirect:/acceder";
        }

        try {
            Usuarios realUser = usuariosService.getUsuariosById(usuarioLogueado.getId_usuario());
            if (realUser == null) {
                session.invalidate();
                return "redirect:/acceder";
            }
        } catch (Exception e) {
            session.invalidate();
            return "redirect:/acceder";
        }

        Integer idUsuario = usuarioLogueado.getId_usuario();

        Optional<Favoritos> fav = favoritosRepository
                .findByUsuarioAndPublicacion(idUsuario, id);

        if (fav.isPresent()) {
            favoritosRepository.delete(fav.get());
        } else {
            Favoritos nuevo = new Favoritos();
            nuevo.setId_usuario(idUsuario);
            nuevo.setId_publicacion(id);
            
            Publicaciones post = publicacionesService.getPublicacionesById(id);
            if (post != null && post.getId_categoria() != null) {
                nuevo.setId_categoria(post.getId_categoria());
            } else {
                nuevo.setId_categoria(1); 
            }
            
            favoritosService.saveFavoritos(nuevo);
        }

        String referer = request.getHeader("Referer");
        if (referer != null && referer.contains("/favoritas")) {
            return "redirect:/favoritas";
        }

        return "redirect:/post/" + id;
    }

}
