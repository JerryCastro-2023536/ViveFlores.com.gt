package com.viveflores.blogturistico.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Service.UsuariosService;




@Controller
public class UsuariosCrudController {
    @Autowired
    private UsuariosService service;

     @GetMapping("/usuarios")
     public String listarUsuarios(@RequestParam(name = "accion", required = false) String accion,
                                  @RequestParam(name = "id", required = false) Integer id,
                                  Model model) {

         model.addAttribute("usuarios",service.getAllUsuarios());
         model.addAttribute("accion", accion);

         if("editar".equals(accion) && id !=null ){
             model.addAttribute("uEncontrado",service.getUsuariosById(id));
         }else{
             model.addAttribute("uEcontrado", new Usuarios());
         }
         return "usuarios";
     }

    @PostMapping("/usuarios/agregar")
    public String agregar(@ModelAttribute Usuarios usuarios) {
        service.saveUsuarios(usuarios);
        return "redirect:/usuarios";
    }

    @PostMapping("/usuarios/buscar")
    public String buscar(@RequestParam Integer id_usuario, Model model) {
        if (id_usuario != null) {
            Usuarios u = service.getUsuariosById(id_usuario);
            if (u != null) {
                model.addAttribute("usuarios", java.util.List.of(u));
            } else {
                model.addAttribute("usuarios", service.getAllUsuarios());
                model.addAttribute("error", "No existe ese ID");
            }
        }
        return "usuarios";
    }


    @PostMapping("/usuarios/editar")
    public String editar(@ModelAttribute Usuarios usuarios) {
        service.saveUsuarios(usuarios);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        service.deleteUsuarios(id);
        return "redirect:/usuarios";
    }
}