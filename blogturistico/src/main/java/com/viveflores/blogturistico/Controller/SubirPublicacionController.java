package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Service.PublicacionesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
public class SubirPublicacionController {

    private final PublicacionesService publicacionesService;

    public SubirPublicacionController(PublicacionesService publicacionesService) {
        this.publicacionesService = publicacionesService;
    }

    @GetMapping("/subir")
    public String subirPublicacion(){
        return "subirPublicacion";
    }

    @PostMapping("/publicaciones/guardar")
    public String guardarPublicacionVendedor(
            @ModelAttribute Publicaciones publicacion,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            HttpSession session
    ) {
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

        return "redirect:/subir";
    }
}
