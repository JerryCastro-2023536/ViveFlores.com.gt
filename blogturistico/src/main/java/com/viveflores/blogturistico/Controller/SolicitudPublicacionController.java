package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.SolicitudPublicacion;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Service.SolicitudPublicacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitud")

public class SolicitudPublicacionController {
    private final SolicitudPublicacionService solicitudPublicacionService;
    FechasValidar fv = new FechasValidar();

    public SolicitudPublicacionController(SolicitudPublicacionService solicitudPublicacionService){ this.solicitudPublicacionService=solicitudPublicacionService;}

    @GetMapping
    public List<SolicitudPublicacion> getAllSolicitud(){return solicitudPublicacionService.getAllSolicitud();}

    @PostMapping
    public ResponseEntity<Object> createSolicitud(@Valid @RequestBody SolicitudPublicacion solicitudP){
        try{
            SolicitudPublicacion Createdsolicitud= solicitudPublicacionService.saveSolicitud(solicitudP);
            return new ResponseEntity<>(Createdsolicitud,HttpStatus.CREATED);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSolicitud(@PathVariable Integer id, @Valid @RequestBody SolicitudPublicacion solicitud){
        try {
            SolicitudPublicacion solicitudPubli= solicitudPublicacionService.getSolicitudById(id);
            if(solicitudPubli==null) {
                throw new NotFoundExcepcion("El id no existe");
            }
            fv.validarLocalDate(solicitud.getFecha_solicitud());
            solicitud.setId_solicitud(id);

            SolicitudPublicacion updateSolicitud= solicitudPublicacionService.updateSolicitud(id,solicitud);
            return ResponseEntity.ok(updateSolicitud);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSolicitud(@PathVariable Integer id){
        try {

            SolicitudPublicacion solicitudP = solicitudPublicacionService.getSolicitudById(id);

            solicitudPublicacionService.deleteSolicitud(id);
            return ResponseEntity.ok(solicitudP);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSolicitudById(@PathVariable Integer id) {
        try {
            SolicitudPublicacion solicitudP = solicitudPublicacionService.getSolicitudById(id);
            return ResponseEntity.ok(solicitudP);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
