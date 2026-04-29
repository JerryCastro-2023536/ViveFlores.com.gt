package com.viveflores.blogturistico.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Repository.PublicacionesRepository;
import com.viveflores.blogturistico.Service.PublicacionesService;

@Controller
public class PublicacionesController {
    @Autowired
    PublicacionesService service;

//    @Autowired
//    PublicacionesRepository repo;
//
//    @GetMapping("/")
//    public String inicio() {return "redirect:/publicaciones";}

    @GetMapping("/publicaciones")
    public String listarPublicaciones(@RequestParam(name = "accion", required = false) String accion,
                                 @RequestParam(name = "id", required = false) Integer id,
                                 Model model) {

        model.addAttribute("publicaciones",service.listar());
        model.addAttribute("accion", accion);

        if("editar".equals(accion) && id !=null ){
            model.addAttribute("uEncontrado",service.buscarporId(id));
        }else{
            model.addAttribute("uEcontrado", new Publicaciones());
        }
        return "publicaciones";
    }

     @GetMapping("publicaciones/foto/{id}")
    public ResponseEntity<byte[]> verFoto(@PathVariable int id) {
        Publicaciones p = service.buscarporId(id);
        if (p != null && p.getFoto() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(p.getFoto());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/publicaciones/agregar")
    public String guardar(@ModelAttribute Publicaciones publicacion, 
                      @RequestParam("fotoArchivo") MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            publicacion.setFoto(archivo.getBytes());
        }
        service.guardar(publicacion);
        return "redirect:/publicaciones";
    }

    @PostMapping("/publicaciones/buscar")
    public String buscar(@RequestParam Integer id_publicacion, Model model) {
        if (id_publicacion != null) {
            Publicaciones u = service.buscarporId(id_publicacion);
            if (u != null) {
                model.addAttribute("publicaciones", java.util.List.of(u));
            } else {
                model.addAttribute("publicaciones", service.listar());
                model.addAttribute("error", "No existe ese ID");
            }
        }
        return "publicaciones";
    }


    @PostMapping("/publicaciones/editar")
    public String editar(@ModelAttribute Publicaciones publicaciones,
                     @RequestParam("fotoArchivo") MultipartFile archivo) throws IOException {
    
        if (!archivo.isEmpty()) {
            publicaciones.setFoto(archivo.getBytes());
        } else {
            Publicaciones temp = service.buscarporId(publicaciones.getId_publicacion());
            if (temp != null) {
                publicaciones.setFoto(temp.getFoto());
            }
        }
    
        service.guardar(publicaciones);
        return "redirect:/publicaciones";
    }
    @GetMapping("/publicaciones/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        service.eliminar(id);
        return "redirect:/publicaciones";
    }

    
}
