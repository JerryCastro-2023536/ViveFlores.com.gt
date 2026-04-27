package com.viveflores.blogturistico.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Repository.PublicacionesRepository;

@Service
public class PublicacionesServiceImplements implements PublicacionesService {
    @Autowired
    PublicacionesRepository repo;

    @Override
    public List<Publicaciones> listar() {
        return repo.findAll();
    }

    @Override
    public Publicaciones guardar(Publicaciones publicaciones) {
        return repo.save(publicaciones);
    }

    @Override
    public Publicaciones buscarporId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(int id) {
        repo.deleteById(id);
    }

    

    
}
