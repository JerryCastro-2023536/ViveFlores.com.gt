package com.viveflores.blogturistico.Controller.View;

import com.viveflores.blogturistico.Entity.Contactar;
import com.viveflores.blogturistico.Service.ContactarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/contactar")
public class ContactarViewController {

    private final ContactarService contactarService;

    public ContactarViewController(ContactarService contactarService) {
        this.contactarService = contactarService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("contactos", contactarService.getAllContactar());
        model.addAttribute("contactar", new Contactar());
        model.addAttribute("modoEdicion", false);
        return "contactar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Contactar contactar) {
        if (contactar.getFecha_envio() == null) {
            contactar.setFecha_envio(LocalDateTime.now());
        }
        contactarService.saveContactar(contactar);
        return "redirect:/contactar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Contactar contactar = contactarService.getContactarById(id);
        model.addAttribute("contactar", contactar);
        model.addAttribute("contactos", contactarService.getAllContactar());
        model.addAttribute("modoEdicion", true);
        return "contactar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute Contactar contactar) {
        contactarService.updateContactar(id, contactar);
        return "redirect:/contactar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        contactarService.deleteContactar(id);
        return "redirect:/contactar";
    }
}
