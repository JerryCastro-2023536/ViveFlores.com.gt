package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Service.PublicacionesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
@RequestMapping("/adminpublicaciones")
public class AdministrarPublicacionController {

    private final PublicacionesService publicacionesService;

    public AdministrarPublicacionController(PublicacionesService publicacionesService) {
        this.publicacionesService = publicacionesService;
    }

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("publicaciones", publicacionesService.getAllPublicaciones());
        model.addAttribute("publicacion", new Publicaciones());
        return "administrarPublicaciones";
    }

    // GUARDAR (crear y editar)
    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Publicaciones publicacion,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo
    ) {
        try {

            Publicaciones existente = null;

            if (publicacion.getId_publicacion() != null) {
                existente = publicacionesService.getPublicacionesById(publicacion.getId_publicacion());

                // Mantener fecha
                publicacion.setFecha_creacion(existente.getFecha_creacion());

                // Mantener imagen si no sube nueva
                if (archivo == null || archivo.isEmpty()) {
                    publicacion.setFoto(existente.getFoto());
                } else {
                    publicacion.setFoto(archivo.getBytes());
                }

            } else {
                // NUEVA PUBLICACIÓN
                publicacion.setFecha_creacion(LocalDate.now());
                publicacion.setEstado_publicacion("activo");

                if (archivo != null && !archivo.isEmpty()) {
                    publicacion.setFoto(archivo.getBytes());
                }
            }

            publicacionesService.savePublicaciones(publicacion);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/adminpublicaciones";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        Publicaciones pub = publicacionesService.getPublicacionesById(id);
        model.addAttribute("publicacion", pub);

        return "editarPublicacion";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        publicacionesService.deletePublicaciones(id);
        return "redirect:/adminpublicaciones";
    }

    // IMAGEN
    @GetMapping("/imagen/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable Integer id) {

        Publicaciones p = publicacionesService.getPublicacionesById(id);

        if (p.getFoto() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .body(p.getFoto());
    }
}