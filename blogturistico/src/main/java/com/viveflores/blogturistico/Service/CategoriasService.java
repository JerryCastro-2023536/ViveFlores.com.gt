package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Categorias;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriasService {
    List<Categorias> getAllCategorias();
    Categorias getCategoriaById(Integer id);
    Categorias saveCategorias(Categorias categorias);
    Categorias updateCategorias(Integer id, Categorias categorias);
    void deleteCategorias(Integer id);
}