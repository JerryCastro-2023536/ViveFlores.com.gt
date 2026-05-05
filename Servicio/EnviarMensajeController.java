package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Contactar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/enviarmensaje")
public class EnviarMensajeController {

    @GetMapping
    public String mostrarMensaje(Model model) {
        model.addAttribute("contactar", new Contactar());
        return "enviarmensaje";
    }

    @PostMapping("/guardar")
    public String guardarMensaje(@ModelAttribute Contactar contactar) {

        System.out.println("Asunto: " + contactar.getAsunto());
        System.out.println("Mensaje: " + contactar.getMensaje());

        return "redirect:/enviarmensaje";
    }
}