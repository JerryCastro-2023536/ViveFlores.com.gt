package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Reportes;
import com.viveflores.blogturistico.Service.ReportesService;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {
    private final ReportesService reportesService;

    public ReportesController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }

    @GetMapping
    public List<Reportes> getAllReportes(){
        return reportesService.getAllReportes();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getReportesById(@PathVariable Integer id){
        try{
            Reportes reportes = reportesService.getReporteById(id);
            return new ResponseEntity<>(reportes, HttpStatus.FOUND);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveReportes(@Valid @RequestBody Reportes reportes){
        try{
            Reportes saveReportes = reportesService.saveReportes(reportes);
            return new ResponseEntity<>(saveReportes, HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateReportes(@PathVariable Integer id, @Valid @RequestBody Reportes reportes, ServletRequest servletRequest){
        try{
            Reportes updateReportes = reportesService.updateReportes(id, reportes);
            return new ResponseEntity<>(updateReportes, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteReportes(@PathVariable Integer id){
        try{
            reportesService.deleteReportes(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
