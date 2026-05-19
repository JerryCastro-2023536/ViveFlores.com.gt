package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Fotos;
import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Service.FotosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AlbumFotosController {

    @Autowired
    private FotosService fotosService;

    @GetMapping("/album")
    public String verAlbum(Model model, HttpSession session) {
        List<Fotos> listaFotos = fotosService.getAllFotos();
        model.addAttribute("fotos", listaFotos);
        
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        model.addAttribute("usuarioLogueado", u);
        
        return "album";
    }

    @PostMapping("/album/guardar")
    public String guardarFoto(@RequestParam("titulo_foto") String titulo,
                              @RequestParam("descripcion") String descripcion,
                              @RequestParam("archivo") MultipartFile archivo,
                              HttpSession session) throws IOException {
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u == null) {
            return "redirect:/acceder";
        }

        Fotos foto = new Fotos();
        foto.setTitulo_foto(titulo);
        foto.setDescripcion(descripcion);
        foto.setFecha_creacion(LocalDate.now());
        foto.setId_usuario(u.getId_usuario());

        if (archivo != null && !archivo.isEmpty()) {
            foto.setFoto(archivo.getBytes());
        }

        fotosService.saveFotos(foto);
        return "redirect:/album";
    }

    @GetMapping("/album/eliminar/{id}")
    public String eliminarFoto(@PathVariable Integer id, HttpSession session) {
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u == null) {
            return "redirect:/acceder";
        }

        Fotos foto = fotosService.getFotoById(id);
        if (foto != null) {
            // Permitir eliminar si es el propietario o es administrador
            if (foto.getId_usuario().equals(u.getId_usuario()) || "admin".equalsIgnoreCase(u.getRol())) {
                fotosService.deleteFotos(id);
            }
        }
        return "redirect:/album";
    }

    @GetMapping("/foto/{id}")
    @ResponseBody
    public byte[] obtenerFoto(@PathVariable Integer id) {
        Fotos foto = fotosService.getFotoById(id);
        if (foto == null || foto.getFoto() == null) {
            return new byte[0];
        }
        return foto.getFoto();
    }
}