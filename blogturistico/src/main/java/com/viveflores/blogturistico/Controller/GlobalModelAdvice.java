package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Categorias;
import com.viveflores.blogturistico.Service.CategoriasService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalModelAdvice {

    private final CategoriasService categoriasService;

    public GlobalModelAdvice(CategoriasService categoriasService) {
        this.categoriasService = categoriasService;
    }

    @ModelAttribute("categoriasGlobal")
    public List<Categorias> getCategoriasGlobal() {
        try {
            return categoriasService.getAllCategorias();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
