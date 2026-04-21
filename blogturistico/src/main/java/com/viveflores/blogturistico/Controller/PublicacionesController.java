package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Service.PublicacionesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionesController {
    private final PublicacionesService publicacionesService;

    public PublicacionesController(PublicacionesService publicacionesService) {
        this.publicacionesService = publicacionesService;
    }

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
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
