package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Repository.EventoRepository;
import com.viveflores.blogturistico.Repository.PublicacionesRepository;
import com.viveflores.blogturistico.Repository.ServicioRepository;
import com.viveflores.blogturistico.Repository.FotosRepository;
import com.viveflores.blogturistico.Service.EventoService;
import com.viveflores.blogturistico.Service.PublicacionesService;
import com.viveflores.blogturistico.Service.ServicioService;
import com.viveflores.blogturistico.Service.FotosService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PaginaPrincipalController {

    private final PublicacionesService publicacionesService;
    private final PublicacionesRepository publicacionesRepository;
    private final ServicioRepository servicioRepository;
    private final EventoRepository eventoRepository;
    private final ServicioService servicioService;
    private final EventoService eventoService;
    private final FotosService fotosService;
    private final FotosRepository fotosRepository;

    public PaginaPrincipalController(PublicacionesService publicacionesService, PublicacionesRepository publicacionesRepository, ServicioRepository servicioRepository, EventoRepository eventoRepository, ServicioService servicioService, EventoService eventoService, FotosService fotosService, FotosRepository fotosRepository) {
        this.publicacionesService = publicacionesService;
        this.publicacionesRepository = publicacionesRepository;
        this.servicioRepository = servicioRepository;
        this.eventoRepository = eventoRepository;
        this.servicioService = servicioService;
        this.eventoService = eventoService;
        this.fotosService = fotosService;
        this.fotosRepository = fotosRepository;
    }

    @GetMapping("/")
    public String redirigir(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String paginaPrincipal(Model model){
        List<Publicaciones> activas = publicacionesService.getAllPublicaciones().stream()
                .filter(p -> p.getEstado_publicacion() != null &&
                             (p.getEstado_publicacion().equalsIgnoreCase("activo") ||
                              p.getEstado_publicacion().equalsIgnoreCase("aceptado")))
                .toList();
        model.addAttribute("publi", activas);
        model.addAttribute("servi", servicioService.getAllServicios());
        model.addAttribute("event", eventoService.getAllEventos());
        model.addAttribute("fotos", fotosService.getAllFotos());
        return "index";
    }

    @GetMapping("/publicaciones/foto/{id}")
    public ResponseEntity<byte[]> obtenerFotoPublicacion(@PathVariable("id") Integer id) {
        return publicacionesRepository.findById(id)
                .map(u -> {
                    if (u.getFoto() == null || u.getFoto().length == 0) {
                        return ResponseEntity.notFound().<byte[]>build();
                    }
                    return ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG)
                            .body(u.getFoto());
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/servicios/foto/{id}")
    public ResponseEntity<byte[]> obtenerFotoServicio(@PathVariable("id") Integer id) {
        return servicioRepository.findById(id)
                .map(u -> {
                    if (u.getFoto() == null || u.getFoto().length == 0) {
                        return ResponseEntity.notFound().<byte[]>build();
                    }
                    return ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG)
                            .body(u.getFoto());
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/eventos/foto/{id}")
    public ResponseEntity<byte[]> obtenerFotoEvento(@PathVariable("id") Integer id) {
        return eventoRepository.findById(id)
                .map(u -> {
                    if (u.getFoto() == null || u.getFoto().length == 0) {
                        return ResponseEntity.notFound().<byte[]>build();
                    }
                    return ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG)
                            .body(u.getFoto());
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/fotos/foto/{id}")
    public ResponseEntity<byte[]> obtenerFotoGaleria(@PathVariable("id") Integer id) {
        return fotosRepository.findById(id)
                .map(u -> {
                    if (u.getFoto() == null || u.getFoto().length == 0) {
                        return ResponseEntity.notFound().<byte[]>build();
                    }
                    return ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG)
                            .body(u.getFoto());
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscarNombre")
    public String buscar(@RequestParam("nombre") String nombre, Model model) {
        List<Publicaciones> resultados = publicacionesRepository.buscarPorNombre(nombre);
        List<Publicaciones> resultadosActivas = resultados.stream()
                .filter(p -> p.getEstado_publicacion() != null &&
                             (p.getEstado_publicacion().equalsIgnoreCase("activo") ||
                              p.getEstado_publicacion().equalsIgnoreCase("aceptado")))
                .toList();
        model.addAttribute("busqueda", nombre);
        model.addAttribute("publiEncont", resultadosActivas);
        return "mostrar";
    }
}
