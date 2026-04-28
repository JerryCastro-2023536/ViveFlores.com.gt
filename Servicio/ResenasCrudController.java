package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Resena;
import com.viveflores.blogturistico.Service.ResenaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/resenascrud")
public class ResenasCrudController {

    private final ResenaService resenaService;

    public ResenasCrudController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("resenas", resenaService.getAllResenas());
        model.addAttribute("resena", new Resena());

        return "resenasCrud";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Resena resena) {

        try {

            if (resena.getId_resena() == null) {
                resena.setFecha_creacion(LocalDate.now());
            }

            resenaService.saveResena(resena);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/resenascrud";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        Resena resenaEdit = resenaService.getResenaById(id);

        model.addAttribute("resena", resenaEdit);
        model.addAttribute("resenas", resenaService.getAllResenas());

        return "resenasCrud";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        resenaService.deleteResena(id);

        return "redirect:/resenascrud";
    }
}
