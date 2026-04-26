package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Reportes;
import com.viveflores.blogturistico.Service.ReportesService;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
/*
@RestController
@RequestMapping("/api/reportes")
 */

@Controller
public class ReportesController {
    private final ReportesService reportesService;

    public ReportesController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }

    @GetMapping("reportes")
    public String mostrarReportes(Model model){
        model.addAttribute("reportes", reportesService.getAllReportes());
        return "crudReportes";
    }

    @PostMapping("/saveReporte")
    public String saveReporte(@RequestParam("asunto") String asunto,
                              @RequestParam("mensaje") String mensaje,
                              @RequestParam("fecha") LocalDateTime fecha,
                              @RequestParam("idUsuario") Integer idUsuario){
        Reportes r = new Reportes();

        r.setAsunto(asunto);
        r.setMensaje(mensaje);
        r.setFecha_envio(fecha);
        r.setId_usuario(idUsuario);

        reportesService.saveReportes(r);
        return "redirect:/reportes";

    }

    @PostMapping("/updateReporte")
    public String updateReporte(@RequestParam("id") Integer id,
                                @RequestParam("asunto") String asunto,
                                @RequestParam("mensaje") String mensaje,
                                @RequestParam("fecha") LocalDateTime fecha,
                                @RequestParam("idUsuario") Integer idUsuario){
        Reportes r = new Reportes();

        r.setAsunto(asunto);
        r.setMensaje(mensaje);
        r.setFecha_envio(fecha);
        r.setId_usuario(idUsuario);

        reportesService.updateReportes(id, r);
        return "redirect:/reportes";
    }

    @PostMapping("/searchReporte")
    public String buscarReporte(@RequestParam("id") Integer id, Model model){
        Reportes r = reportesService.getReporteById(id);
        model.addAttribute("reportes", List.of(r));
        return "crudReportes";
    }

    @GetMapping("/deleteReporte/{id}")
    public String deleteReporte(@PathVariable("id") Integer id){
        reportesService.deleteReportes(id);
        return "redirect:/reportes";
    }

    /*
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
    */
}
