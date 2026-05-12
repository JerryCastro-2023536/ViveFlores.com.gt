package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Evento;
import com.viveflores.blogturistico.Service.EventoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/eventos")
public class EventoController {
    private final EventoService eventoService;
    public EventoController (EventoService eventoService){
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> getAllEventos(){return eventoService.getAllEventos();}

    @PostMapping
    public ResponseEntity<Object> createEvento(@Valid @RequestBody Evento evento){
        try{
            Evento createdEmpleado = eventoService.saveEvento(evento);
            return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvento(@PathVariable Integer id){
        try {
            eventoService.deleteEvento(id);
            return ResponseEntity.ok("Evento Eliminado Correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvento(@PathVariable Integer id, @RequestBody Evento evento) {

        try {
            Evento actualizado = eventoService.updateEvento(id, evento);
            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
