package com.viveflores.blogturistico.Controller.View;

import com.viveflores.blogturistico.Entity.Categorias;
import com.viveflores.blogturistico.Service.CategoriasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriasViewController {

    private final CategoriasService categoriasService;

    public CategoriasViewController(CategoriasService categoriasService) {
        this.categoriasService = categoriasService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriasService.getAllCategorias());
        model.addAttribute("categoria", new Categorias());
        model.addAttribute("modoEdicion", false);
        return "categorias";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Categorias categorias) {
        categoriasService.saveCategorias(categorias);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Categorias categoria = categoriasService.getCategoriaById(id);
        model.addAttribute("categoria", categoria);
        model.addAttribute("categorias", categoriasService.getAllCategorias());
        model.addAttribute("modoEdicion", true);
        return "categorias";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute Categorias categorias) {
        categoriasService.updateCategorias(id, categorias);
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        categoriasService.deleteCategorias(id);
        return "redirect:/categorias";
    }
}
