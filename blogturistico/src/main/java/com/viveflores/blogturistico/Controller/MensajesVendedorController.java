package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Repository.ContactarRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MensajesVendedorController {

    private final ContactarRepository contactarRepository;

    public MensajesVendedorController(ContactarRepository contactarRepository) {
        this.contactarRepository = contactarRepository;
    }

    @GetMapping("/mensajes")
    public String Mensajes(Model model, HttpSession session){
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u != null) {
            List<Object[]> mensajes = contactarRepository.findMensajesYPublicacionPorVendedor(u.getId_usuario());
            model.addAttribute("listaContactos", mensajes);
        }
        return "mensajesVendedor";
    }
    @PostMapping("/mensajes/responder/{id}")
    public String responderMensaje(@PathVariable Integer id, @RequestParam("respuesta") String respuesta) {
        com.viveflores.blogturistico.Entity.Contactar contactar = contactarRepository.findById(id).orElse(null);
        if (contactar != null) {
            contactar.setRespuesta(respuesta);
            contactarRepository.save(contactar);
        }
        return "redirect:/mensajes";
    }

    @PostMapping("/mensajes/eliminar/{id}")
    public String eliminarMensaje(@PathVariable Integer id) {
        contactarRepository.deleteById(id);
        return "redirect:/mensajes";
    }
}
