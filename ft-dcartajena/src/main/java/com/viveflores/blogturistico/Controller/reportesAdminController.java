package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Reportes;
import com.viveflores.blogturistico.Service.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class reportesAdminController {

    @Autowired
    private ReportesService reportesService;

    // ESTO HACE QUE localhost:8080 CARGUE LA LISTA DIRECTAMENTE
    @GetMapping("/")
    public String index(Model model) {
        List<Reportes> lista = reportesService.listarReportes();
        model.addAttribute("reportes", lista);
        return "reportesAdmin";
    }

    // Ruta para eliminar: localhost:8080/eliminar/5
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id) {
        reportesService.eliminarReportes(id);
        return "redirect:/";
    }

    // Ruta para el formulario de usuario: localhost:8080/nuevo
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("reporte", new Reportes());
        return "reportesUsuario";
    }

    // Ruta para guardar: localhost:8080/guardar
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("reporte") Reportes reporte) {
        reporte.setFecha_envio(LocalDateTime.now());
        if (reporte.getId_usuario() == null) reporte.setId_usuario(1);
        reportesService.agregarReportes(reporte);
        return "redirect:/?exito=true";
    }
    @GetMapping("/buscar")
    public String buscar(@RequestParam("keyword") String keyword, Model model) {
        // Por ahora refresca la lista, pero ya no dará error 404
        List<Reportes> lista = reportesService.listarReportes();
        model.addAttribute("reportes", lista);
        return "reportesAdmin";
    }
}