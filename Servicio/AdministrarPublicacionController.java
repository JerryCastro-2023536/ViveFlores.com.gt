package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Service.PublicacionesService;
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

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Publicaciones publicacion,
            @RequestParam("archivo") MultipartFile archivo
    ) {
        try {

            if (!archivo.isEmpty()) {
                publicacion.setFoto(archivo.getBytes());
            }

            // Solo si es nueva
            if (publicacion.getId_publicacion() == null) {
                publicacion.setFecha_creacion(LocalDate.now());
                publicacion.setEstado_publicacion("activo");
            }

            publicacionesService.savePublicaciones(publicacion);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/mis-publicaciones";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        Publicaciones publiEdit = publicacionesService.getPublicacionesById(id);

        model.addAttribute("publicacion", publiEdit);
        model.addAttribute("publicaciones", publicacionesService.getAllPublicaciones());

        return "administrarPublicaciones";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        publicacionesService.deletePublicaciones(id);

        return "redirect:/mis-publicaciones";
    }

    @GetMapping("/imagen/{id}")
    @ResponseBody
    public byte[] obtenerImagen(@PathVariable Integer id) {

        Publicaciones p = publicacionesService.getPublicacionesById(id);

        return p.getFoto();
    }
}