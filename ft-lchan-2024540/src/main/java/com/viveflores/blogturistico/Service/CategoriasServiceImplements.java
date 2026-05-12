package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Categorias;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Repository.CategoriasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriasServiceImplements implements CategoriasService {

    private final CategoriasRepository categoriasRepository;

    public CategoriasServiceImplements(CategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

    @Override
    public List<Categorias> getAllCategorias() {
        return categoriasRepository.findAll();
    }

    @Override
    public Categorias getCategoriaById(Integer id) {
        return categoriasRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
    }

    @Override
    public Categorias saveCategorias(Categorias categorias) {
        return categoriasRepository.save(categorias);
    }

    @Override
    public Categorias updateCategorias(Integer id, Categorias categorias) {
        Categorias categorias1 = categoriasRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));

        categorias1.setNombre_categoria(categorias.getNombre_categoria());
        categorias1.setDescripcion(categorias.getDescripcion());
        return categoriasRepository.save(categorias1);
    }

    @Override
    public void deleteCategorias(Integer id) {
        Categorias categorias = categoriasRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
        categoriasRepository.delete(categorias);
    }
}