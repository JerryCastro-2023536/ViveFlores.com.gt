package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Service.CategoriasService;
import com.viveflores.blogturistico.Service.PublicacionesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
public class SubirPublicacionController {

    private final PublicacionesService publicacionesService;
    private final CategoriasService categoriasService;

    public SubirPublicacionController(PublicacionesService publicacionesService, CategoriasService categoriasService) {
        this.publicacionesService = publicacionesService;
        this.categoriasService = categoriasService;
    }

    @GetMapping("/subir")
    public String subirPublicacion(Model model) {
        model.addAttribute("categorias", categoriasService.getAllCategorias());
        return "subirPublicacion";
    }

    @GetMapping("/vendedor/subir")
    public String subirPublicacionVendedor(Model model) {
        model.addAttribute("categorias", categoriasService.getAllCategorias());
        return "subirPublicacion";
    }

    @PostMapping("/vendedor/publicaciones/guardar")
    public String guardarPublicacionVendedor(
            @ModelAttribute Publicaciones publicacion,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            HttpSession session) {
        try {
            publicacion.setFecha_creacion(LocalDate.now());
            publicacion.setEstado_publicacion("pendiente");

            Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
            if (u != null) {
                publicacion.setId_usuario(u.getId_usuario());
            } else {
                return "redirect:/acceder";
            }

            if (archivo != null && !archivo.isEmpty()) {
                publicacion.setFoto(archivo.getBytes());
            }

            publicacionesService.savePublicaciones(publicacion);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/panelvendedor";
    }

    @GetMapping("/vendedor/publicaciones/datos/{id}")
    @ResponseBody
    public ResponseEntity<Publicaciones> obtenerDatosPublicacionVendedor(@PathVariable Integer id) {
        Publicaciones p = publicacionesService.getPublicacionesById(id);
        p.setFoto(null);
        return ResponseEntity.ok(p);
    }

    @PostMapping("/vendedor/publicaciones/editar")
    public String editarPublicacionVendedor(
            @ModelAttribute Publicaciones publicacion,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo) {
        try {
            Publicaciones existente = publicacionesService.getPublicacionesById(publicacion.getId_publicacion());

            publicacion.setFecha_creacion(existente.getFecha_creacion());
            publicacion.setId_usuario(existente.getId_usuario());
            publicacion.setEstado_publicacion("pendiente");

            if (archivo == null || archivo.isEmpty()) {
                publicacion.setFoto(existente.getFoto());
            } else {
                publicacion.setFoto(archivo.getBytes());
            }

            publicacionesService.savePublicaciones(publicacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/adminpublicaciones";
    }

    @PostMapping("/publicaciones/guardar")
    public String guardarPublicacionVendedorLegacy(
            @ModelAttribute Publicaciones publicacion,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            HttpSession session) {
        try {
            publicacion.setFecha_creacion(LocalDate.now());
            publicacion.setEstado_publicacion("pendiente");
            Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
            if (u != null) {
                publicacion.setId_usuario(u.getId_usuario());
            } else {
                publicacion.setId_usuario(1);
            }

            if (archivo != null && !archivo.isEmpty()) {
                publicacion.setFoto(archivo.getBytes());
            }

            publicacionesService.savePublicaciones(publicacion);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/panelvendedor";
    }
}
