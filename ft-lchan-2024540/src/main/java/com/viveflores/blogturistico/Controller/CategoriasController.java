package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Categorias;
import com.viveflores.blogturistico.Service.CategoriasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriasController {

    private final CategoriasService categoriasService;

    public CategoriasController(CategoriasService categoriasService) {
        this.categoriasService = categoriasService;
    }

    @GetMapping
    public List<Categorias> getAllCategorias() {
        return categoriasService.getAllCategorias();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getCategoriaById(@PathVariable Integer id) {
        try {
            Categorias categorias = categoriasService.getCategoriaById(id);
            return new ResponseEntity<>(categorias, HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveCategorias(@Valid @RequestBody Categorias categorias) {
        try {
            Categorias saveCategorias = categoriasService.saveCategorias(categorias);
            return new ResponseEntity<>(saveCategorias, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCategorias(@PathVariable Integer id, @Valid @RequestBody Categorias categorias) {
        try {
            Categorias updateCategorias = categoriasService.updateCategorias(id, categorias);
            return new ResponseEntity<>(updateCategorias, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategorias(@PathVariable Integer id) {
        try {
            categoriasService.deleteCategorias(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}