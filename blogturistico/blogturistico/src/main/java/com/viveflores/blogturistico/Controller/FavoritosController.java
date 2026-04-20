package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Favoritos;
import com.viveflores.blogturistico.Service.FavoritosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritosController {

    private final FavoritosService favoritosService;

    public FavoritosController(FavoritosService favoritosService) {
        this.favoritosService = favoritosService;
    }

    @GetMapping
    List<Favoritos> getAllFavoritos(){
        return favoritosService.getFavoritosAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getFavoritosById(@PathVariable Integer id){
        try{
            Favoritos buscarFavorito = favoritosService.getFavoritosById(id);
            return new ResponseEntity<>(buscarFavorito, HttpStatus.FOUND);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveFavoritos(@Valid @RequestBody Favoritos favoritos){
        try{
            Favoritos guardarFavoritos = favoritosService.saveFavoritos(favoritos);
            return new ResponseEntity<>(guardarFavoritos, HttpStatus.CREATED);
        }catch(Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateFavoritos(@PathVariable Integer id, @Valid @RequestBody Favoritos favoritos){
        try{
            Favoritos actualizarFavoritos = favoritosService.updateFavoritos(id, favoritos);
            return new ResponseEntity<>(actualizarFavoritos, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteFavoritos(@PathVariable Integer id){
        try{
            favoritosService.deleteFavoritos(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
