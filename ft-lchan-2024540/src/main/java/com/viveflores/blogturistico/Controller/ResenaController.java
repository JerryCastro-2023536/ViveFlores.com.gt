package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Resena;
import com.viveflores.blogturistico.Service.ResenaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/resenas")
public class ResenaController {
    private final ResenaService resenaService;

    public ResenaController(ResenaService resenaService){
        this.resenaService = resenaService;
    }

    @GetMapping
    public List<Resena> getAllResenas(){
        return resenaService.getAllResenas();
    }

    @PostMapping
    public ResponseEntity<Object> createResena(@Valid @RequestBody Resena resena){
        try{
            Resena createdResena = resenaService.saveResena(resena);
            return new ResponseEntity<>(createdResena, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResena (@PathVariable Integer id){
        try {
            resenaService.deleteResena(id);
            return ResponseEntity.ok("Resena eliminada correctamente");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> updateResena (@PathVariable Integer id, @RequestBody Resena resena){
        try {
            Resena actualizado = resenaService.updateResena(id, resena);
            return ResponseEntity.ok((actualizado));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
