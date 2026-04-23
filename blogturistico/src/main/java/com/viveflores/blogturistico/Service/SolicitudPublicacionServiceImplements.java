package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.SolicitudPublicacion;
import com.viveflores.blogturistico.Repository.SolicitudPublicacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudPublicacionServiceImplements implements SolicitudPublicacionService{

    @Autowired
    private SolicitudPublicacionRepository repo;

    @Override
    public List<SolicitudPublicacion> listar(){
        return repo.findAll();
    }
    @Override
    public SolicitudPublicacion guardar(SolicitudPublicacion solicitudPublicacion) {
       return repo.save(solicitudPublicacion);
    }

    @Override
    public SolicitudPublicacion buscarPorId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(int id) {
        repo.deleteById(id);
    }
    


}

