package com.viveflores.blogturistico.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Repository.UsuariosRepository;

@Service
public class UsuariosServiceImplements implements UsuariosService{
    
    @Autowired
    private UsuariosRepository repo;

    @Override
    public List<Usuarios> listar() {
        return repo.findAll();
    }
}
