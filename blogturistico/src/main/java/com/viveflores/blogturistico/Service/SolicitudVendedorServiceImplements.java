package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.SolicitudVendedor;
import com.viveflores.blogturistico.Repository.SolicitudVendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudVendedorServiceImplements implements SolicitudVendedorService {

    @Autowired
    private SolicitudVendedorRepository repo;

    @Override
    public List<SolicitudVendedor> listar() {
        return repo.findAll();
    }

    @Override
    public SolicitudVendedor guardar(SolicitudVendedor solicitudVendedor) {
        return repo.save(solicitudVendedor);
    }

    @Override
    public SolicitudVendedor buscarPorId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public SolicitudVendedor buscarPorIdUsuario(int idUsuario) {
        return repo.findByIdUsuario(idUsuario).orElse(null);
    }

    @Override
    public void eliminar(int id) {
        repo.deleteById(id);
    }
}
