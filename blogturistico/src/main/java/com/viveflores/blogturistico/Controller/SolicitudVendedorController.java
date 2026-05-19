package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.SolicitudVendedor;
import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Service.SolicitudVendedorService;
import com.viveflores.blogturistico.Service.UsuariosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SolicitudVendedorController {

    @Autowired
    private SolicitudVendedorService service;

    @Autowired
    private UsuariosService usuariosService;

    // USUARIO SOLICITA SER VENDEDOR
    @GetMapping("/vendedor")
    public String solicitarSerVendedor(HttpSession session, Model model) {
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u == null) {
            return "redirect:/acceder";
        }

        // Check if there is already a pending request
        SolicitudVendedor existente = service.buscarPorIdUsuario(u.getId_usuario());
        if (existente != null) {
            if ("pendiente".equalsIgnoreCase(existente.getEstado())) {
                model.addAttribute("infoMessage", "Ya tienes una solicitud de vendedor pendiente de aprobación.");
                return "redirect:/index";
            } else if ("aprobada".equalsIgnoreCase(existente.getEstado())) {
                model.addAttribute("infoMessage", "Tu solicitud ya ha sido aprobada.");
                return "redirect:/index";
            }
        }

        // Create new request
        SolicitudVendedor solicitud = new SolicitudVendedor();
        solicitud.setId_usuario(u.getId_usuario());
        solicitud.setFecha_solicitud(LocalDate.now());
        solicitud.setEstado("pendiente");
        solicitud.setDescripcion("El usuario " + u.getUsername() + " (" + u.getNombre_usuario() + " " + u.getApellido_usuario() + ") solicita ser convertido en Vendedor.");

        service.guardar(solicitud);

        model.addAttribute("successMessage", "¡Solicitud enviada exitosamente! El administrador revisará tu perfil pronto.");
        return "redirect:/index";
    }

    // VISTA ADMIN DE SOLICITUDES DE VENDEDOR
    @GetMapping("/solicitudVendedor")
    public String listarSolicitudesVendedor(Model model, HttpSession session) {
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u == null || !u.getRol().equalsIgnoreCase("admin")) {
            return "redirect:/acceder";
        }

        List<SolicitudVendedor> solicitudes = service.listar();
        model.addAttribute("solicitudes", solicitudes);

        // We can pass user info helper if needed, but we can do it dynamically or display in description
        return "solicitudVendedor";
    }

    // APROBAR SOLICITUD VENDEDOR
    @GetMapping("/solicitudVendedor/aprobar/{id}")
    public String aprobarSolicitudVendedor(@PathVariable int id, HttpSession session) {
        Usuarios admin = (Usuarios) session.getAttribute("usuarioLogueado");
        if (admin == null || !admin.getRol().equalsIgnoreCase("admin")) {
            return "redirect:/acceder";
        }

        SolicitudVendedor sol = service.buscarPorId(id);
        if (sol != null) {
            sol.setEstado("Aprobada");
            service.guardar(sol);

            // Change user role
            Usuarios usuario = usuariosService.getUsuariosById(sol.getId_usuario());
            if (usuario != null) {
                usuario.setRol("vendedor");
                usuariosService.saveUsuarios(usuario);
            }
        }
        return "redirect:/solicitudVendedor";
    }

    // RECHAZAR SOLICITUD VENDEDOR
    @GetMapping("/solicitudVendedor/rechazar/{id}")
    public String rechazarSolicitudVendedor(@PathVariable int id, HttpSession session) {
        Usuarios admin = (Usuarios) session.getAttribute("usuarioLogueado");
        if (admin == null || !admin.getRol().equalsIgnoreCase("admin")) {
            return "redirect:/acceder";
        }

        SolicitudVendedor sol = service.buscarPorId(id);
        if (sol != null) {
            sol.setEstado("Rechazada");
            service.guardar(sol);
        }
        return "redirect:/solicitudVendedor";
    }

    // ELIMINAR REGISTRO DE SOLICITUD VENDEDOR
    @GetMapping("/solicitudVendedor/eliminar/{id}")
    public String eliminarSolicitudVendedor(@PathVariable int id, HttpSession session) {
        Usuarios admin = (Usuarios) session.getAttribute("usuarioLogueado");
        if (admin == null || !admin.getRol().equalsIgnoreCase("admin")) {
            return "redirect:/acceder";
        }

        service.eliminar(id);
        return "redirect:/solicitudVendedor";
    }
}
