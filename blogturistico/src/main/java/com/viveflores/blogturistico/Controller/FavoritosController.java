package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Favoritos;
import com.viveflores.blogturistico.Service.FavoritosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@RestController
@RequestMapping("/api/favoritos")
 */

@Controller
public class FavoritosController {

    private final FavoritosService favoritosService;

    public FavoritosController(FavoritosService favoritosService) {
        this.favoritosService = favoritosService;
    }

    @GetMapping("/favoritos")
    public String mostrarFavoritos(Model model){
        model.addAttribute("favoritos", favoritosService.getFavoritosAll());
        return "crudFavoritos";
    }

    @PostMapping("/saveFavorito")
    public String saveFavorito(
            @RequestParam("idUsuario") Integer idUsuario,
            @RequestParam("idPublicacion") Integer idPublicacion,
            @RequestParam("idCategoria") Integer idCategoria
    ) {
        Favoritos f = new Favoritos();
        f.setId_usuario(idUsuario);
        f.setId_publicacion(idPublicacion);
        f.setId_categoria(idCategoria);

        favoritosService.saveFavoritos(f);
        return "redirect:/favoritos";
    }

    @PostMapping("/updateFavorito")
    public String updateFavorito(
            @RequestParam("id") Integer id,
            @RequestParam("idUsuario") Integer idUsuario,
            @RequestParam("idPublicacion") Integer idPublicacion,
            @RequestParam("idCategoria") Integer idCategoria
    ) {
        Favoritos f = new Favoritos();
        f.setId_usuario(idUsuario);
        f.setId_publicacion(idPublicacion);
        f.setId_categoria(idCategoria);

        favoritosService.updateFavoritos(id, f);
        return "redirect:/favoritos";
    }

    @PostMapping("/searchFavorito")
    public String buscarFavorito(@RequestParam("id") Integer id, Model model){
        Favoritos f = favoritosService.getFavoritosById(id);
        model.addAttribute("favoritos", List.of(f));
        return "crudFavoritos";
    }

    @GetMapping("/deleteFavorito/{id}")
    public String deleteFavorito(@PathVariable("id") Integer id){
        favoritosService.deleteFavoritos(id);
        return "redirect:/favoritos";
    }

    /*
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
    */
}
