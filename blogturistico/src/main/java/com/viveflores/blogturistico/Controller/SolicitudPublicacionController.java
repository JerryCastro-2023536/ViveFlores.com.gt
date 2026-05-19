package com.viveflores.blogturistico.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.viveflores.blogturistico.Entity.SolicitudPublicacion;
import com.viveflores.blogturistico.Service.SolicitudPublicacionService;

@Controller
public class SolicitudPublicacionController {
    @Autowired
    private SolicitudPublicacionService service;

    @Autowired
    private com.viveflores.blogturistico.Service.PublicacionesService publicacionesService;

    @GetMapping("/solicitudPublicacion/aprobar/{id}")
    public String aprobar(@PathVariable int id) {
        SolicitudPublicacion sol = service.buscarPorId(id);
        if (sol != null) {
            sol.setEstado("Aprobada");
            service.guardar(sol);
            if (sol.getId_publicacion() != null) {
                com.viveflores.blogturistico.Entity.Publicaciones pub = publicacionesService.getPublicacionesById(sol.getId_publicacion());
                if (pub != null) {
                    pub.setEstado_publicacion("activo");
                    publicacionesService.savePublicaciones(pub);
                }
            }
        }
        return "redirect:/solicitudPublicacion";
    }

    @GetMapping("/solicitudPublicacion/rechazar/{id}")
    public String rechazar(@PathVariable int id) {
        SolicitudPublicacion sol = service.buscarPorId(id);
        if (sol != null) {
            sol.setEstado("Rechazada");
            service.guardar(sol);
            if (sol.getId_publicacion() != null) {
                com.viveflores.blogturistico.Entity.Publicaciones pub = publicacionesService.getPublicacionesById(sol.getId_publicacion());
                if (pub != null) {
                    pub.setEstado_publicacion("rechazado");
                    publicacionesService.savePublicaciones(pub);
                }
            }
        }
        return "redirect:/solicitudPublicacion";
    }

    @GetMapping("/solicitudPublicacion")
    public String listarsolicitudPublicacion(@RequestParam(name = "accion", required = false) String accion,
                                 @RequestParam(name = "id", required = false) Integer id,
                                 Model model) {

        model.addAttribute("solicitudPublicacion",service.listar());
        model.addAttribute("accion", accion);

        if("editar".equals(accion) && id !=null ){
            model.addAttribute("uEncontrado",service.buscarPorId(id));
        }else{
            model.addAttribute("uEcontrado", new SolicitudPublicacion());
        }
        return "solicitudPublicacion";
    }

    @PostMapping("/solicitudPublicacion/agregar")
    public String agregar(@ModelAttribute SolicitudPublicacion solicitudPublicacion) {
        service.guardar(solicitudPublicacion);
        return "redirect:/solicitudPublicacion";
    }

    @PostMapping("/solicitudPublicacion/buscar")
    public String buscar(@RequestParam Integer id_solicitud, Model model) {
        if (id_solicitud != null) {
            SolicitudPublicacion u = service.buscarPorId(id_solicitud);
            if (u != null) {
                model.addAttribute("solicitudPublicacion", java.util.List.of(u));
            } else {
                model.addAttribute("solicitudPublicacion", service.listar());
                model.addAttribute("error", "No existe ese ID");
            }
        }
        return "solicitudPublicacion";
    }


    @PostMapping("/solicitudPublicacion/editar")
    public String editar(@ModelAttribute SolicitudPublicacion solicitudPublicacion) {
        service.guardar(solicitudPublicacion);
        return "redirect:/solicitudPublicacion";
    }

    @GetMapping("/solicitudPublicacion/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        service.eliminar(id);
        return "redirect:/solicitudPublicacion";
    }


}
