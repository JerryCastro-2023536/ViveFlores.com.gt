package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Reportes;
import com.viveflores.blogturistico.Service.ReportesService;
import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Repository.ReportesRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReportesController {

    @Autowired
    private ReportesService service;

    @Autowired
    private ReportesRepository repo;

    // ==========================================
    // USUARIO NORMAL
    // ==========================================
    @GetMapping("/mis-reportes")
    public String misReportes(Model model, HttpSession session) {
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u == null) return "redirect:/acceder";

        List<Reportes> reportes = repo.findByIdUsuario(u.getId_usuario());
        model.addAttribute("listaReportes", reportes);
        return "misReportes";
    }

    @PostMapping("/reportes/enviar")
    public String enviarReporte(@RequestParam("asunto") String asunto, 
                                @RequestParam("mensaje") String mensaje,
                                HttpSession session) {
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u == null) return "redirect:/acceder";

        Reportes reporte = new Reportes();
        reporte.setAsunto(asunto);
        reporte.setMensaje(mensaje);
        reporte.setId_usuario(u.getId_usuario());
        reporte.setFecha_envio(java.time.LocalDateTime.now());
        service.agregarReportes(reporte);

        return "redirect:/mis-reportes";
    }

    // ==========================================
    // ADMINISTRADOR
    // ==========================================
    @GetMapping("/admin-reportes")
    public String adminReportes(Model model, HttpSession session) {
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u == null || !u.getRol().equalsIgnoreCase("admin")) return "redirect:/acceder";

        model.addAttribute("listaReportes", service.listarReportes());
        return "adminReportes";
    }

    @PostMapping("/reportes/responder/{id}")
    public String responderReporte(@PathVariable Integer id, @RequestParam("respuesta") String respuesta, HttpSession session) {
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u == null || !u.getRol().equalsIgnoreCase("admin")) return "redirect:/acceder";

        Reportes reporte = service.BuscarPorId(id);
        if (reporte != null) {
            reporte.setRespuesta(respuesta);
            service.agregarReportes(reporte); // save
        }
        return "redirect:/admin-reportes";
    }

    @PostMapping("/reportes/eliminar/{id}")
    public String eliminarReporte(@PathVariable Integer id, HttpSession session) {
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u == null || !u.getRol().equalsIgnoreCase("admin")) return "redirect:/acceder";

        service.eliminarReportes(id);
        return "redirect:/admin-reportes";
    }
}
