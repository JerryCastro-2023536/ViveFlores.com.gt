package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Servicio;
import com.viveflores.blogturistico.Service.ServicioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/servicios")
public class ServicioController {
    private final ServicioService servicioService;
    public ServicioController (ServicioService servicioService){
        this.servicioService = servicioService;
    }

    @GetMapping
    public List<Servicio> getAllServicios(){return servicioService.getAllServicios();}

    @PostMapping
    public ResponseEntity<Object> createServicio(@Valid @RequestBody Servicio servicio){
        try{
            Servicio createdServicio = servicioService.saveServicio(servicio);
            return new ResponseEntity<>(createdServicio, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServicio(@PathVariable Integer id){
        try {
            servicioService.deleteServicio(id);
            return ResponseEntity.ok("Servicio Eliminado Correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateServicio(@PathVariable Integer id, @RequestBody Servicio servicio) {

        try {
            Servicio actualizado = servicioService.updateServicio(id, servicio);
            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
