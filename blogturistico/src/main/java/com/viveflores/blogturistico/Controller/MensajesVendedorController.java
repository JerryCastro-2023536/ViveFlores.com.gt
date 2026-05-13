package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Repository.ContactarRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
