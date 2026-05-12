package com.viveflores.blogturistico.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viveflores.blogturistico.Entity.servicio;
import com.viveflores.blogturistico.Repository.ServicioRepository;

@Service
public class ServicioServiceImplements implements ServicioService{

    @Autowired
    private ServicioRepository repo;

    @Override
    public List<servicio> listar() {
        return repo.findAll();
    }

    @Override
    public servicio agregarServicio(servicio servicio) {
       return repo.save(servicio);
    }

    @Override
    public servicio buscarPorId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(int id) {
        repo.deleteById(id);
    }
    
}
