package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Service.PublicacionesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/*
@RestController
@RequestMapping("/api/publicaciones")
 */

@Controller
public class PublicacionesController {
    private final PublicacionesService publicacionesService;

    public PublicacionesController(PublicacionesService publicacionesService) {
        this.publicacionesService = publicacionesService;
    }

    @GetMapping("/publicaciones")
    public String mostrarPublicaciones(Model model){
        model.addAttribute("publicaciones", publicacionesService.getAllPublicaciones());
        return "crudPublicaciones";
    }

    @PostMapping("/savePublicacion")
    public String savePublicacion(
            @RequestParam("nombrePublicacion") String nombrePublicacion,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("direccion") String direccion,
            @RequestParam("telefono") Integer telefono,
            @RequestParam("emailPublicacion") String emailPublicacion,
            @RequestParam("horario") String horario,
            @RequestParam("fechaCreacion") LocalDate fechaCreacion,
            @RequestParam("estadoPublicacion") String estadoPublicacion,
            @RequestParam("idCategoria") Integer idCategoria,
            @RequestParam("idUsuario") Integer idUsuario,
            @RequestParam("foto") MultipartFile foto
    ) {

        System.out.println("FILE NAME: " + foto.getOriginalFilename());
        System.out.println("FILE SIZE: " + foto.getSize());
        System.out.println("EMPTY: " + foto.isEmpty());


        Publicaciones p = new Publicaciones();
        p.setNombre_publicacion(nombrePublicacion);
        p.setDescripcion(descripcion);
        p.setDireccion(direccion);
        p.setTelefono(telefono);
        p.setEmail_publicacion(emailPublicacion);
        p.setHorario(horario);
        p.setFecha_creacion(fechaCreacion);
        p.setEstado_publicacion(estadoPublicacion);
        p.setId_categoria(idCategoria);
        p.setId_usuario(idUsuario);

        if (foto != null && !foto.isEmpty()) {
            try {
                p.setFoto(foto.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        publicacionesService.savePublicaciones(p);
        return "redirect:/publicaciones";
    }

    @PostMapping("/updatePublicacion")
    public String updatePublicacion(
            @RequestParam("id") Integer id,
            @RequestParam("nombrePublicacion") String nombrePublicacion,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("direccion") String direccion,
            @RequestParam("telefono") Integer telefono,
            @RequestParam("emailPublicacion") String emailPublicacion,
            @RequestParam("horario") String horario,
            @RequestParam("fechaCreacion") LocalDate fechaCreacion,
            @RequestParam("estadoPublicacion") String estadoPublicacion,
            @RequestParam("idCategoria") Integer idCategoria,
            @RequestParam("idUsuario") Integer idUsuario,
            @RequestParam("foto") MultipartFile foto
    ) {
        Publicaciones p = new Publicaciones();
        p.setNombre_publicacion(nombrePublicacion);
        p.setDescripcion(descripcion);
        p.setDireccion(direccion);
        p.setTelefono(telefono);
        p.setEmail_publicacion(emailPublicacion);
        p.setHorario(horario);
        p.setFecha_creacion(fechaCreacion);
        p.setEstado_publicacion(estadoPublicacion);
        p.setId_categoria(idCategoria);
        p.setId_usuario(idUsuario);

        if (foto != null && !foto.isEmpty()) {
            try {
                p.setFoto(foto.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Publicaciones existing = publicacionesService.getPublicacionesById(id);
            if (existing != null) {
                p.setFoto(existing.getFoto());
            }
        }

        publicacionesService.updatePublicaciones(id, p);
        return "redirect:/publicaciones";
    }

    @PostMapping("/searchPublicacion")
    public String buscarPublicacion(@RequestParam("id") Integer id, Model model){
        Publicaciones p = publicacionesService.getPublicacionesById(id);
        model.addAttribute("publicaciones", List.of(p));
        return "crudPublicaciones";
    }

    @GetMapping("/deletePublicacion/{id}")
    public String deletePublicacion(@PathVariable("id") Integer id){
        publicacionesService.deletePublicaciones(id);
        return "redirect:/publicaciones";
    }

    /*
    @GetMapping
    List<Publicaciones> getAllPublicaciones() {
        return publicacionesService.getAllPublicaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPublicacionesById(@PathVariable Integer id) {
        try {
            Publicaciones buscarPublicaciones = publicacionesService.getPublicacionesById(id);
            return new ResponseEntity<>(buscarPublicaciones, HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> savePublicaciones(@Valid @RequestBody Publicaciones publicaciones) {
        try {
            Publicaciones savedPublicaciones = publicacionesService.savePublicaciones(publicaciones);
            return new ResponseEntity<>(savedPublicaciones, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePublicaciones(@PathVariable Integer id, @Valid @RequestBody Publicaciones publicaciones) {
        try {
            Publicaciones actualizarPublicaciones = publicacionesService.updatePublicaciones(id, publicaciones);
            return new ResponseEntity<>(actualizarPublicaciones, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePublicaciones(@PathVariable Integer id){
        try {
            publicacionesService.deletePublicaciones(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    */
}
