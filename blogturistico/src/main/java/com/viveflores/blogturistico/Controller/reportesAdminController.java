package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Reportes;
import com.viveflores.blogturistico.Service.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class reportesAdminController {

    @Autowired
    private ReportesService reportesService;

    @GetMapping("/reportes")
    public String index(Model model) {
        List<Reportes> lista = reportesService.listarReportes();
        model.addAttribute("reportes", lista);
        return "reportesAdmin";
    }
    @GetMapping("/reportes/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id) {
        reportesService.eliminarReportes(id);
        return "redirect:/reportes";
    }
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("reporte", new Reportes());
        return "reportesUsuario";
    }
}