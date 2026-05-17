package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Service.CategoriasService;
import com.viveflores.blogturistico.Service.PublicacionesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import com.viveflores.blogturistico.Entity.Usuarios;

import java.time.LocalDate;

@Controller
@RequestMapping("/adminpublicaciones")
public class AdministrarPublicacionController {

    private final PublicacionesService publicacionesService;
    private final CategoriasService categoriasService;

    public AdministrarPublicacionController(PublicacionesService publicacionesService, CategoriasService categoriasService) {
        this.publicacionesService = publicacionesService;
        this.categoriasService = categoriasService;
    }

    // LISTAR
    @GetMapping
    public String listar(Model model, HttpSession session) {
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u != null) {
            model.addAttribute("publicaciones", publicacionesService.getPublicacionesByUsuarioId(u.getId_usuario()));
        } else {
            model.addAttribute("publicaciones", publicacionesService.getAllPublicaciones());
        }
        model.addAttribute("categorias", categoriasService.getAllCategorias());
        model.addAttribute("publicacion", new Publicaciones());
        return "administrarPublicaciones";
    }

    // GUARDAR (crear y editar) - Admin Legacy
    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Publicaciones publicacion,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            HttpSession session
    ) {
        try {

            Publicaciones existente = null;

            if (publicacion.getId_publicacion() != null) {
                existente = publicacionesService.getPublicacionesById(publicacion.getId_publicacion());

                // Mantener fecha
                publicacion.setFecha_creacion(existente.getFecha_creacion());
                
                // Mantener usuario
                publicacion.setId_usuario(existente.getId_usuario());
                
                // Mantener estado
                publicacion.setEstado_publicacion(existente.getEstado_publicacion());

                // Mantener imagen si no sube nueva
                if (archivo == null || archivo.isEmpty()) {
                    publicacion.setFoto(existente.getFoto());
                } else {
                    publicacion.setFoto(archivo.getBytes());
                }

            } else {
                // NUEVA PUBLICACIÓN
                publicacion.setFecha_creacion(LocalDate.now());
                publicacion.setEstado_publicacion("pendiente");
                
                Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
                if (u != null) {
                    publicacion.setId_usuario(u.getId_usuario());
                } else {
                    publicacion.setId_usuario(1); // Default safe value
                }

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