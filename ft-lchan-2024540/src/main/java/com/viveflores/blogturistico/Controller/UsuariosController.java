package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Exception.CorreoValidar;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Exception.Validation;
import com.viveflores.blogturistico.Service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {this.usuariosService = usuariosService;}

    @GetMapping
    public List<Usuarios> getAllUsuarios(){return usuariosService.getAllUsuarios();}

    @PostMapping
    public ResponseEntity<Object> createUsuarios(@Valid @RequestBody Usuarios Usuarios){
        try {
            Usuarios createdUsuarios= usuariosService.saveUsuarios(Usuarios);
            return new ResponseEntity<>(createdUsuarios, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuarios(@PathVariable Integer id, @Valid @RequestBody Usuarios Usuarios){
        try{
            Usuarios usuarios= usuariosService.getUsuariosById(id);
            Validation v = new Validation();
            FechasValidar fv = new FechasValidar();
            CorreoValidar cv = new CorreoValidar();

            if(usuarios==null){
                throw new NotFoundExcepcion("El id no existe");
            }

            v.validarRol(usuarios.getRol());
            fv.validarLocalDate(usuarios.getFecha_registro());
            cv.formatoCorreo(usuarios.getEmail_usuario());

            usuarios.setId_usuario(id);

            Usuarios updateUsuarios= usuariosService.updateUsuarios(id,usuarios);
            return ResponseEntity.ok(updateUsuarios);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuarios(@PathVariable Integer id){
        try{
            Usuarios usuarios=usuariosService.getUsuariosById(id);
            usuariosService.deleteUsuarios(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuariosById(@PathVariable Integer id){
        try {
            Usuarios usuarios= usuariosService.getUsuariosById(id);
            return ResponseEntity.ok(usuarios);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}