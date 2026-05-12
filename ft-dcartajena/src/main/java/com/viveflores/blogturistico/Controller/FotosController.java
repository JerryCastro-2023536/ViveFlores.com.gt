package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Fotos;
import com.viveflores.blogturistico.Service.FotosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fotos")
public class FotosController {

    private final FotosService fotosService;

    public FotosController(FotosService fotosService) {
        this.fotosService = fotosService;
    }

    @GetMapping
    public List<Fotos> getAllFotos() {
        return fotosService.getAllFotos();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getFotoById(@PathVariable Integer id) {
        try {
            Fotos fotos = fotosService.getFotoById(id);
            return new ResponseEntity<>(fotos, HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveFotos(@Valid @RequestBody Fotos fotos) {
        try {
            Fotos saveFotos = fotosService.saveFotos(fotos);
            return new ResponseEntity<>(saveFotos, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateFotos(@PathVariable Integer id, @Valid @RequestBody Fotos fotos) {
        try {
            Fotos updateFotos = fotosService.updateFotos(id, fotos);
            return new ResponseEntity<>(updateFotos, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteFotos(@PathVariable Integer id) {
        try {
            fotosService.deleteFotos(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}